package pd.discount;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static pd.Constants.WEB_URL;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("max")
    @CrossOrigin(origins = WEB_URL)
    public double getMaxDiscountPercent() {
        return discountService.getMaxDiscountPercent();
    }
}
