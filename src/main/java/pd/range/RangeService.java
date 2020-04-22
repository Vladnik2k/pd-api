package pd.range;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RangeService {
    private final RangeRepository rangeRepository;

    public RangeService(RangeRepository rangeRepository) {
        this.rangeRepository = rangeRepository;
    }

    public List<Range> getAll() {
        return rangeRepository.findAll();
    }

    public Range getById(int rangeId) throws Exception {
        return rangeRepository.findById(rangeId).orElseThrow(Exception::new);
    }
}
