package pd.ingredient;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient getById(int ingredientId) throws Exception {
        return ingredientRepository.findById(ingredientId).orElseThrow(Exception::new);
    }
}
