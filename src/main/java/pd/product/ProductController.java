package pd.product;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<ProductDto> findAll() {
        return productService.getAll()
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }
}
