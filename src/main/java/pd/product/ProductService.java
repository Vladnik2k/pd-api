package pd.product;

import org.springframework.stereotype.Service;
import pd.category.Category;
import pd.category.CategoryService;
import pd.product.dto.ProductFilterDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
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

    public BigDecimal getMaxPrice() {
        return productRepository.getMaxPrice();
    }

    public BigDecimal getMinPrice() {
        return productRepository.getMinPrice();
    }
}
