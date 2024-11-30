import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "market_data")
public class MarketData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crypto_id", nullable = false)
    private CryptoCurrency cryptoCurrency;

    private LocalDateTime timestamp;
    private Double price_usd;
    private Double volume_usd;
    private Double market_cap_usd;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(Double price_usd) {
        this.price_usd = price_usd;
    }

    public Double getVolume_usd() {
        return volume_usd;
    }

    public void setVolume_usd(Double volume_usd) {
        this.volume_usd = volume_usd;
    }

    public Double getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(Double market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }
}
