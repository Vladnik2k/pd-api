package pd.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT * FROM order", nativeQuery = true)
    List<Order> findAll();

    @Query(value = "SELECT * FROM order where order.id = ?1", nativeQuery = true)
    Optional<Order> findById(int id);

}