package pd.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pd.category.dto.CategoryDto;

import java.util.List;
import java.util.stream.Collectors;

import static pd.Constants.WEB_URL;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @CrossOrigin(origins = WEB_URL)
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAll()
                .stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{categoryId}")
    @CrossOrigin(origins = WEB_URL)
    public ResponseEntity<CategoryDto> getAllCategories(@PathVariable int categoryId) {
        try {
            return new ResponseEntity<>(new CategoryDto(categoryService.getById(categoryId)), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
