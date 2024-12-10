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

@ExtendWith(MockitoExtension.class)
public class MarketDataServiceTest {

    @Mock
    private MarketDataRepository marketDataRepository;

    @InjectMocks
    private MarketDataService marketDataService;

    private CryptoCurrency cryptoCurrency;

    private MarketData marketData1;
    private MarketData marketData2;
    private MarketData marketData3;
    private MarketData marketData4;
    private MarketData marketData5;
    private MarketData marketData6;

    @BeforeEach
    void setUp() {
        cryptoCurrency = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        marketData1 = new MarketData(1L, cryptoCurrency, LocalDateTime.now(), 1.0, 1.0, 1.0);
        marketData2 = new MarketData(2L, cryptoCurrency, LocalDateTime.now(), 2.0, 2.0, 2.0);
        marketData3 = new MarketData(3L, cryptoCurrency, LocalDateTime.now(), 3.0, 3.0, 3.0);
        marketData4 = new MarketData(4L, cryptoCurrency, LocalDateTime.now(), 4.0, 4.0, 4.0);
        marketData5 = new MarketData(5L, cryptoCurrency, LocalDateTime.now(), 5.0, 5.0, 5.0);
        marketData6 = new MarketData(6L, cryptoCurrency, LocalDateTime.now(), 5.0, 5.0, 5.0);
    }

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

    @Test
    void testFindById() {
        Long id = 1L;
        when(marketDataRepository.findById(id)).thenReturn(java.util.Optional.of(marketData1));
        MarketData result = marketDataService.findById(id);

        assertEquals(1.0, result.getPriceUsd());
        assertEquals(1.0, result.getVolumeUsd());
        assertEquals(1.0, result.getMarketCapUsd());
    }

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

    @Test
    void testFindByPriceUsd() {
        when(marketDataRepository.findByPriceUsd(1.0)).thenReturn(List.of(marketData5, marketData6));
        List<MarketData> result = marketDataService.findByPriceUsd(1.0);

        assertEquals(2, result.size());
        assertEquals(5.0, result.get(0).getPriceUsd());
        assertEquals(5.0, result.get(1).getPriceUsd());
        assertEquals(5.0, result.get(0).getVolumeUsd());
        assertEquals(5.0, result.get(1).getVolumeUsd());
        assertEquals(5.0, result.get(0).getMarketCapUsd());
        assertEquals(5.0, result.get(1).getMarketCapUsd());
    }

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

    @Test
    void testFindByCryptoCurrency() {
        when(marketDataRepository.findByCryptoCurrency(cryptoCurrency)).thenReturn(List.of(marketData5, marketData6));
        List<MarketData> result = marketDataService.findByCryptoCurrency(cryptoCurrency);

        assertEquals(2, result.size());
        assertEquals(5.0, result.get(0).getPriceUsd());
        assertEquals(5.0, result.get(1).getPriceUsd());
        assertEquals(5.0, result.get(0).getVolumeUsd());
        assertEquals(5.0, result.get(1).getVolumeUsd());
    }
}
