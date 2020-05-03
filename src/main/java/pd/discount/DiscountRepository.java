package pd.discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    @Query(value = "SELECT MAX(percent) FROM discount WHERE CURRENT_TIMESTAMP BETWEEN activate_from AND activate_by", nativeQuery = true)
    double findMaxByPercent();

}
