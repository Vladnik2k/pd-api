package pd.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import pd.order.dto.NewOrderDto;
import pd.order.dto.OrderDto;
import pd.order_product_mapping.OrderProductMappingService;
import pd.product.Product;
import pd.product.ProductService;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderProductMappingService orderProductMappingService;
    private NamedParameterJdbcTemplate template;

    public OrderService(OrderRepository orderRepository,
                        ProductService productService,
                        OrderProductMappingService orderProductMappingService,
                        NamedParameterJdbcTemplate template) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderProductMappingService = orderProductMappingService;
        this.template = template;
    }

    public Integer createOrder(NewOrderDto newOrderDto) throws Exception {
        Map<Product, Integer> products = formProductQuantityMap(newOrderDto.getProducts());
        Order order = dtoToOrder(newOrderDto);
        order.setPrice(getOrderPrice(products));

        Integer orderId = saveOrder(order);
        products.forEach((product, quantity) -> orderProductMappingService.save(orderId, product.getId(), quantity));

        return orderId;
    }

    public OrderDto getOrderById(Integer orderId) throws Exception {
        Order order = orderRepository.findOptionalById(orderId).orElseThrow(Exception::new);
        Map<Product, Integer> products = orderProductMappingService.getProductsByOrderId(orderId);

        return new OrderDto(order, products);
    }

    public Integer getCountOfCustomers() {
        return orderRepository.getCountOfProducts();
    }

    public Integer getNumberOfActiveOrders() {
        return orderRepository.getNumberOfActiveOrders();
    }

    private Map<Product, Integer> formProductQuantityMap(String products) throws Exception {
        Map<Integer, Integer> map = stringToMap(products);
        map = simplifyProducts(map);

        if (map.isEmpty()) {
            throw new Exception();
        }

        Map<Product, Integer> productQuantityMap = new HashMap<>();
        map.forEach((k, v) -> productQuantityMap.put(productService.getById(k).orElse(null), v));
        if (productQuantityMap.containsKey(null)) {
            throw new Exception();
        }

        return productQuantityMap;
    }

    private Integer saveOrder(Order order) {
        String query = "INSERT INTO orders (email, status_id, created_at, customer_name, customer_surname, delivery_address, price) values" +
                "(:email, 1, CURRENT_TIMESTAMP, :customerName, :customerSurname, :deliveryAddress, :price)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("email", order.getEmail())
                .addValue("customerName", order.getCustomerName())
                .addValue("deliveryAddress", order.getDeliveryAddress())
                .addValue("price", order.getPrice())
                .addValue("customerSurname", order.getCustomerSurname());

        template.update(query, param, keyHolder);

        return ((BigInteger)keyHolder.getKeyList().get(0).get("GENERATED_KEY")).intValue();
    }

    private Map<Integer, Integer> stringToMap(String products) throws JsonProcessingException {
        return new ObjectMapper().readValue(products, new TypeReference<HashMap<Integer, Integer>>() {});
    }

    private Map<Integer, Integer> simplifyProducts(Map<Integer, Integer> products) {
        return products.entrySet().stream()
            .filter(entry -> entry.getValue() > 0)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Order dtoToOrder(NewOrderDto newOrderDto) {
        Order order = new Order();
        order.setCustomerName(newOrderDto.getCustomerName());
        order.setCustomerSurname(newOrderDto.getCustomerSurname());
        order.setEmail(newOrderDto.getEmail());
        order.setDeliveryAddress(newOrderDto.getDeliveryAddress());

        return order;
    }

    private double getOrderPrice(Map<Product, Integer> products) {
        AtomicReference<Double> price = new AtomicReference<>(0D);
        products.forEach((product, quantity) -> price.updateAndGet(v -> v + quantity * product.getPrice()));

        return price.get();
    }
}
