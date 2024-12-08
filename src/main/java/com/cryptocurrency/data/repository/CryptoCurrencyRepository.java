package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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
     * @param marketCapRank the market capitalization rank of the cryptocurrency to search for
     * @return a list of CryptoCurrency entities with the given market capitalization rank
     */
    List<CryptoCurrency> findByMarketCapRank(int marketCapRank);

    /**
     * Deletes the oldest entries from the CryptoCurrency table.
     * The number of entries to keep is specified by the parameter.
     *
     * @param keepCount the number of entries to keep
     */
    @Query(value = "DELETE FROM crypto_currency WHERE id IN (SELECT id FROM crypto_currency ORDER BY id ASC LIMIT ?1)", nativeQuery = true)
    @Modifying
    void deleteOldestEntries(int keepCount);

}