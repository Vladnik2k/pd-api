package pd.discount;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pd.discount.dto.DiscountDto;

import java.util.List;
import java.util.stream.Collectors;

import static pd.Constants.WEB_URL;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    @CrossOrigin(origins = WEB_URL)
    public List<DiscountDto> getAllDiscounts() {
        return discountService.getAll()
                .stream()
                .map(DiscountDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{discountId}")
    @CrossOrigin(origins = WEB_URL)
    public ResponseEntity<DiscountDto> getDiscountById(@PathVariable int discountId) {
        try {
            return new ResponseEntity<>(new DiscountDto(discountService.getById(discountId)), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("max")
    @CrossOrigin(origins = WEB_URL)
    public double getMaxDiscountPercent() {
        return discountService.getMaxDiscountPercent();
    }
}
