package com.mycompany.smarttasklogger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class IAClient {

    private String apiKey;
    private String apiURL;
    private final Gson gson;

    public IAClient(String apiKey) {
        this.apiKey = apiKey;
        this.apiURL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + apiKey;
        this.gson = new Gson();
    }

    public String getFormattedTask(String userInput) {
        //prompt que le es proporcionado a la IA para crear el texto que se necesita con la informacion dada por el usuario
        try {
            String prompt = "Responde SOLO con este formato: 'DATE | HH:mm | Tarea'. "
                    + "Si no hay hora, pone 00:00. La frase es: " + userInput;

            String jsonBody = "{ \"contents\": [ { \"parts\": [ { \"text\": \"" + prompt + "\" } ] } ] }";

            //configuracion del cliente de internet
            HttpClient client = HttpClient.newHttpClient();

            //http request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiURL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

            return jsonResponse.getAsJsonArray("candidates")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("content")
                    .getAsJsonArray("parts")
                    .get(0).getAsJsonObject()
                    .get("text").getAsString().trim();
        } catch (Exception e) {
            return "Error procesando con la IA: " + e.getMessage();

        }
    }

}
