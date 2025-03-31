package br.com.bitwise.bithealth.modules.unidade_saude.endereco.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public interface AdressApiService {
    String getCoordinatesAsString(String address);
}
