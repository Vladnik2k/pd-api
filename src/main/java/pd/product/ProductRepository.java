package pd.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product WHERE category_id IN ?1 AND name LIKE %?2%", nativeQuery = true)
    List<Product> findAllByFilter(List<Integer> categoryIds, String searchText);

    @Query(value = "SELECT * FROM product WHERE id IN ?1", nativeQuery = true)
    List<Product> findAllByIds(List<Integer> categoryIds);

    @Query(value = "SELECT * FROM product where product.id = ?1", nativeQuery = true)
    Optional<Product> findById(int id);

    @Query(value = "SELECT MAX(price) FROM product", nativeQuery = true)
    BigDecimal getMaxPrice();

    @Query(value = "SELECT MIN(price) FROM product", nativeQuery = true)
    BigDecimal getMinPrice();
}
