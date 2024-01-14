package HttpChecker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HttpImageStatusCli {

    public static void askStatus() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter HTTP status code");
            if (scanner.hasNextInt()) {
                int code = scanner.nextInt();
                HttpStatusImageDownloader.downloadStatusImage(code);
            } else {
                System.out.println("Please enter a valid number");
            }
        } catch (InputMismatchException ex) {
            System.out.println("Please enter a valid number");
        }
    }
}
