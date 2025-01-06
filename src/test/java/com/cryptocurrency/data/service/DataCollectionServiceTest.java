package com.cryptocurrency.data.service;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.repository.CryptoCurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * The DataCollectionServiceTest class is a JUnit test class for the DataCollectionService class.
 * Author: Mouhamadou Ahibou DIALLO
 */
//@ExtendWith(MockitoExtension.class)
public class DataCollectionServiceTest {

    /**
//     * The cryptoCurrencyRepository field is a mock of the CryptoCurrencyRepository class.
  //   */
    //@Mock
    //private CryptoCurrencyRepository cryptoCurrencyRepository;

    /**
     * The dataCollectionService field is an instance of the DataCollectionService class.
     */
    //@InjectMocks
    //private DataCollectionService dataCollectionService;

    /**
     * Tests the collectMarketData() method of the DataCollectionService class.
     * Ensures that market data is collected and saved using the cryptoCurrencyRepository.
     */
    //@Test
    //public void testCollectMarketData() {
      //  ReflectionTestUtils.setField(dataCollectionService, "bearerToken", "Bearer testToken123");

        //List<CryptoCurrency> cryptoCurrencies = new ArrayList<>();
        //CryptoCurrency cryptoCurrency = new CryptoCurrency();
        //cryptoCurrency.setName("Test Crypto");
       // cryptoCurrency.setSymbol("TEST");
       // cryptoCurrency.setRank(1);
       // cryptoCurrencies.add(cryptoCurrency);

        //when(cryptoCurrencyRepository.saveAll(any())).thenReturn(cryptoCurrencies);
        //dataCollectionService.collectMarketData();

        //verify(cryptoCurrencyRepository, times(1)).saveAll(any());
    //}

//    /**
//     * Tests the collectMarketData() method of the DataCollectionService class.
//     * Verifies that an exception is thrown if the bearer token is not set.
//     */
//    @Test
//    public void testCollectMarketDataException() {
//        ReflectionTestUtils.setField(dataCollectionService, "bearerToken", null);
//        assertThrows(NullPointerException.class, () -> dataCollectionService.collectMarketData());
//    }
//
//    /**
//     * Tests the enforceTableLimit() method of the DataCollectionService class.
//     * Verifies that data is deleted from the repository before collecting new data.
//     */
//    @Test
//    public void testEnforceTableLimit() {
//        dataCollectionService.collectMarketData();
//        verify(cryptoCurrencyRepository, times(1)).deleteAll();
//    }

}
