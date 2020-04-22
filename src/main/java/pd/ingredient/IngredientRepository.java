package pd.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query(value = "SELECT * FROM ingredient", nativeQuery = true)
    List<Ingredient> findAll();

    @Query(value = "SELECT * FROM ingredient where ingredient.id = ?1", nativeQuery = true)
    Optional<Ingredient> findById(int id);
}
