package pd.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pd.order.dto.NewOrderDto;
import pd.order.dto.OrderDto;
import pd.order_product_mapping.OrderProductMappingService;
import pd.order_status.OrderStatusService;
import pd.product.Product;
import pd.product.ProductService;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static pd.Constants.ORDER_STATUS_PREPARING;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderProductMappingService orderProductMappingService;
    private final OrderStatusService orderStatusService;

    public OrderService(OrderRepository orderRepository,
                        ProductService productService,
                        OrderProductMappingService orderProductMappingService,
                        OrderStatusService orderStatusService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderProductMappingService = orderProductMappingService;
        this.orderStatusService = orderStatusService;
    }

    @Transactional
    public Integer createOrder(NewOrderDto newOrderDto) throws Exception {
        Map<Product, Integer> products = formProductQuantityMap(newOrderDto.getProducts());
        Order order = dtoToOrder(newOrderDto);
        order.setPrice(getOrderPrice(products));

        orderRepository.save(order);
        products.forEach((product, quantity) -> orderProductMappingService.save(order, product, quantity));

        return order.getId();
    }

    public OrderDto getOrderById(Integer orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(Exception::new);
        Map<Product, Integer> products = orderProductMappingService.getProductsByOrderId(orderId);

        return new OrderDto(order, products);
    }

    private Map<Product, Integer> formProductQuantityMap(String products) throws Exception {
        Map<Integer, Integer> map = stringToMap(products);
        map = simplifyProducts(map);

        if (map.isEmpty()) {
            throw new Exception();
        }

        Map<Product, Integer> productQuantityMap = new HashMap<>();
        map.forEach((k, v) -> productQuantityMap.put(productService.getByIdOptional(k).orElse(null), v));
        if (productQuantityMap.containsKey(null)) {
            throw new Exception();
        }

        return productQuantityMap;
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
        order.setStatus(orderStatusService.findById(ORDER_STATUS_PREPARING));
        order.setCreatedAt(Instant.now());
        order.setCustomerName(newOrderDto.getCustomerName());
        order.setCustomerSurname(newOrderDto.getCustomerSurname());
        order.setEmail(newOrderDto.getEmail());
        order.setDeliveryAddress(newOrderDto.getDeliveryAddress());

        return order;
    }

    private double getOrderPrice(Map<Product, Integer> products) {
        AtomicReference<Double> price = new AtomicReference<>(0D);
        products.forEach((product, quantity) -> price.updateAndGet(v -> v + quantity * productService.getPriceForProduct(product)));

        return price.get();
    }
}
