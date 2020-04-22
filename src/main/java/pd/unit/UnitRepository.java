package pd.unit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {

    @Query(value = "SELECT * FROM unit", nativeQuery = true)
    List<Unit> findAll();

    @Query(value = "SELECT * FROM unit where unit.id = ?1", nativeQuery = true)
    Optional<Unit> findById(int id);
}
