import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lector = new Scanner(System.in);

        //System.out.println("Ingrese la moneda base:");
        //String monedaBase = lector.nextLine();

        String url = "https://api.exchangerate-api.com/v4/latest/"
                + "usd" + "?apikey="+ "83a6d69288d27dffc3f3bf56";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        Gson gson = new Gson();
        Record record = gson.fromJson(json, Record.class);

        Convertidor convertidor = new Convertidor(record);

        while (true){
            System.out.println("**************************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("\n1) Dolar =>> Peso dominicano");
            System.out.println("2) Peso dominicano =>> Dolar");
            System.out.println("3) Dolar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dolar");
            System.out.println("5) Dolar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dolar");
            System.out.println("7) Salir");
            System.out.println("elija una opcion valida");
            System.out.println("**************************************************");

            int opcion = lector.nextInt();

            if (opcion == 7){
                System.out.println("gracias por usar el conversor");
                break;
            }

            System.out.println("ingrese la cantidad que desea convertir");

            double cantidad = lector.nextDouble();

            Map<String,Double> tasas = convertidor.getRates();
            double resultado = 0;
            String mensaje = "";

            switch (opcion) {
                case 1:
                    resultado = cantidad * tasas.get("DOP");
                    mensaje = cantidad + "[USD] equivalen a " + resultado + " pesos dominicanos";
                    break;
                case 2:
                    resultado = cantidad / tasas.get("DOP");
                    mensaje = cantidad + " pesos dominicanos equivalen a " + resultado + "USD";
                    break;
                case 3:
                    resultado = cantidad * tasas.get("BRL");
                    mensaje = cantidad + "[USD] equivalen a " + resultado + " reales brasileños";
                case 4:
                    resultado = cantidad / tasas.get("BRL");
                    mensaje = cantidad + " reales brasileños equivales a " + resultado + "USD";
                    break;
                case 5:
                    resultado = cantidad * tasas.get("COP");
                    mensaje = cantidad + "[USD] equivalen a " + resultado + " pesos colombianos";
                    break;
                case 6:
                    resultado = cantidad / tasas.get("COP");
                    mensaje = cantidad + " pesos colombianos equivalen a " + resultado + "USD";
                    break;
                default:
                    mensaje = "opcion no valida";
            }
            System.out.println(mensaje);
        }
    }
}
