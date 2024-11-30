import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoCurrencyService {

    private CryptoCurrencyRepository cryptoCurrencyRepository;

    public List<CryptoCurrency> getCryptoCurrencyByMarket_cap_rank(int market_cap_rank) {
        return cryptoCurrencyRepository.findByMarket_cap_rank(market_cap_rank);
    }

    public List<CryptoCurrency> getCryptoCurrencyBySymbol(String symbol) {
        return cryptoCurrencyRepository.findBySymbol(symbol);
    }

    public List<CryptoCurrency> getCryptoCurrencyByName(String name) {
        return cryptoCurrencyRepository.findByName(name);
    }

    public List<CryptoCurrency> getAllCryptoCurrency() {
        return cryptoCurrencyRepository.findAll();
    }

    public CryptoCurrency getCryptoCurrencyById(Long id) {
        return cryptoCurrencyRepository.findById(id).orElse(null);
    }
}
