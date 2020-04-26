package pd.order_status;

import org.springframework.stereotype.Service;

@Service
public class OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusService(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public OrderStatus findById(int id) {
        return orderStatusRepository.findById(id).get();
    }
}
