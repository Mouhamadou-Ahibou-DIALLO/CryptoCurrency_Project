package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.CryptoCurrency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The CryptoCurrencyRepositoryTest class is a JUnit test class for the CryptoCurrencyRepository class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
class CryptoCurrencyRepositoryTest {

    /**
     * The cryptoCurrencyRepository object is used to mock the CryptoCurrencyRepository class.
     */
    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    /**
     * The cryptoCurrency1 and cryptoCurrency2 objects are used to mock the CryptoCurrency class.
     */
    private CryptoCurrency cryptoCurrency1;
    private CryptoCurrency cryptoCurrency2;

    /**
     * The setUp method is used to initialize the cryptoCurrency1 and cryptoCurrency2 objects before each test.
     */
    @BeforeEach
    void setUp() {
        cryptoCurrency1 = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        cryptoCurrency2 = new CryptoCurrency(2L, "Bitcoin Cash", "BTC2", 10);
    }

    /**
     * Tests the findBySymbol method of the CryptoCurrencyRepository class.
     * Ensures that a CryptoCurrency object is returned when a valid symbol is provided.
     * Verifies that findBySymbol is called once.
     */
    @Test
    void testFindBySymbol() {
        String symbol = "BTC";

        when(cryptoCurrencyRepository.findBySymbol(symbol)).thenReturn(java.util.Optional.of(cryptoCurrency1));
        Optional<CryptoCurrency> result = cryptoCurrencyRepository.findBySymbol(symbol);
        CryptoCurrency cryptoCurrency = result.orElseThrow(() -> new RuntimeException("CryptoCurrency not found"));

        assertEquals("Bitcoin", cryptoCurrency.getName());
        assertEquals("BTC", cryptoCurrency.getSymbol());
        assertEquals(1, cryptoCurrency.getMarketCapRank());

        verify(cryptoCurrencyRepository, times(1)).findBySymbol(symbol);
    }

    /**
     * Tests the findByName method of the CryptoCurrencyRepository class.
     * Ensures that a CryptoCurrency object is returned when a valid name is provided.
     * Verifies that findByName is called once.
     */
    @Test
    void testFindByName() {
        String name = "Bitcoin Cash";

        when(cryptoCurrencyRepository.findByName(name)).thenReturn(java.util.Optional.of(cryptoCurrency2));
        Optional<CryptoCurrency> result = cryptoCurrencyRepository.findByName(name);

        verify(cryptoCurrencyRepository, times(1)).findByName(name);

        CryptoCurrency cryptoCurrency = result.orElseThrow(() -> new RuntimeException("CryptoCurrency not found"));
        assertEquals("Bitcoin Cash", cryptoCurrency.getName());
        assertEquals("BTC2", cryptoCurrency.getSymbol());
        assertEquals(10, cryptoCurrency.getMarketCapRank());
    }

    /**
     * Tests the findByMarketCapRank method of the CryptoCurrencyRepository class.
     * Ensures that a CryptoCurrency object is returned when a valid market cap rank is provided.
     * Verifies that findByMarketCapRank is called once.
     */
    @Test
    void testFindByMarketCapRank() {
        int marketCapRank = 1;

        when(cryptoCurrencyRepository.findByMarketCapRank(marketCapRank)).thenReturn(java.util.Optional.of(cryptoCurrency1));
        Optional<CryptoCurrency> result = cryptoCurrencyRepository.findByMarketCapRank(marketCapRank);

        CryptoCurrency cryptoCurrency = result.orElseThrow(() -> new RuntimeException("CryptoCurrency not found"));
        assertEquals("Bitcoin", cryptoCurrency.getName());
        assertEquals("BTC", cryptoCurrency.getSymbol());
        assertEquals(1, cryptoCurrency.getMarketCapRank());

        verify(cryptoCurrencyRepository, times(1)).findByMarketCapRank(marketCapRank);
    }

    /**
     * Tests the findAll method of the CryptoCurrencyRepository class.
     * Ensures that all CryptoCurrency objects are returned correctly.
     */
    @Test
    void testFindAll() {
        List<CryptoCurrency> mockCryptoList = List.of(cryptoCurrency1, cryptoCurrency2);

        when(cryptoCurrencyRepository.findAll()).thenReturn(mockCryptoList);
        List<CryptoCurrency> result = cryptoCurrencyRepository.findAll();

        assertEquals(2, result.size(), "The size of the result should be 2");
        assertEquals("Bitcoin", result.get(0).getName(), "The name of the first crypto should be 'Bitcoin'");
        assertEquals("BTC2", result.get(1).getSymbol(), "The symbol of the second crypto should be 'BTC2'");
        assertEquals(1, result.get(0).getMarketCapRank(), "The market cap rank of the first crypto should be 1");
    }

    /**
     * Tests the findById method of the CryptoCurrencyRepository class.
     * Ensures that a CryptoCurrency object is returned when a valid id is provided.
     */
    @Test
    void testFindById() {
        Long id = 1L;
        when(cryptoCurrencyRepository.findById(id)).thenReturn(java.util.Optional.of(cryptoCurrency1));
        CryptoCurrency result = cryptoCurrencyRepository.findById(id).orElse(null);

        assert result != null;
        assertEquals("Bitcoin", result.getName());
        assertEquals("BTC", result.getSymbol());
        assertEquals(1, result.getMarketCapRank());
    }
}
