package pd.order_product_mapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductMappingRepository extends JpaRepository<OrderProductMapping, Integer> {

    @Query(value = "SELECT * FROM order_product_mapping WHERE order_id = ?1", nativeQuery = true)
    List<OrderProductMapping> findAllByOrderId(Integer orderId);
}
