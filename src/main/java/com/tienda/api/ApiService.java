package com.tienda.api;
import org.springframework.stereotype.Service;
import java.net.http.*;
        import java.net.URI;
import org.json.JSONObject;
@Service
public class ApiService {

    private static final String URL =
            "https://open.er-api.com/v6/latest/EUR";

    public double convertirEuroADolar(double precioEuro) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .build();

            HttpResponse<String> res =
                    client.send(req, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(res.body());
            double tasa = json.getJSONObject("rates").getDouble("USD");

            return precioEuro * tasa;

        } catch (Exception e) {
            System.out.println("Error API: " + e.getMessage());
            return -1;
        }
    }
}