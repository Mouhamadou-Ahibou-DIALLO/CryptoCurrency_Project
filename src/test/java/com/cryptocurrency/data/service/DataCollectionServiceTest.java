package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.MarketData;
import com.cryptocurrency.data.repository.CryptoCurrencyRepository;
import com.cryptocurrency.data.repository.MarketDataRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DataCollectionServiceTest {

    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @Mock
    private MarketDataRepository marketDataRepository;

    @InjectMocks
    private DataCollectionService dataCollectionService;

    private CryptoCurrency cryptoCurrency;
    private MarketData marketData;

    @BeforeEach
    public void setup() {
        cryptoCurrency = new CryptoCurrency();
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

    @Test
    public void testCollectMarketData() {
        ReflectionTestUtils.setField(dataCollectionService, "bearerToken", "Bearer testToken123");

        List<MarketData> marketDataList = new ArrayList<>();
        marketDataList.add(marketData);
        when(marketDataRepository.saveAll(any())).thenReturn(marketDataList);
        dataCollectionService.collectMarketData();

        verify(marketDataRepository, times(1)).saveAll(any());
    }

    @Test
    public void testCollectMarketDataException() {
        ReflectionTestUtils.setField(dataCollectionService, "bearerToken", null);
        dataCollectionService.collectMarketData();
    }

}
