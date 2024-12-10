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

@Service
public class DataCollectionService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final MarketDataRepository marketDataRepository;
    private static final Logger logger = LoggerFactory.getLogger(DataCollectionService.class);
    private static final int LIMIT_LINE = 20;

    @Value("${api.coincap.bearer-token}")
    private String bearerToken;

    public DataCollectionService(CryptoCurrencyRepository cryptoCurrencyRepository,
                                 MarketDataRepository marketDataRepository) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
        this.marketDataRepository = marketDataRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void collectMarketData() {
        logger.info("Début de la collecte de données ......");

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

                logger.info("20 nouvelles lignes insérées dans MarketData et CryptoCurrency.");
            }

        } catch (Exception e) {
            logger.error("Une erreur s'est produite lors de la collecte de données: ", e);
        }

        logger.info("Fin de la collecte de données");
    }

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
            logger.error("Erreur lors du parsing des données: ", e);
        }
        return marketDataList;
    }

    private void enforceTableLimit() {
        logger.info("Suppression des données dans MarketData...");
        marketDataRepository.deleteAll();
        logger.info("Suppression des données dans CryptoCurrency...");
        cryptoCurrencyRepository.deleteAll();

        logger.info("Toutes les données des tables MarketData et CryptoCurrency ont été supprimées.");
    }

}

