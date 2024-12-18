package com.cryptocurrency.data.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.MarketData;
import com.cryptocurrency.data.repository.CryptoCurrencyRepository;
import com.cryptocurrency.data.repository.MarketDataRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The service for data collection.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Service
public class DataCollectionService {

    /**
     * The repository for CryptoCurrency objects.
     * */
    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    /**
     * The repository for MarketData objects.
     * */
    private final MarketDataRepository marketDataRepository;

    /**
     * The logger for the service.
     * */
    private static final Logger logger = LoggerFactory.getLogger(DataCollectionService.class);

    /**
     * The limit for the number of lines in the table.
     * */
    private static final int LIMIT_LINE = 20;

    /**
     * The bearer token for the CoinCap API.
     * */
    @Value("${api.coincap.bearer-token}")
    private String bearerToken;

    /**
     * Constructs a new instance of the DataCollectionService class.
     *
     * @param cryptoCurrencyRepository The repository for CryptoCurrency objects.
     * @param marketDataRepository The repository for MarketData objects.
     */
    public DataCollectionService(CryptoCurrencyRepository cryptoCurrencyRepository,
                                 MarketDataRepository marketDataRepository) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
        this.marketDataRepository = marketDataRepository;
    }

    /**
     * This method is scheduled to run every 60 seconds and collects market data from the CoinCap API.
     * The collected data is then saved to the database.
     */
    @Scheduled(fixedRate = 60000)
    public void collectMarketData() {
        logger.info("Starting data collection ......");

        String apiUrl = "https://api.coincap.io/v2/assets";

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .header("Authorization", bearerToken)
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseBody = response.body().string();
                List<MarketData> marketDataList = parseMarketData(responseBody);

                enforceTableLimit();

                List<MarketData> limitedMarketData = marketDataList.subList(0, Math.min(marketDataList.size(), LIMIT_LINE));
                marketDataRepository.saveAll(limitedMarketData);

                logger.info("20 new lines inserted into MarketData and CryptoCurrency.");
            }

        } catch (Exception e) {
            logger.error("An error occurred during data collection: ", e);
        }

        logger.info("Finished data collection");
    }

    /**
     * Parses the market data from the CoinCap API response.
     *
     * @param jsonResponse The JSON response from the CoinCap API.
     * @return A list of MarketData objects parsed from the API response.
     */
    private List<MarketData> parseMarketData(String jsonResponse) {
        List<MarketData> marketDataList = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode dataNode = rootNode.path("data");

            int count = 0;
            if (dataNode.isArray()) {
                for (JsonNode node : dataNode) {
                    if (count >= LIMIT_LINE) break;

                    String name = node.path("name").asText();
                    String symbol = node.path("symbol").asText();
                    int marketCapRank = node.path("rank").asInt();

                    CryptoCurrency cryptocurrency = cryptoCurrencyRepository.findByName(name)
                            .stream()
                            .findFirst()
                            .orElseGet(() -> {
                                logger.info("CryptoCurrency {} not found, creating a new one: ", name);
                                CryptoCurrency newCrypto = new CryptoCurrency();
                                newCrypto.setName(name);
                                newCrypto.setSymbol(symbol);
                                newCrypto.setMarketCapRank(marketCapRank);
                                return cryptoCurrencyRepository.save(newCrypto);
                            });

                    MarketData marketData = new MarketData();
                    marketData.setCryptoCurrency(cryptocurrency);
                    marketData.setTimeStamp(LocalDateTime.now());
                    marketData.setPriceUsd(node.path("priceUsd").asDouble());
                    marketData.setVolumeUsd(node.path("volumeUsd24Hr").asDouble());
                    marketData.setMarketCapUsd(node.path("marketCapUsd").asDouble());

                    marketDataList.add(marketData);
                    count++;
                }
            }
        } catch (Exception e) {
            logger.error("Erreur lors du parsing des donn es: ", e);
        }
        return marketDataList;
    }

    /**
     * Enforces the limit on the number of rows in the MarketData table by deleting all rows before inserting new data.
     */
    private void enforceTableLimit() {
        logger.info("Deleting all data in MarketData...");
        marketDataRepository.deleteAll();

        logger.info("All data in MarketData and CryptoCurrency tables have been deleted.");
    }

}

