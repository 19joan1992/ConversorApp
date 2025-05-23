import java.util.Map;

public record Record(String base,
                     String date,
                     Map<String,Double> rates) {
}
