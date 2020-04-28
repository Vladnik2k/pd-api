package pd.discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    @Query(value = "SELECT * FROM discount", nativeQuery = true)
    List<Discount> findAll();

    @Query(value = "SELECT * FROM discount where discount.id = ?1", nativeQuery = true)
    Optional<Discount> findById(int id);

    @Query(value = "SELECT MAX(percent) FROM discount", nativeQuery = true)
    double findMaxByPercent();

    @Query(value = "SELECT * FROM discount WHERE product_id = ?1 " +
            "AND activate_from < CURRENT_TIMESTAMP AND activate_by > CURRENT_TIMESTAMP", nativeQuery = true)
    Optional<Discount> findByProductIdNow(Integer productId);

}
