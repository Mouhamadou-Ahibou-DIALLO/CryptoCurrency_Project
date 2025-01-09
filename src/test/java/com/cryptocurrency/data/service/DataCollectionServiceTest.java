package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.CryptoPriceHistory;
import com.cryptocurrency.data.repository.CryptoCurrencyRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * The DataCollectionServiceTest class is a JUnit test class for the DataCollectionService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
@ExtendWith(MockitoExtension.class)
public class DataCollectionServiceTest {

    /**
     * The cryptoCurrencyRepository field is a mock of the CryptoCurrencyRepository class.
     */
    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    /**
     * The dataCollectionService field is an instance of the DataCollectionService class.
     */
    @InjectMocks
    private DataCollectionService dataCollectionService;

    @BeforeAll
    public static void setUp() throws IOException {
        String jsonContent = """
        {
            "Bitcoin": [
                {"date": "2025-01-01", "price": 40000.0},
                {"date": "2025-01-02", "price": 41000.0}
            ]
        }
        """;

        Path path = Paths.get("src/main/resources/crypto_price_history.json");
        Files.writeString(path, jsonContent);
    }

    /**
     * Tests the collectMarketData() method of the DataCollectionService class.
     * Ensures that market data is collected and saved using the cryptoCurrencyRepository.
     */
    @Test
    public void testCollectMarketData() {
        ReflectionTestUtils.setField(dataCollectionService, "bearerToken", "Bearer testToken123");

        List<CryptoCurrency> cryptoCurrencies = new ArrayList<>();
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setName("Test Crypto");
        cryptoCurrency.setSymbol("TEST");
        cryptoCurrency.setRank(1);
        cryptoCurrencies.add(cryptoCurrency);

        when(cryptoCurrencyRepository.saveAll(any())).thenReturn(cryptoCurrencies);
        dataCollectionService.collectMarketData();

        verify(cryptoCurrencyRepository, times(1)).saveAll(any());
    }

    /**
     * Tests the getCryptoPriceHistoryMap() method of the DataCollectionService class.
     * Ensures that the method returns a map of all the CryptoPriceHistory objects in the database.
     * Each key is the name of a cryptocurrency, and the value is a list of
     * CryptoPriceHistory objects for that currency.
     */
    @Test
    public void testGetCryptoPriceHistoryMap() {

        List<CryptoPriceHistory> priceHistoryList = new ArrayList<>();
        priceHistoryList.add(new CryptoPriceHistory(LocalDateTime.now(), 100.0));

        try (MockedStatic<DataCollectionService> mockedService = Mockito.mockStatic(DataCollectionService.class)) {
            mockedService.when(DataCollectionService::getCryptoPriceHistoryMap)
                    .thenReturn(Map.of("bitcoin", priceHistoryList));

            Map<String, List<CryptoPriceHistory>> result = DataCollectionService.getCryptoPriceHistoryMap();

            assertEquals(1, result.size());
            assertEquals(priceHistoryList, result.get("bitcoin"));
        }
    }
}
