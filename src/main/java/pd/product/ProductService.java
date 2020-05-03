package pd.product;

import org.springframework.stereotype.Service;
import pd.category.Category;
import pd.category.CategoryService;
import pd.product.dto.ProductFilterDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository,
                          CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public List<Product> getAllByFilter(ProductFilterDto filter) {
        List<Integer> categoryIds = filter.getCategoryIds();
        if (categoryIds.isEmpty()) {
            categoryIds = categoryService.getAll().stream().map(Category::getId).collect(Collectors.toList());
        }
        Double minPrice = filter.getMinPrice() != null ? filter.getMinPrice() : getMinPrice();
        Double maxPrice = filter.getMaxPrice() != null ? filter.getMaxPrice() : getMaxPrice();
        return productRepository.findAllByFilter(categoryIds, filter.getSearchText(), minPrice, maxPrice);
    }

    public List<Product> getAllWithDiscount() {
        return productRepository.findAllWithDiscount();
    }

    public Integer getCountAllWithDiscount() {
        return productRepository.findCountAllWithDiscount();
    }

    public Optional<Product> getById(int productId) {
        return productRepository.findById(productId);
    }

    public Double getMaxPrice() {
        return productRepository.getMaxPrice();
    }

    public Double getMinPrice() {
        return productRepository.getMinPrice();
    }

    public Integer getCountOfProducts() {
        return productRepository.getCount();
    }

    public List<Product> getAllByIds(List<Integer> productIds) {
        return productRepository.findAllByIds(productIds);
    }

}
