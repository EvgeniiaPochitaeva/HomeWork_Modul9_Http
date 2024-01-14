package HttpChecker;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class HttpStatusImageDownloader {
    static HttpStatusChecker  httpStatusChecker= new HttpStatusChecker();

    public static void downloadStatusImage(int code) {
        try {
            String uri = httpStatusChecker.getStatusImage(code);
            if (uri.isEmpty()) {
                return;
            }

            InputStream inputStream = new URL(uri).openStream();
            Path imagePath = Paths.get("images", "status_" + code + ".png");
            Files.createDirectories(imagePath.getParent());
            Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
