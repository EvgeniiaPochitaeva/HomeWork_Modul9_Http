package HttpChecker;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class HttpStatusImageDownloader {
    static HttpStatusChecker httpStatusChecker = new HttpStatusChecker();

    public static void downloadStatusImage(int code) throws HttpException {
        try {
            String uri = httpStatusChecker.getStatusImage(code);

            InputStream inputStream = new URL(uri).openStream();
            Path imagePath = Paths.get("images", "status_" + code + ".png");
            Files.createDirectories(imagePath.getParent());
            Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
        } catch (IOException e) {
            throw new HttpException("Error downloading image for HTTP-status " + code, e);
        }
    }
}

