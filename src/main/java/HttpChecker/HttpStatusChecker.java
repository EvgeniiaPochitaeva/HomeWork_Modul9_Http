package HttpChecker;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpStatusChecker {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final String URL =  "https://http.cat";

    public static String getStatusImage(int code) {
        StringBuilder response = new StringBuilder();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL + "/" + code + ".jpg"))
                    .GET()
                    .build();
            HttpResponse<String> result = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            int codeResponse = result.statusCode();
            if (codeResponse != 200) {
                System.out.println("There is no image for HTTP-status " + code);
            } else {
                response.append(result.uri());
                System.out.println("Image URL for HTTP-status " + code + ": " + response);
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}

