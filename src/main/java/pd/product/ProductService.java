package pd.product;

import org.springframework.http.ResponseEntity;
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

    /**
     * Method for taking list of ProductDto by using filter from frontend
     * @param filter: {@link ProductFilterDto} contains information about filtering
     * @return {@link List<ProductDto>} found list of products ready for sending to frontend
     */
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

    /**
     * Get all products that have discount in current moment
     * @return {@link List<ProductDto>} list of found Products
     */
    public List<ProductDto> getAllWithDiscount() {
        return template.query(ProductFormQueryLine.getAllWithDiscount(), new ProductDtoMapping());
    }

    /**
     * Get the number of all product that have discount in current moment
     * @return {@link Integer} shows the number of found products
     */
    public Integer getCountAllWithDiscount() {
        return productRepository.findCountAllWithDiscount();
    }

    /**
     * Get one product by id
     * @param productId {@link int} shows id of product need to be found
     * @return {@link Optional<ProductDto>} found Product information
     */
    public Optional<Product> getById(int productId) {
        return productRepository.findById(productId);
    }

    /**
     * Get max price of all products using discount
     * @return {@link Double} max price of all products
     */
    public Double getMaxPrice() {
        return productRepository.getMaxPrice();
    }

    /**
     * Get min price of all products using discount
     * @return {@link Double} min price of all products
     */
    public Double getMinPrice() {
        return productRepository.getMinPrice();
    }

    /**
     * Get count of all products stored in db
     * @return {@link Integer} number of all products in db
     */
    public Integer getCountOfProducts() {
        return productRepository.getCount();
    }

    /**
     * Get products by ids
     * @param productIds {@link List<Integer>} contains all ids and products with them need to be found
     * @return {@link List<ProductDto>} list of found Products
     */
    public List<ProductDto> getAllByIds(List<Integer> productIds) {
        if (productIds.isEmpty()) {
            return new ArrayList<>();
        }
        return template.query(ProductFormQueryLine.getAllByIds(productIds), new ProductDtoMapping());
    }

}
