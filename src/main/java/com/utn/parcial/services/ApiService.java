package com.utn.parcial.services;

import com.google.gson.*;
import com.utn.parcial.exceptions.CurrencyApiException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ApiService {
    private static final String BASE_URL = "https://www.dolarsi.com/api/api.php?type=";
    private final HttpClient httpClient;
    private HttpResponse<String> response;

    public ApiService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @CircuitBreaker(name = "euro", fallbackMethod = "fallback")
    public Float getEuro() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "genedolar&opc=ta"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonArray arr = JsonParser.parseString(response.body()).getAsJsonArray();
        JsonObject obj = arr.get(0).getAsJsonObject().get("dolar").getAsJsonObject();
        return parseFloat(obj.get("venta").getAsString());
    }

    @CircuitBreaker(name = "dolar", fallbackMethod = "fallback")
    public Float getDolar() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "dolar"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonArray arr = JsonParser.parseString(response.body()).getAsJsonArray();
        JsonObject obj = arr.get(0).getAsJsonObject().get("casa").getAsJsonObject();
        return parseFloat(obj.get("venta").getAsString());
    }

    public Float fallback(final Throwable t) {
        log.error(t.getMessage());
        throw new CurrencyApiException();
    }

    private Float parseFloat(String f) {
        return Float.parseFloat(f.replace(",", "."));
    }
}
