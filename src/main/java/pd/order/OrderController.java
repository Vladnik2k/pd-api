package pd.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pd.order.dto.NewOrderDto;
import pd.order.dto.OrderDto;

import javax.validation.Valid;

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
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid NewOrderDto newOrderDto) {
        try {
            return new ResponseEntity<>(orderService.createOrder(newOrderDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{orderId}")
    @CrossOrigin(origins = WEB_URL)
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Integer orderId) {
        try {
            return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("customers-count")
    @CrossOrigin(origins = WEB_URL)
    public Integer getCountOfCustomers() {
        return orderService.getCountOfCustomers();
    }

    @GetMapping("active-orders")
    @CrossOrigin(origins = WEB_URL)
    public Integer getNumberOfActiveOrders() {
        return orderService.getNumberOfActiveOrders();
    }
}
