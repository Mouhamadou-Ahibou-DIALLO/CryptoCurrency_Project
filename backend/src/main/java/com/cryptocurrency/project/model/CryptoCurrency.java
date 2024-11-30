import jakarta.persistence.*;

@Entity
@Table(name = "crypto_currency")
public class CryptoCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String symbol;
    private int market_cap_rank;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getMarket_cap_rank() {
        return market_cap_rank;
    }

    public void setMarket_cap_rank(int market_cap_rank) {
        this.market_cap_rank = market_cap_rank;
    }
}
