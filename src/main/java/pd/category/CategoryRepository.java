package pd.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM category", nativeQuery = true)
    List<Category> findAll();

    @Query(value = "SELECT * FROM category where category.id = ?1", nativeQuery = true)
    Optional<Category> findById(int id);

}
