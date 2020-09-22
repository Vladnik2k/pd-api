package pd.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pd.product.dto.ProductDto;
import pd.product.dto.ProductFilterDto;

import java.util.List;
import java.util.stream.Collectors;

import static pd.Constants.WEB_URL;

/**
 * Controller uses "/api/product" URL
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Geting all product by filter
     * @param filter {@link ProductFilterDto} contains information for filtering
     * @return {@link List<ProductDto>} list of found Products
     */
    @PostMapping("filter")
    @CrossOrigin(origins = WEB_URL)
    public List<ProductDto> getAllProductsByFilter(@RequestBody ProductFilterDto filter) {
        return productService.getAllByFilter(filter);
    }

    /**
     * Get all products that have discount in current moment
     * @return {@link List<ProductDto>} list of found Products
     */
    @GetMapping("/discount")
    @CrossOrigin(origins = WEB_URL)
    public List<ProductDto> getAllWithDiscount() {
        return productService.getAllWithDiscount();
    }

    /**
     * Get the number of all product that have discount in current moment
     * @return {@link Integer} shows the number of found products
     */
    @GetMapping("/discount/count")
    @CrossOrigin(origins = WEB_URL)
    public Integer getCountAllWithDiscount() {
        return productService.getCountAllWithDiscount();
    }

    /**
     * Get products by ids
     * @param productIds {@link List<Integer>} contains all ids and products with them need to be found
     * @return {@link List<ProductDto>} list of found Products
     */
    @PostMapping("ids")
    @CrossOrigin(origins = WEB_URL)
    public List<ProductDto> getProductsByIds(@RequestBody List<Integer> productIds) {
        return productService.getAllByIds(productIds);
    }

    /**
     * Get one product by id
     * @param productId {@link int} shows id of product need to be found
     * @return {@link ResponseEntity<ProductDto>} found Product
     */
    @GetMapping("{productId}")
    @CrossOrigin(origins = WEB_URL)
    public ResponseEntity<ProductDto> getProductById(@PathVariable int productId) {
        try {
            return new ResponseEntity<>(new ProductDto(productService.getById(productId).orElseThrow(Exception::new)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get max price of all products using discount
     * @return {@link Double} max price of all products
     */
    @GetMapping("max")
    @CrossOrigin(origins = WEB_URL)
    public Double getMaxPrice() {
        return productService.getMaxPrice();
    }

    /**
     * Get min price of all products using discount
     * @return {@link Double} min price of all products
     */
    @GetMapping("min")
    @CrossOrigin(origins = WEB_URL)
    public Double getMinPrice() {
        return productService.getMinPrice();
    }

    /**
     * Get count of all products stored in db
     * @return {@link Integer} number of all products in db
     */
    @GetMapping("count")
    @CrossOrigin(origins = WEB_URL)
    public Integer getCountOfProducts() {
        return productService.getCountOfProducts();
    }

}
