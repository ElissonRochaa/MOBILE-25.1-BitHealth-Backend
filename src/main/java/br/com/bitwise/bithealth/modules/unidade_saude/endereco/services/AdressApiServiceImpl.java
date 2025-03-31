package br.com.bitwise.bithealth.modules.unidade_saude.endereco.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class AdressApiServiceImpl implements AdressApiService {

    @Value("${api.geocoding.secret}")
    private String apiKey;

    @Override
    public String getCoordinatesAsString(String address) {
        try {
            String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8.toString());
            String urlString = String.format("https://api.opencagedata.com/geocode/v1/json?q=%s&key=%s&pretty=1&language=pt-BR",
                    encodedAddress, apiKey);

            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();

            JsonObject status = response.getAsJsonObject("status");
            String message = status.get("message").getAsString();

            if ("OK".equals(message)) {
                JsonObject result = response.getAsJsonArray("results").get(0).getAsJsonObject();
                JsonObject geometry = result.getAsJsonObject("geometry");

                if (geometry != null && geometry.has("lat") && geometry.has("lng")) {
                    double lat = geometry.get("lat").getAsDouble();
                    double lng = geometry.get("lng").getAsDouble();
                    return lat + "," + lng;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
