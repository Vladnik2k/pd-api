package pd.order_product_mapping;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import pd.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderProductMappingService {
    private final OrderProductMappingRepository orderProductMappingRepository;
    private NamedParameterJdbcTemplate template;

    public OrderProductMappingService(OrderProductMappingRepository orderProductMappingRepository,
                                      NamedParameterJdbcTemplate template) {
        this.orderProductMappingRepository = orderProductMappingRepository;
        this.template = template;
    }

    public void save(Integer orderId, Integer productId, Integer quantity) {
        String query = "INSERT INTO order_product_mapping (order_id, product_id, quantity) values (:orderId, :productId, :quantity)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("orderId", orderId)
                .addValue("productId", productId)
                .addValue("quantity", quantity);

        template.update(query, param, keyHolder);
    }

    public Map<Product, Integer> getProductsByOrderId(Integer orderId) {
        List<OrderProductMapping> orderProductMappings = orderProductMappingRepository.findAllByOrderId(orderId);
        Map<Product, Integer> products = new HashMap<>();
        orderProductMappings.forEach(oPM -> products.put(oPM.getProduct(), oPM.getQuantity()));

        return products;
    }
}
