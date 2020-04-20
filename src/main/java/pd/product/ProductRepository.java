package pd.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> findAll();

    @Query(value = "SELECT * FROM product where product.id = ?1", nativeQuery = true)
    Optional<Product> findById(int id);
}
