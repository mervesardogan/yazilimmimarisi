import java.util.Scanner;

public class ScannerSingleton {
    private static Scanner scannerInstance;

    // Private constructor, dışarıdan doğrudan oluşturulmasını önler
    private ScannerSingleton() {
        // Boş constructor
    }
    public static Scanner getInstance() {
        if (scannerInstance == null) {
            synchronized (ScannerSingleton.class) {
                // Double-Checked Locking
                if (scannerInstance == null) {
                    scannerInstance = new Scanner(System.in);
                }
            }
        }
        return scannerInstance;
    }

    // Singleton örneğini kapatmak için bir metot
    public static void closeScanner() {
        if (scannerInstance != null) {
            scannerInstance.close();
            scannerInstance = null;
        }
    }
}
