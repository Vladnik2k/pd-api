package pd.product;

import org.springframework.stereotype.Service;
import pd.category.Category;
import pd.category.CategoryService;
import pd.discount.Discount;
import pd.discount.DiscountService;
import pd.product.dto.ProductFilterDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final DiscountService discountService;

    public ProductService(ProductRepository productRepository,
                          CategoryService categoryService,
                          DiscountService discountService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.discountService = discountService;
    }

    public List<Product> getAllByFilter(ProductFilterDto filter) {
        List<Integer> categoryIds = filter.getCategoryIds();
        if (categoryIds.isEmpty()) {
            categoryIds = categoryService.getAll().stream().map(Category::getId).collect(Collectors.toList());
        }
        return productRepository.findAllByFilter(categoryIds, filter.getSearchText());
    }

    public Product getById(int productId) throws Exception {
        return productRepository.findById(productId).orElseThrow(Exception::new);
    }

    public Optional<Product> getByIdOptional(Integer productId) {
        return productRepository.findById(productId);
    }

    public BigDecimal getMaxPrice() {
        return productRepository.getMaxPrice();
    }

    public BigDecimal getMinPrice() {
        return productRepository.getMinPrice();
    }

    public List<Product> getAllByIds(List<Integer> productIds) {
        return productRepository.findAllByIds(productIds);
    }

    public double getPriceForProduct(Product product) {
        Optional<Discount> discount = discountService.findByProductId(product.getId());
        return discount.map(value -> product.getPrice() / 100 * (100 - value.getPercent())).orElseGet(product::getPrice);
    }
}
