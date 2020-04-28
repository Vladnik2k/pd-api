package pd.ingredient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pd.ingredient.dto.IngredientDto;

import java.util.List;
import java.util.stream.Collectors;

import static pd.Constants.WEB_URL;

@RestController
@RequestMapping("/api/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("{productId}")
    @CrossOrigin(origins = WEB_URL)
    public List<IngredientDto> getIngredientById(@PathVariable int productId) {
        return ingredientService.getAllByProductId(productId)
                .stream()
                .map(IngredientDto::new)
                .collect(Collectors.toList());
    }
}
