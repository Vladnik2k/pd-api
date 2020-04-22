package pd.order;

import org.springframework.stereotype.Service;
import pd.order.dto.NewOrderDto;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(NewOrderDto newOrderDto) throws Exception {
        return null;
    }
}
