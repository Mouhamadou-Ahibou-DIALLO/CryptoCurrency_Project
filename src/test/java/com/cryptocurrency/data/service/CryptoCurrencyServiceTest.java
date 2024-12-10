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

@ExtendWith(MockitoExtension.class)
public class CryptoCurrencyServiceTest {

    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;
    @InjectMocks
    private CryptoCurrencyService cryptoCurrencyService;

    private CryptoCurrency cryptoCurrency1;
    private CryptoCurrency cryptoCurrency2;
    private CryptoCurrency cryptoCurrency3;
    private CryptoCurrency cryptoCurrency4;

    @BeforeEach
    public void setUp() {
        cryptoCurrency1 = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        cryptoCurrency2 = new CryptoCurrency(2L, "Bitcoin Cash", "BTC", 10);
        cryptoCurrency3 = new CryptoCurrency(3L, "Bitcoin Cash", "APA", 20);
        cryptoCurrency4 = new CryptoCurrency(4L, "Bitcoin Cash", "ETH", 20);
    }

    @Test
    public void testGetAllCryptoCurrency() {
        List<CryptoCurrency> cryptoCurrencyList = List.of(cryptoCurrency1, cryptoCurrency2);

        when(cryptoCurrencyRepository.findAll()).thenReturn(cryptoCurrencyList);

        List<CryptoCurrency> result = cryptoCurrencyService.getAllCryptoCurrency();
        assertEquals(2, result.size());

        verify(cryptoCurrencyRepository, times(1)).findAll();
    }

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

    @Test
    public void testGetCryptoCurrencyBySymbol() {
        String symbol = "BTC";
        List<CryptoCurrency> mockCryptoList = List.of(cryptoCurrency1, cryptoCurrency2);
        when(cryptoCurrencyRepository.findBySymbol(symbol)).thenReturn(mockCryptoList);
        List<CryptoCurrency> result = cryptoCurrencyService.getCryptoCurrencyBySymbol(symbol);
        assertEquals(2, result.size());
        assertEquals("Bitcoin", result.get(0).getName());
        assertEquals("BTC", result.get(1).getSymbol());
        assertEquals(1, result.get(0).getMarketCapRank());
        verify(cryptoCurrencyRepository, times(1)).findBySymbol(symbol);
    }

    @Test
    public void testGetCryptoCurrencyByName() {
        String name = "Bitcoin Cash";
        List<CryptoCurrency> mockCryptoList = List.of(cryptoCurrency2, cryptoCurrency3);
        when(cryptoCurrencyRepository.findByName(name)).thenReturn(mockCryptoList);
        List<CryptoCurrency> result = cryptoCurrencyService.getCryptoCurrencyByName(name);
        assertEquals(2, result.size());
        assertEquals("Bitcoin Cash", result.get(0).getName());
        assertEquals("APA", result.get(1).getSymbol());
        assertEquals(10, result.get(0).getMarketCapRank());
        verify(cryptoCurrencyRepository, times(1)).findByName(name);
    }

    @Test
    public void testGetCryptoCurrencyByMarketCapRank() {
        int marketCapRank = 20;
        List<CryptoCurrency> mockCryptoList = List.of(cryptoCurrency3, cryptoCurrency4);
        when(cryptoCurrencyRepository.findByMarketCapRank(marketCapRank)).thenReturn(mockCryptoList);
        List<CryptoCurrency> result = cryptoCurrencyService.getCryptoCurrencyByMarketCapRank(marketCapRank);
        assertEquals(2, result.size());
        assertEquals("Bitcoin Cash", result.get(0).getName());
        assertEquals("ETH", result.get(1).getSymbol());
        assertEquals(20, result.get(0).getMarketCapRank());
        verify(cryptoCurrencyRepository, times(1)).findByMarketCapRank(marketCapRank);
    }
}
