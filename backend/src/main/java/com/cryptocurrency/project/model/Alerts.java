import jakarta.persistence.*;

@Entity
@Table(name = "alerts")
public class Alerts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "crypto_id", nullable = false)
    private CryptoCurrency cryptoCurrency;

    private Double price_threshold;
    private Double variation_thrsh;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public void setCryptoCurrency(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    public Double getPrice_threshold() {
        return price_threshold;
    }

    public void setPrice_threshold(Double price_threshold) {
        this.price_threshold = price_threshold;
    }

    public Double getVariation_thrsh() {
        return variation_thrsh;
    }
}
