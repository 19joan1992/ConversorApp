import java.util.Map;

public class Convertidor {
    private String base;
    private String date;
    private Map<String,Double> rates;

    public Convertidor(Record record) {
        this.base = record.base();
        this.date = record.date();
        this.rates = record.rates();
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "ExchangeRateResponse{" +
                "base=" + base +
                ", date=" + date +
                ", rates=" + rates +
                '.';
    }
}
