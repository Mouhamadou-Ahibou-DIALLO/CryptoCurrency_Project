import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

    /**
     * Finds a list of CryptoCurrency entities by their symbol.
     *
     * @param symbol the symbol of the cryptocurrency to search for
     * @return a list of CryptoCurrency entities with the given symbol
     */
    List<CryptoCurrency> findBySymbol(String symbol);

    /**
     * Finds a list of CryptoCurrency entities by their name.
     *
     * @param name the name of the cryptocurrency to search for
     * @return a list of CryptoCurrency entities with the given name
     */
    List<CryptoCurrency> findByName(String name);

    /**
     * Finds a list of CryptoCurrency entities by their market capitalization rank.
     *
     * @param market_cap_rank the market capitalization rank of the cryptocurrency to search for
     * @return a list of CryptoCurrency entities with the given market capitalization rank
     */
    List<CryptoCurrency> findByMarket_cap_rank(int market_cap_rank);
}