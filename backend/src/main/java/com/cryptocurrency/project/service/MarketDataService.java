import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MarketDataService {

    private MarketDataRepository marketDataRepository;

    public List<MarketData> findByCryptoCurrency(CryptoCurrency cryptoCurrency) {
        return marketDataRepository.findByCryptoCurrency(cryptoCurrency);
    }

    public List<MarketData> findByTimestamp(Date timestamp) {
        return marketDataRepository.findByTimestamp(timestamp);
    }

    public List<MarketData> findByPriceUsd(Double priceUsd) {
        return marketDataRepository.findByPriceUsd(priceUsd);
    }

    public List<MarketData> findByVolumeUsd(Double volumeUsd) {
        return marketDataRepository.findByVolumeUsd(volumeUsd);
    }

    public List<MarketData> findByMarketCapUsd(Double marketCapUsd) {
        return marketDataRepository.findByMarket_cap_usd(marketCapUsd);
    }

    public List<MarketData> findAll() {
        return marketDataRepository.findAll();
    }

    public MarketData findById(Long id) {
        Optional<MarketData> result = marketDataRepository.findById(id);
        return result.orElse(null);
    }
}
