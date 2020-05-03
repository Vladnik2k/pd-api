package pd.discount;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public double getMaxDiscountPercent() {
        return discountRepository.findMaxByPercent();
    }
}
