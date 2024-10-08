package tracker.utils;

import java.util.Scanner;

public class ScannerUtils {
    private static Scanner SCANNER;

    private ScannerUtils() {
    }

    public static Scanner getScannerInstance() {
        if (SCANNER == null) {
            SCANNER = new Scanner(System.in);
        }
        return SCANNER;
    }
}
