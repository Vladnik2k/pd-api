package pd.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value =
            "SELECT product.id, product.name, product.description, product.category_id, product.image_url," +
            "       IF(discount.percent IS NULL, price, price - price / 100 * discount.percent) AS price" +
            "    FROM product" +
            "        LEFT JOIN discount ON discount.product_id = product.id" +
            "            AND discount.activate_by >= CURRENT_TIMESTAMP" +
            "            AND discount.activate_from <= CURRENT_TIMESTAMP" +
            "    WHERE product.id = ?1",
            nativeQuery = true)
    Optional<Product> findById(int id);

    @Query(value =
            "SELECT COUNT(*) FROM product" +
            "    INNER JOIN discount ON discount.product_id = product.id" +
            "        AND discount.activate_by >= CURRENT_TIMESTAMP" +
            "        AND discount.activate_from <= CURRENT_TIMESTAMP",
            nativeQuery = true)
    Integer findCountAllWithDiscount();

    @Query(value =
            "SELECT MAX(IF(discount.percent IS NULL, price, price - price / 100 * discount.percent))" +
            "    FROM product" +
            "        LEFT JOIN discount ON discount.product_id = product.id" +
            "            AND discount.activate_by >= CURRENT_TIMESTAMP" +
            "            AND discount.activate_from <= CURRENT_TIMESTAMP",
            nativeQuery = true)
    Double getMaxPrice();

    @Query(value =
            "SELECT MIN(IF(discount.percent IS NULL, price, price - price / 100 * discount.percent))" +
            "    FROM product" +
            "        LEFT JOIN discount ON discount.product_id = product.id" +
            "            AND discount.activate_by >= CURRENT_TIMESTAMP" +
            "            AND discount.activate_from <= CURRENT_TIMESTAMP",
            nativeQuery = true)
    Double getMinPrice();

    @Query(value = "SELECT COUNT(*) FROM product", nativeQuery = true)
    Integer getCount();
}
