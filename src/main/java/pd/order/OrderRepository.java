package pd.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM orders where orders.id = ?1", nativeQuery = true)
    Optional<Order> findById(int id);

    @Query(value = "SELECT COUNT(DISTINCT email) FROM orders", nativeQuery = true)
    Integer getCountOfProducts();

    @Query(value =
            "SELECT COUNT(*) FROM orders WHERE status_id IN" +
            "(SELECT id FROM order_status WHERE name = 'Готується' or name LIKE 'У кур%')",
            nativeQuery = true)
    Integer getNumberOfActiveOrders();

}
