package pd.discount;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> getAll() {
        return discountRepository.findAll();
    }

    public Discount getById(int discountId) throws Exception {
        return discountRepository.findById(discountId).orElseThrow(Exception::new);
    }

    public double getMaxDiscountPercent() {
        return discountRepository.findMaxByPercent();
    }
}
