package pd.order_status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

    @Query(value = "SELECT * FROM order_status where id = ?1", nativeQuery = true)
    Optional<OrderStatus> findById(int id);

}
