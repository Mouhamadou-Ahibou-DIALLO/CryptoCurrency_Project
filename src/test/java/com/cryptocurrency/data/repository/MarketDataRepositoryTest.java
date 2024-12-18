package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.MarketData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * The MarketDataRepositoryTest class is a JUnit test class for the MarketDataRepository class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
public class MarketDataRepositoryTest{

    /**
     * The marketDataRepository variable is a mock of the MarketDataRepository class.
     */
    @Mock
    private MarketDataRepository marketDataRepository;

    /**
     * The marketData1 variable is a object of the MarketData class.
     */
    private MarketData marketData1;

    /**
     * The marketData2 variable is a object of the MarketData class.
     */
    private MarketData marketData2;

    /**
     * The marketData3 variable is a object of the MarketData class.
     */
    private MarketData marketData3;

    /**
     * The marketData4 variable is a object of the MarketData class.
     */
    private MarketData marketData4;

    /**
     * The marketData5 variable is a object of the MarketData class.
     */
    private MarketData marketData5;

    /**
     * Sets up the test environment before each test.
     * Creates a MarketData object, a CryptoCurrency object and a LocalDateTime object.
     */
    @BeforeEach
    void setUp() {
        LocalDateTime timeStamp = LocalDateTime.now();
        CryptoCurrency cryptoCurrency1 = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);

        marketData1 = new MarketData(1L, cryptoCurrency1, timeStamp, 1.0, 1.0, 1.0);
        marketData2 = new MarketData(2L, cryptoCurrency1, timeStamp, 2.0, 2.0, 2.0);
        marketData3 = new MarketData(3L, cryptoCurrency1, timeStamp, 3.0, 3.0, 3.0);
        marketData4 = new MarketData(4L, cryptoCurrency1, timeStamp, 4.0, 4.0, 4.0);
        marketData5 = new MarketData(1L, cryptoCurrency1, timeStamp, 1.0, 1.0, 1.0);
    }

    /**
     * Test the findByTimeStamp() method of the MarketDataRepository class.
     * This test case tests if the findByTimeStamp() method returns the correct list of MarketData objects
     * when given a valid LocalDateTime object.
     */
    @Test
    void testFindByTimeStamp() {
        LocalDateTime timeStamp = LocalDateTime.now();

        when(marketDataRepository.findByTimeStamp(timeStamp)).thenReturn(List.of(marketData1, marketData2, marketData3, marketData4));
        List<MarketData> result = marketDataRepository.findByTimeStamp(timeStamp);

        assertEquals(4, result.size());

        assertEquals(1.0, result.get(0).getPriceUsd());
        assertEquals(2.0, result.get(1).getPriceUsd());
        assertEquals(3.0, result.get(2).getPriceUsd());
        assertEquals(4.0, result.get(3).getPriceUsd());
    }

    /**
     * Test the findByCryptoCurrency() method of the MarketDataRepository class.
     * This test case tests if the findByCryptoCurrency() method returns the correct MarketData object
     * when given a valid CryptoCurrency object.
     */
    @Test
    void testFindByCryptoCurrency() {
        CryptoCurrency currency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);

        when(marketDataRepository.findByCryptoCurrency(currency)).thenReturn(java.util.Optional.of(marketData1));
        MarketData result = marketDataRepository.findByCryptoCurrency(currency).orElse(null);

        assert result != null;
        assertEquals(1.0, result.getPriceUsd());
        assertEquals(1.0, result.getVolumeUsd());
        assertEquals(1.0, result.getMarketCapUsd());
    }

    /**
     * Test the findAll() method of the MarketDataRepository class.
     * This test case tests if the findAll() method returns the correct list of MarketData objects.
     */
    @Test
    void testFindAll() {
        when(marketDataRepository.findAll()).thenReturn(List.of(marketData1, marketData2, marketData3, marketData4));
        List<MarketData> result = marketDataRepository.findAll();

        assertEquals(4, result.size());
        assertEquals(1.0, result.get(0).getMarketCapUsd());
        assertEquals(2.0, result.get(1).getMarketCapUsd());
        assertEquals(3.0, result.get(2).getMarketCapUsd());
        assertEquals(4.0, result.get(3).getMarketCapUsd());
    }

    /**
     * Test the findById() method of the MarketDataRepository class.
     * This test case tests if the findById() method returns the correct MarketData object
     * when given a valid id.
     */
    @Test
    void testFindById() {
        Long id = 1L;

        when(marketDataRepository.findById(id)).thenReturn(java.util.Optional.of(marketData1));
        MarketData result = marketDataRepository.findById(id).orElse(null);

        assert result != null;
        assertEquals(1.0, result.getPriceUsd());
        assertEquals(1.0, result.getVolumeUsd());
        assertEquals(1.0, result.getMarketCapUsd());
    }

    /**
     * Test the findByPriceUsd() method of the MarketDataRepository class.
     * This test case tests if the findByPriceUsd() method returns the correct MarketData objects
     * when given a valid price in USD.
     */
    @Test
    void TestFindByPriceUsd() {
        when(marketDataRepository.findByPriceUsd(1.0)).thenReturn(List.of(marketData1, marketData5));
        List<MarketData> result = marketDataRepository.findByPriceUsd(1.0);

        assertEquals(2, result.size());
        assertEquals(1.0, result.get(0).getPriceUsd());
        assertEquals(1.0, result.get(1).getPriceUsd());
        assertEquals(1.0, result.get(0).getVolumeUsd());
        assertEquals(1.0, result.get(1).getVolumeUsd());
        assertEquals(1.0, result.get(0).getMarketCapUsd());
        assertEquals(1.0, result.get(1).getMarketCapUsd());
    }

    /**
     * Test the findByVolumeUsd() method of the MarketDataRepository class.
     * This test case tests if the findByVolumeUsd() method returns the correct MarketData objects
     * when given a valid volume in USD.
     */
    @Test
    void TestFindByVolumeUsd() {
        when(marketDataRepository.findByVolumeUsd(1.0)).thenReturn(List.of(marketData1, marketData5));
        List<MarketData> result = marketDataRepository.findByVolumeUsd(1.0);

        assertEquals(2, result.size());
        assertEquals(1.0, result.get(0).getPriceUsd());
        assertEquals(1.0, result.get(1).getPriceUsd());
        assertEquals(1.0, result.get(0).getVolumeUsd());
        assertEquals(1.0, result.get(1).getVolumeUsd());
        assertEquals(1.0, result.get(0).getMarketCapUsd());
        assertEquals(1.0, result.get(1).getMarketCapUsd());
    }

    /**
     * Test the findByMarketCapUsd() method of the MarketDataRepository class.
     * This test case tests if the findByMarketCapUsd() method returns the correct MarketData objects
     * when given a valid market cap in USD.
     */
    @Test
    void testFindByMarketCapUsd() {
        when(marketDataRepository.findByMarketCapUsd(1.0)).thenReturn(List.of(marketData1, marketData5));
        List<MarketData> result = marketDataRepository.findByMarketCapUsd(1.0);
        assertEquals(2, result.size());

        assertEquals(1.0, result.get(0).getPriceUsd());
        assertEquals(1.0, result.get(1).getPriceUsd());
        assertEquals(1.0, result.get(0).getVolumeUsd());
        assertEquals(1.0, result.get(1).getVolumeUsd());
        assertEquals(1.0, result.get(0).getMarketCapUsd());
        assertEquals(1.0, result.get(1).getMarketCapUsd());
    }
}
