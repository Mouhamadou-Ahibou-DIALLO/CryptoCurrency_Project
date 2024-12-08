package com.cryptocurrency.data.repository;

import com.cryptocurrency.data.model.Alerts;
import com.cryptocurrency.data.model.CryptoCurrency;
import com.cryptocurrency.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertsRepository extends JpaRepository<Alerts, Long> {

    /**
     * Find all alerts for a given user.
     *
     * @param user the ID of the user to find alerts for
     * @return a list of alerts for the given user
     */
    List<Alerts> findByUser(User user);

    /**
     * Find all alerts for a given cryptocurrency.
     *
     * @param cryptoCurrency the ID of the cryptocurrency to find alerts for
     * @return a list of alerts for the given cryptocurrency
     */
    List<Alerts> findByCryptoCurrency(CryptoCurrency cryptoCurrency);

    /**
     * Find all alerts for a given price threshold.
     *
     * @param priceThreshold the price threshold to find alerts for
     * @return a list of alerts for the given price threshold
     */
    List<Alerts> findByPriceThreshold(Double priceThreshold);

    /**
     * Find all alerts for a given variation threshold.
     *
     * @param variationThreshold the variation threshold to find alerts for
     * @return a list of alerts for the given variation threshold
     */
    List<Alerts> findByVariationThreshold(Double variationThreshold);
}
