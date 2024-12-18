package com.cryptocurrency.data.model;

import jakarta.persistence.*;

/**
 * This class represents an Alerts object.
 * Author: Mouhamadou Ahibou DIALLO
 */
@Entity
@Table(name = "alerts")
public class Alerts {

    /**
     * The ID of the Alerts object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user associated with the Alerts object.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The market data associated with the Alerts object.
     */
    @ManyToOne
    @JoinColumn(name = "market_id", nullable = false)
    private MarketData marketData;

    /**
     * The price threshold associated with the Alerts object.
     */
    private Double priceThreshold;

    /**
     * The variation threshold associated with the Alerts object.
     */
    private Double variationThreshold;

    /**
     * Default constructor for the Alerts class.
     */
    public Alerts() {}


    /**
     * Constructor for the Alerts class.
     *
     * @param id The ID of the Alerts object.
     * @param user The user associated with the Alerts object.
     * @param marketData The market data associated with the Alerts object.
     * @param priceThreshold The price threshold associated with the Alerts object.
     * @param variationThreshold The variation threshold associated with the Alerts object.
     */
    public Alerts(Long id, User user, MarketData marketData, Double priceThreshold, Double variationThreshold) {
        this.id = id;
        this.user = user;
        this.marketData = marketData;
        this.priceThreshold = priceThreshold;
        this.variationThreshold = variationThreshold;
    }

    /**
     * Returns the user associated with the Alerts object.
     *
     * @return The user associated with the Alerts object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the Alerts object.
     *
     * @param user The user associated with the Alerts object.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets the ID of the Alerts object.
     *
     * @param id The ID of the Alerts object.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the ID of the Alerts object.
     *
     * @return The ID of the Alerts object.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the MarketData associated with the Alerts object.
     *
     * @return The MarketData associated with the Alerts object.
     */
    public MarketData getMarketData() {
        return marketData;
    }

    /**
     * Sets the MarketData associated with the Alerts object.
     *
     * @param marketData The MarketData associated with the Alerts object.
     */
    public void setMarketData(MarketData marketData) {
        this.marketData = marketData;
    }

    /**
     * Returns the price threshold associated with the Alerts object.
     *
     * @return The price threshold associated with the Alerts object.
     */
    public Double getPriceThreshold() {
        return priceThreshold;
    }

    /**
     * Sets the price threshold associated with the Alerts object.
     *
     * @param priceThreshold The price threshold associated with the Alerts object.
     */
    public void setPriceThreshold(Double priceThreshold) {
        this.priceThreshold = priceThreshold;
    }

    /**
     * Returns the variation threshold associated with the Alerts object.
     *
     * @return The variation threshold associated with the Alerts object.
     */
    public Double getVariationThreshold() {
        return variationThreshold;
    }

    /**
     * Sets the variation threshold associated with the Alerts object.
     *
     * @param variationThreshold The variation threshold to set for the Alerts object.
     */
    public void setVariationThreshold(Double variationThreshold) {
        this.variationThreshold = variationThreshold;
    }
}

/*

Pour documenter ton API :

Utilise Postman pour définir tous tes endpoints et générer une documentation partagée.
Ou ajoute Swagger à ton projet :

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.api"))
                .paths(PathSelectors.any())
                .build();
    }
}

 */
