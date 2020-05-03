package pd.category;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
