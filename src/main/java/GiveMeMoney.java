import java.math.BigDecimal;

public class GiveMeMoney {
    private BigDecimal amount;

    public GiveMeMoney() {
        this.amount = new BigDecimal("0.00");
    }

    public void addAmount(BigDecimal amount){
        if (amount.compareTo(new BigDecimal("0.00")) < 0){
            return;
        }
        this.amount = this.amount.add(amount);
    }

    public String getAmountAsString(){
        return amount.toString();
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
