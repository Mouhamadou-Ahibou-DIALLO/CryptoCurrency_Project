package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.CryptoCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The CryptoCurrencyRepository interface is a Spring Data JPA repository for managing cryptocurrency data.
 * CryptoCurrencyRepository interface.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Repository
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {


    /**
     * Find a cryptocurrency by its symbol.
     *
     * @param symbol the symbol of the cryptocurrency to find
     * @return an Optional containing the cryptocurrency if found, or an empty Optional if not
     */
    Optional<CryptoCurrency> findBySymbol(String symbol);


    /**
     * Find a cryptocurrency by its name.
     *
     * @param name the name of the cryptocurrency to find
     * @return an Optional containing the cryptocurrency if found, or an empty Optional if not
     */
    Optional<CryptoCurrency> findByName(String name);

    /**
     * Find a cryptocurrency by its market capitalization rank.
     *
     * @param marketCapRank the market capitalization rank of the cryptocurrency to find
     * @return an Optional containing the cryptocurrency if found, or an empty Optional if not
     */
    Optional<CryptoCurrency> findByMarketCapRank(int marketCapRank);
}