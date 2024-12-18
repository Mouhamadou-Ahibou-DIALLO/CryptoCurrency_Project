package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.repository.CryptoCurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The CryptoCurrencyServiceTest class is a JUnit test class for the CryptoCurrencyService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
public class CryptoCurrencyServiceTest {

    /**
     * The cryptoCurrencyRepository field is a mock of the CryptoCurrencyRepository interface.
     */
    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    /**
     * The cryptoCurrencyService field is an instance of the CryptoCurrencyService class.
     */
    @InjectMocks
    private CryptoCurrencyService cryptoCurrencyService;

    /**
     * The cryptoCurrency1 and cryptoCurrency2 fields are instances of the CryptoCurrency class.
     */
    private CryptoCurrency cryptoCurrency1;
    private CryptoCurrency cryptoCurrency2;

    /**
     * Sets up the test environment before each test.
     * Initializes cryptoCurrency1 and cryptoCurrency2 with predefined values.
     */
    @BeforeEach
    public void setUp() {
        cryptoCurrency1 = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        cryptoCurrency2 = new CryptoCurrency(2L, "Bitcoin Cash", "BTC", 10);
    }

    /**
     * Tests the getAllCryptoCurrency() method of the CryptoCurrencyService class.
     * Ensures that the method returns the correct list of CryptoCurrency objects.
     */
    @Test
    public void testGetAllCryptoCurrency() {
        List<CryptoCurrency> cryptoCurrencyList = List.of(cryptoCurrency1, cryptoCurrency2);
        when(cryptoCurrencyRepository.findAll()).thenReturn(cryptoCurrencyList);

        List<CryptoCurrency> result = cryptoCurrencyService.getAllCryptoCurrency();
        assertEquals(2, result.size());

        verify(cryptoCurrencyRepository, times(1)).findAll();
    }

    /**
     * Tests the getCryptoCurrencyById method of the CryptoCurrencyService class.
     * Ensures that the correct CryptoCurrency object is returned when a valid id is provided.
     */
    @Test
    public void testGetCryptoCurrencyById() {
        Long id = 1L;

        when(cryptoCurrencyRepository.findById(id)).thenReturn(java.util.Optional.of(cryptoCurrency1));
        CryptoCurrency result = cryptoCurrencyService.getCryptoCurrencyById(id);

        assertEquals("Bitcoin", result.getName());
        assertEquals("BTC", result.getSymbol());
        assertEquals(1, result.getMarketCapRank());

        verify(cryptoCurrencyRepository, times(1)).findById(id);
    }

    /**
     * Tests the getCryptoCurrencyBySymbol method of the CryptoCurrencyService class.
     * Ensures that the correct CryptoCurrency object is returned when a valid symbol is provided.
     */
    @Test
    public void testGetCryptoCurrencyBySymbol() {
        String symbol = "BTC";

        CryptoCurrency currency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        when(cryptoCurrencyRepository.findBySymbol(symbol)).thenReturn(java.util.Optional.of(currency));

        CryptoCurrency result = cryptoCurrencyService.getCryptoCurrencyBySymbol(symbol);

        assertEquals("Bitcoin", result.getName(), "The name should be 'Bitcoin'");
        assertEquals("BTC", result.getSymbol(), "The symbol should be 'BTC'");
        assertEquals(1, result.getMarketCapRank(), "The market cap rank should be 1");

        verify(cryptoCurrencyRepository, times(1)).findBySymbol(symbol);
    }

    /**
     * Tests the getCryptoCurrencyByName method of the CryptoCurrencyService class.
     * Ensures that the correct CryptoCurrency object is returned when a valid name is provided.
     */
    @Test
    public void testGetCryptoCurrencyByName() {
        String name = "Bitcoin Cash";

        CryptoCurrency currency = new CryptoCurrency(2L, "Bitcoin Cash", "BTC", 10);
        when(cryptoCurrencyRepository.findByName(name)).thenReturn(java.util.Optional.of(currency));
        CryptoCurrency result = cryptoCurrencyService.getCryptoCurrencyByName(name);

        assertEquals("Bitcoin Cash", result.getName(), "The name should be 'Bitcoin Cash'");
        assertEquals("BTC", result.getSymbol(), "The symbol should be 'BTC'");
        assertEquals(10, result.getMarketCapRank(), "The market cap rank should be 10");

        verify(cryptoCurrencyRepository, times(1)).findByName(name);
    }

    /**
     * Tests the getCryptoCurrencyByMarketCapRank method of the CryptoCurrencyService class.
     * Ensures that the correct CryptoCurrency object is returned when a valid market cap rank is provided.
     */
    @Test
    public void testGetCryptoCurrencyByMarketCapRank() {
        int marketCapRank = 20;
        CryptoCurrency currency = new CryptoCurrency(4L, "Bitcoin Cash", "ETH", 20);

        when(cryptoCurrencyRepository.findByMarketCapRank(marketCapRank)).thenReturn(java.util.Optional.of(currency));
        CryptoCurrency result = cryptoCurrencyService.getCryptoCurrencyByMarketCapRank(marketCapRank);

        assertEquals("Bitcoin Cash", result.getName(), "The name should be 'Bitcoin Cash'");
        assertEquals("ETH", result.getSymbol(), "The symbol should be 'ETH'");
        assertEquals(20, result.getMarketCapRank(), "The market cap rank should be 20");

        verify(cryptoCurrencyRepository, times(1)).findByMarketCapRank(marketCapRank);
    }
}
