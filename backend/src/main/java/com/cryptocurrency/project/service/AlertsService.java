import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertsService {

    private AlertsRepository alertsRepository;

    public List<Alerts> findByUser(User user) {
        return alertsRepository.findByUser(user);
    }

    public List<Alerts> findByCryptoCurrency(CryptoCurrency cryptoCurrency) {
        return alertsRepository.findByCryptoCurrency(cryptoCurrency);
    }

    public List<Alerts> findByPriceThreshold(Double priceThreshold) {
        return alertsRepository.findByPriceThreshold(priceThreshold);
    }

    public List<Alerts> findByVariationThrsh(Double variationThrsh) {
        return alertsRepository.findByVariationThrsh(variationThrsh);
    }

    public List<Alerts> findAll() {
        return alertsRepository.findAll();
    }

    public Alerts findById(Long id) {
        Optional<Alerts> result = alertsRepository.findById(id);
        return result.orElse(null);
    }
}
