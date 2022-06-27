package kz.halykacademy;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter correct name");
        String name = sc.nextLine();

        String body = "{\"name\":\"" + name + "\",\"salary\":\"123\",\"age\":\"23\"}";

        try {
            //Подготовка Http запроса
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(new URI("http://dummy.restapiexample.com/api/v1/create"))
                            .headers("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(body))
                            .build();

            HttpClient client = HttpClient.newHttpClient();

            // Отправка Http ответа
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // Вывод ответа в консоль
            if (response.statusCode() == 200) {
                System.out.println(response.body());
            } else {
                String errorBody = "\"statusCode\":" + response.statusCode() + "\", \"status\":\"error\"";
                System.out.println(errorBody);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
