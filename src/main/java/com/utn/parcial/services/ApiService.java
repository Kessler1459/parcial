package com.utn.parcial.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApiService {
    @CircuitBreaker(name = "euro", fallbackMethod = "fallback")
    public Float getEuro() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonArray arr=JsonParser.parseString(response.body()).getAsJsonArray();
        JsonObject obj=arr.get(0).getAsJsonObject().get("dolar").getAsJsonObject();
        return obj.get("venta").getAsFloat();
    }

    @CircuitBreaker(name = "dolar", fallbackMethod = "fallback")
    public Float getDolar()  {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=genedolar&opc=ta"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonArray arr=JsonParser.parseString(response.body()).getAsJsonArray();
        JsonObject obj=arr.get(1).getAsJsonObject().get("casa").getAsJsonObject();
        return obj.get("venta").getAsFloat();
    }


    public Float fallback(final Throwable t) {
        return 0f;
    }
}
