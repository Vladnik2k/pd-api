package pd.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query(value = "SELECT ingredient.id, name " +
            "FROM ingredient " +
            "   INNER JOIN product_ingredient_mapping ON ingredient_id = ingredient.id " +
            "WHERE product_id = ?1", nativeQuery = true)
    List<Ingredient> findAllByProductId(int productId);
}
