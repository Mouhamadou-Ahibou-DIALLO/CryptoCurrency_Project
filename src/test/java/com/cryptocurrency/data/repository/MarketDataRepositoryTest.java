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

@ExtendWith(MockitoExtension.class)
public class MarketDataRepositoryTest{

    @Mock
    private MarketDataRepository marketDataRepository;
    private CryptoCurrency cryptoCurrency1;
    private MarketData marketData1;
    private MarketData marketData2;
    private MarketData marketData3;
    private MarketData marketData4;
    private MarketData marketData5;

    @BeforeEach
    void setUp() {
        LocalDateTime timeStamp = LocalDateTime.now();
        cryptoCurrency1 = new CryptoCurrency(1L, "Bitcoin", "BTC", 1);
        marketData1 = new MarketData(1L, cryptoCurrency1, timeStamp, 1.0, 1.0, 1.0);
        marketData2 = new MarketData(2L, cryptoCurrency1, timeStamp, 2.0, 2.0, 2.0);
        marketData3 = new MarketData(3L, cryptoCurrency1, timeStamp, 3.0, 3.0, 3.0);
        marketData4 = new MarketData(4L, cryptoCurrency1, timeStamp, 4.0, 4.0, 4.0);
        marketData5 = new MarketData(1L, cryptoCurrency1, timeStamp, 1.0, 1.0, 1.0);
    }

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

    @Test
    void testFindByCryptoCurrency() {
        when(marketDataRepository.findByCryptoCurrency(cryptoCurrency1)).thenReturn(List.of(marketData1, marketData2, marketData3, marketData4));
        List<MarketData> result = marketDataRepository.findByCryptoCurrency(cryptoCurrency1);

        assertEquals(4, result.size());
        assertEquals(1.0, result.get(0).getVolumeUsd());
        assertEquals(2.0, result.get(1).getVolumeUsd());
        assertEquals(3.0, result.get(2).getVolumeUsd());
        assertEquals(4.0, result.get(3).getVolumeUsd());
    }

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

    @Test
    void testFindById() {
        Long id = 1L;
        when(marketDataRepository.findById(id)).thenReturn(java.util.Optional.of(marketData1));
        MarketData result = marketDataRepository.findById(id).get();

        assertEquals(1.0, result.getPriceUsd());
        assertEquals(1.0, result.getVolumeUsd());
        assertEquals(1.0, result.getMarketCapUsd());
    }

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

    @Test
    void TestFindByMarketCapUsd() {
        when(marketDataRepository.findByMarketCapUsd(1.0)).thenReturn(List.of(marketData1, marketData5));
        List<MarketData> result = marketDataRepository.findByMarketCapUsd(1.0);

        assertEquals(2, result.size());
        assertEquals(1.0, result.get(0).getPriceUsd());
        assertEquals(1.0, result.get(1).getPriceUsd());
        assertEquals(1.0, result.get(0).getVolumeUsd());
        assertEquals(1.0, result.get(1).getVolumeUsd());
    }
}
