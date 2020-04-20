package pd.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pd.product.dto.ProductDto;

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

    @GetMapping
    @CrossOrigin(origins = WEB_URL)
    public List<ProductDto> getAllProducts() {
        return productService.getAll()
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{productId}")
    @CrossOrigin(origins = WEB_URL)
    public ResponseEntity<ProductDto> getProductById(@PathVariable int productId) {
        try {
            return new ResponseEntity<>(new ProductDto(productService.getById(productId)), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
