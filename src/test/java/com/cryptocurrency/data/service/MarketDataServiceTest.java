package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.MarketData;
import com.cryptocurrency.data.repository.MarketDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The MarketDataServiceTest class is a JUnit test class for the MarketDataService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
public class MarketDataServiceTest {

    /**
     * The marketDataRepository field is a mock of the MarketDataRepository interface.
     */
    @Mock
    private MarketDataRepository marketDataRepository;

    /**
     * The marketDataService field is an instance of the MarketDataService class.
     */
    @InjectMocks
    private MarketDataService marketDataService;

    /**
     * The marketData1, marketData2, marketData3, marketData4, marketData5 and marketData6 fields are instances of the MarketData class.
     */
    private MarketData marketData1;
    private MarketData marketData2;
    private MarketData marketData3;
    private MarketData marketData4;
    private MarketData marketData5;
    private MarketData marketData6;

    /**
     * Sets up the test environment before each test.
     * Creates multiple MarketData objects with different values.
     */
    @BeforeEach
    void setUp() {
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);

        marketData1 = new MarketData(1L, cryptoCurrency, LocalDateTime.now(), 1.0, 1.0, 1.0);
        marketData2 = new MarketData(2L, cryptoCurrency, LocalDateTime.now(), 2.0, 2.0, 2.0);
        marketData3 = new MarketData(3L, cryptoCurrency, LocalDateTime.now(), 3.0, 3.0, 3.0);
        marketData4 = new MarketData(4L, cryptoCurrency, LocalDateTime.now(), 4.0, 4.0, 4.0);
        marketData5 = new MarketData(5L, cryptoCurrency, LocalDateTime.now(), 5.0, 5.0, 5.0);
        marketData6 = new MarketData(6L, cryptoCurrency, LocalDateTime.now(), 5.0, 5.0, 5.0);
    }

    /**
     * Tests the findAll() method of the MarketDataService class.
     * Ensures that it returns all market data stored in the MarketDataRepository.
     */
    @Test
    void testFindAll() {
        when(marketDataRepository.findAll()).thenReturn(List.of(marketData1, marketData2, marketData3, marketData4, marketData5));
        List<MarketData> result = marketDataService.findAll();

        assertEquals(5, result.size());
        assertEquals(1.0, result.get(0).getPriceUsd());
        assertEquals(2.0, result.get(1).getPriceUsd());
        assertEquals(3.0, result.get(2).getPriceUsd());
        assertEquals(4.0, result.get(3).getPriceUsd());
        assertEquals(5.0, result.get(4).getPriceUsd());
    }

    /**
     * Tests the findById() method of the MarketDataService class.
     * Ensures that it returns a MarketData object when given a valid id.
     */
    @Test
    void testFindById() {
        Long id = 1L;

        when(marketDataRepository.findById(id)).thenReturn(java.util.Optional.of(marketData1));
        MarketData result = marketDataService.findById(id);

        assertEquals(1.0, result.getPriceUsd());
        assertEquals(1.0, result.getVolumeUsd());
        assertEquals(1.0, result.getMarketCapUsd());
    }

    /**
     * Tests the findByTimeStamp() method of the MarketDataService class.
     * Ensures that it returns a list of MarketData objects when given a valid LocalDateTime object.
     */
    @Test
    void testFindByTimeStamp() {
        LocalDateTime timeStamp = LocalDateTime.now();
        when(marketDataRepository.findByTimeStamp(timeStamp)).thenReturn(List.of(marketData1, marketData2, marketData3, marketData4, marketData5));
        List<MarketData> result = marketDataService.findByTimeStamp(timeStamp);

        assertEquals(5, result.size());
        assertEquals(1.0, result.get(0).getPriceUsd());
        assertEquals(2.0, result.get(1).getPriceUsd());
        assertEquals(3.0, result.get(2).getPriceUsd());
        assertEquals(4.0, result.get(3).getPriceUsd());
        assertEquals(5.0, result.get(4).getPriceUsd());
    }

    /**
     * Tests the findByPriceUsd() method of the MarketDataService class.
     * Ensures that it returns a list of MarketData objects when given a valid price in USD.
     */
    @Test
    void testFindByPriceUsd() {
        double price = 1.0;

        when(marketDataRepository.findByPriceUsd(price)).thenReturn(List.of(marketData5, marketData6));
        List<MarketData> result = marketDataService.findByPriceUsd(price);

        assertEquals(2, result.size());
        assertEquals(5.0, result.get(0).getPriceUsd());
        assertEquals(5.0, result.get(1).getPriceUsd());
        assertEquals(5.0, result.get(0).getVolumeUsd());
        assertEquals(5.0, result.get(1).getVolumeUsd());
        assertEquals(5.0, result.get(0).getMarketCapUsd());
        assertEquals(5.0, result.get(1).getMarketCapUsd());
    }

    /**
     * Tests the findByVolumeUsd() method of the MarketDataService class.
     * Ensures that it returns a list of MarketData objects when given a valid volume in USD.
     */
    @Test
    void testFindByVolumeUsd() {
        when(marketDataRepository.findByVolumeUsd(1.0)).thenReturn(List.of(marketData5, marketData6));
        List<MarketData> result = marketDataService.findByVolumeUsd(1.0);

        assertEquals(2, result.size());
        assertEquals(5.0, result.get(0).getPriceUsd());
        assertEquals(5.0, result.get(1).getPriceUsd());
        assertEquals(5.0, result.get(0).getVolumeUsd());
        assertEquals(5.0, result.get(1).getVolumeUsd());
        assertEquals(5.0, result.get(0).getMarketCapUsd());
        assertEquals(5.0, result.get(1).getMarketCapUsd());
    }

    /**
     * Tests the findByMarketCapUsd() method of the MarketDataService class.
     * Ensures that it returns a list of MarketData objects when given a valid market cap in USD.
     */
    @Test
    void testFindByMarketCapUsd() {
        when(marketDataRepository.findByMarketCapUsd(1.0)).thenReturn(List.of(marketData5, marketData6));
        List<MarketData> result = marketDataService.findByMarketCapUsd(1.0);

        assertEquals(2, result.size());
        assertEquals(5.0, result.get(0).getPriceUsd());
        assertEquals(5.0, result.get(1).getPriceUsd());
        assertEquals(5.0, result.get(0).getVolumeUsd());
        assertEquals(5.0, result.get(1).getVolumeUsd());
    }

    /**
     * Tests the findByCryptoCurrency() method of the MarketDataService class.
     * Ensures that it returns the correct MarketData object when given a valid CryptoCurrency object.
     */
    @Test
    void testFindByCryptoCurrency() {
        CryptoCurrency currency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);

        when(marketDataRepository.findByCryptoCurrency(currency)).thenReturn(java.util.Optional.of(marketData1));
        MarketData result = marketDataService.findByCryptoCurrency(currency);

        assertEquals(1.0, result.getPriceUsd());
        assertEquals(1.0, result.getVolumeUsd());
        assertEquals(1.0, result.getMarketCapUsd());
    }
}
