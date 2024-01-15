package HttpChecker;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpStatusChecker {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final String URL =  "https://http.cat";

    public static String getStatusImage(int code) throws HttpException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL + "/" + code + ".jpg"))
                    .GET()
                    .build();
            HttpResponse<String> result = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            int codeResponse = result.statusCode();
            if (codeResponse != 200) {
                throw new HttpException("There is no image for HTTP status " + code);
            }

            return result.uri().toString();
        } catch (IOException | InterruptedException e) {
            throw new HttpException("Error fetching status image for HTTP status " + code, e);
        }
    }
}

