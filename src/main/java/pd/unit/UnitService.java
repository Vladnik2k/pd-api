package pd.unit;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<Unit> getAll() {
        return unitRepository.findAll();
    }

    public Unit getById(int unitId) throws Exception {
        return unitRepository.findById(unitId).orElseThrow(Exception::new);
    }
}
