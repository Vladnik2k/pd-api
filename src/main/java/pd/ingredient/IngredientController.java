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

    @GetMapping
    @CrossOrigin(origins = WEB_URL)
    public List<IngredientDto> getAllIngredients() {
        return ingredientService.getAll()
                .stream()
                .map(IngredientDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{ingredientId}")
    @CrossOrigin(origins = WEB_URL)
    public ResponseEntity<IngredientDto> getIngredientById(@PathVariable int ingredientId) {
        try {
            return new ResponseEntity<>(new IngredientDto(ingredientService.getById(ingredientId)), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
