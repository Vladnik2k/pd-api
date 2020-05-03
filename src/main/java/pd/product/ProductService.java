package pd.product;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import pd.category.Category;
import pd.category.CategoryService;
import pd.product.dto.ProductDto;
import pd.product.dto.ProductFilterDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private NamedParameterJdbcTemplate template;

    public ProductService(ProductRepository productRepository,
                          CategoryService categoryService,
                          NamedParameterJdbcTemplate template) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.template = template;
    }

    public List<ProductDto> getAllByFilter(ProductFilterDto filter) {
        List<Integer> categoryIds = filter.getCategoryIds();
        if (categoryIds.isEmpty()) {
            categoryIds = categoryService.getAll().stream().map(Category::getId).collect(Collectors.toList());
        }

        Double minPrice = filter.getMinPrice() != null ? filter.getMinPrice() : getMinPrice();
        Double maxPrice = filter.getMaxPrice() != null ? filter.getMaxPrice() : getMaxPrice();

        return template.query(ProductFormQueryLine.getAllByFilter(categoryIds, filter.getSearchText(), minPrice, maxPrice),
                new ProductDtoMapping());
    }

    public List<ProductDto> getAllWithDiscount() {
        return template.query(ProductFormQueryLine.getAllWithDiscount(), new ProductDtoMapping());
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

    public List<ProductDto> getAllByIds(List<Integer> productIds) {
        if (productIds.isEmpty()) {
            return new ArrayList<>();
        }
        return template.query(ProductFormQueryLine.getAllByIds(productIds), new ProductDtoMapping());
    }

}
