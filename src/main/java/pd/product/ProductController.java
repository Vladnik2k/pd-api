package pd.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pd.product.dto.ProductDto;
import pd.product.dto.ProductFilterDto;

import java.util.List;
import java.util.stream.Collectors;

import static pd.Constants.WEB_URL;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("filter")
    @CrossOrigin(origins = WEB_URL)
    public List<ProductDto> getAllProductsByFilter(@RequestBody ProductFilterDto filter) {
        return productService.getAllByFilter(filter)
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/discount")
    @CrossOrigin(origins = WEB_URL)
    public List<ProductDto> getAllWithDiscount() {
        return productService.getAllWithDiscount()
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/discount/count")
    @CrossOrigin(origins = WEB_URL)
    public Integer getCountAllWithDiscount() {
        return productService.getCountAllWithDiscount();
    }

    @PostMapping("ids")
    @CrossOrigin(origins = WEB_URL)
    public List<ProductDto> getProductsByIds(@RequestBody List<Integer> productIds) {
        return productService.getAllByIds(productIds)
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{productId}")
    @CrossOrigin(origins = WEB_URL)
    public ResponseEntity<ProductDto> getProductById(@PathVariable int productId) {
        try {
            return new ResponseEntity<>(new ProductDto(productService.getById(productId).orElseThrow(Exception::new)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("max")
    @CrossOrigin(origins = WEB_URL)
    public Double getMaxPrice() {
        return productService.getMaxPrice();
    }

    @GetMapping("min")
    @CrossOrigin(origins = WEB_URL)
    public Double getMinPrice() {
        return productService.getMinPrice();
    }

    @GetMapping("count")
    @CrossOrigin(origins = WEB_URL)
    public Integer getCountOfProducts() {
        return productService.getCountOfProducts();
    }

}
