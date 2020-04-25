package pd.order_product_mapping;

import org.springframework.stereotype.Service;
import pd.order.Order;
import pd.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderProductMappingService {
    private final OrderProductMappingRepository orderProductMappingRepository;

    public OrderProductMappingService(OrderProductMappingRepository orderProductMappingRepository) {
        this.orderProductMappingRepository = orderProductMappingRepository;
    }

    public void save(Order order, Product product, Integer quantity) {
        orderProductMappingRepository.save(new OrderProductMapping(order, product, quantity));
    }

    public Map<Integer, Integer> getProductsByOrderId(Integer orderId) {
        List<OrderProductMapping> orderProductMappings = orderProductMappingRepository.findAllByOrderId(orderId);
        Map<Integer, Integer> products = new HashMap<>();
        orderProductMappings.forEach(oPM -> products.put(oPM.getProduct().getId(), oPM.getQuantity()));

        return products;
    }
}
