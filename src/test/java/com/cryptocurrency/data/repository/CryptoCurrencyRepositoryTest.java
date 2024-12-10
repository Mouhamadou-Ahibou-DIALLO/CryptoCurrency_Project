package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.CryptoCurrency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class CryptoCurrencyRepositoryTest {

    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;
    private CryptoCurrency cryptoCurrency1;
    private CryptoCurrency cryptoCurrency2;
    private CryptoCurrency cryptoCurrency3;
    private CryptoCurrency cryptoCurrency4;

    @BeforeEach
    void setUp() {
        cryptoCurrency1 = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        cryptoCurrency2 = new CryptoCurrency(2L, "Bitcoin Cash", "BTC", 10);
        cryptoCurrency3 = new CryptoCurrency(3L, "Bitcoin Cash", "APA", 20);
        cryptoCurrency4 = new CryptoCurrency(4L, "Bitcoin Cash", "ETH", 20);
    }

    @Test
    void testFindBySymbol() {
        String symbol = "BTC";
        List<CryptoCurrency> mockCryptoList = List.of(cryptoCurrency1, cryptoCurrency2);

        when(cryptoCurrencyRepository.findBySymbol(symbol)).thenReturn(mockCryptoList);
        List<CryptoCurrency> result = cryptoCurrencyRepository.findBySymbol(symbol);

        assertEquals(2, result.size());
        assertEquals("Bitcoin", result.get(0).getName());
        assertEquals("BTC", result.get(1).getSymbol());
        assertEquals(1, result.get(0).getMarketCapRank());

        verify(cryptoCurrencyRepository, times(1)).findBySymbol(symbol);
    }

    @Test
    void testFindByName() {
        String name = "Bitcoin Cash";
        List<CryptoCurrency> mockCryptoList = List.of(cryptoCurrency2, cryptoCurrency3);

        when(cryptoCurrencyRepository.findByName(name)).thenReturn(mockCryptoList);
        List<CryptoCurrency> result = cryptoCurrencyRepository.findByName(name);

        assertEquals(2, result.size());
        assertEquals("Bitcoin Cash", result.get(0).getName());
        assertEquals("APA", result.get(1).getSymbol());
        assertEquals(10, result.get(0).getMarketCapRank());

        verify(cryptoCurrencyRepository, times(1)).findByName(name);
    }

    @Test
    void testFindByMarketCapRank() {
        int marketCapRank = 20;
        List<CryptoCurrency> mockCryptoList = List.of(cryptoCurrency3, cryptoCurrency4);

        when(cryptoCurrencyRepository.findByMarketCapRank(marketCapRank)).thenReturn(mockCryptoList);
        List<CryptoCurrency> result = cryptoCurrencyRepository.findByMarketCapRank(marketCapRank);

        assertEquals(2, result.size());
        assertEquals("Bitcoin Cash", result.get(0).getName());
        assertEquals("ETH", result.get(1).getSymbol());
        assertEquals(20, result.get(0).getMarketCapRank());

        verify(cryptoCurrencyRepository, times(1)).findByMarketCapRank(marketCapRank);
    }

    @Test
    void testFindAll() {
        List<CryptoCurrency> mockCryptoList = List.of(cryptoCurrency1, cryptoCurrency2, cryptoCurrency3, cryptoCurrency4);

        when(cryptoCurrencyRepository.findAll()).thenReturn(mockCryptoList);
        List<CryptoCurrency> result = cryptoCurrencyRepository.findAll();

        assertEquals(4, result.size());
        assertEquals("Bitcoin", result.get(0).getName());
        assertEquals("BTC", result.get(1).getSymbol());
        assertEquals(1, result.get(0).getMarketCapRank());
    }

    @Test
    void testFindById() {
        Long id = 1L;
        when(cryptoCurrencyRepository.findById(id)).thenReturn(java.util.Optional.of(cryptoCurrency1));
        CryptoCurrency result = cryptoCurrencyRepository.findById(id).get();

        assertEquals("Bitcoin", result.getName());
        assertEquals("BTC", result.getSymbol());
        assertEquals(1, result.getMarketCapRank());
    }
}
