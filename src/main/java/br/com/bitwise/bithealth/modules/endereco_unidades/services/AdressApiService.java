package br.com.bitwise.bithealth.modules.endereco_unidades.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class AdressApiService {

    // Chave da API
    private static final String API_KEY = "120b0ab6378b401aade85ddaa831ba17";

    public String getCoordinatesAsString(String address) {
        try {
            String urlString = String.format(
                    "https://api.opencagedata.com/geocode/v1/json?q=%s&key=%s",
                    address.replace(" ", "+"), API_KEY);

            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();

            if ("OK".equals(response.get("status").getAsString())) {
                JsonObject location = response.getAsJsonArray("results")
                        .get(0).getAsJsonObject()
                        .getAsJsonObject("geometry")
                        .getAsJsonObject("location");

                double lat = location.get("lat").getAsDouble();
                double lng = location.get("lng").getAsDouble();

                return lat + "," + lng;
            } else {
                System.out.println("Erro na geocodificação: " + response.get("status").getAsString());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
