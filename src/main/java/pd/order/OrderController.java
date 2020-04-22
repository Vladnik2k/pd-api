package pd.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pd.order.dto.NewOrderDto;

import static pd.Constants.WEB_URL;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @CrossOrigin(origins = WEB_URL)
    public ResponseEntity<?> createOrder(@RequestBody NewOrderDto newOrderDto) {
        try {
            orderService.createOrder(newOrderDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
