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
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * The DataCollectionServiceTest class is a JUnit test class for the DataCollectionService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
public class DataCollectionServiceTest {

    /**
     * The marketDataRepository field is a mock of the MarketDataRepository class.
     */
    @Mock
    private MarketDataRepository marketDataRepository;

    /**
     * The dataCollectionService field is an instance of the DataCollectionService class.
     */
    @InjectMocks
    private DataCollectionService dataCollectionService;

    /**
     * The marketData field is a mock of the MarketData class.
     */
    private MarketData marketData;

    /**
     * Sets up the test environment before each test.
     * Creates a MarketData object, a CryptoCurrency object and a LocalDateTime object.
     */
    @BeforeEach
    public void setUp() {
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setName("Test Crypto");
        cryptoCurrency.setSymbol("TEST");
        cryptoCurrency.setMarketCapRank(1);

        marketData = new MarketData();
        marketData.setCryptoCurrency(cryptoCurrency);
        marketData.setTimeStamp(LocalDateTime.now());
        marketData.setPriceUsd(10.0);
        marketData.setVolumeUsd(100.0);
        marketData.setMarketCapUsd(1000.0);
    }

    /**
     * Tests the collectMarketData() method of the DataCollectionService class.
     * Ensures that market data is collected and saved using the marketDataRepository.
     */
    @Test
    public void testCollectMarketData() {
        ReflectionTestUtils.setField(dataCollectionService, "bearerToken", "Bearer testToken123");

        List<MarketData> marketDataList = new ArrayList<>();
        marketDataList.add(marketData);

        when(marketDataRepository.saveAll(any())).thenReturn(marketDataList);
        dataCollectionService.collectMarketData();

        verify(marketDataRepository, times(1)).saveAll(any());
    }

    /**
     * Tests the collectMarketData() method of the DataCollectionService class.
     * Verifies that an exception is thrown if the bearer token is not set.
     */
    @Test
    public void testCollectMarketDataException() {
        ReflectionTestUtils.setField(dataCollectionService, "bearerToken", null);
        assertThrows(NullPointerException.class, () -> dataCollectionService.collectMarketData());
    }

}
