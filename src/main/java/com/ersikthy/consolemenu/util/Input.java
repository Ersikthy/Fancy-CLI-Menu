package com.ersikthy.consolemenu.util;

import java.util.Scanner;

import static com.ersikthy.consolemenu.util.Output.*;

public class Input {
    private Input() {
    }

    public static String readStr(Scanner scanner, String message) {
        while (true) {
            print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            error("Input can't be empty");
        }
    }

    public static int readInt(Scanner scanner, String message) {
        while (true) {
            try {
                print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                error("Please enter a valid number!");
            }
        }
    }

    public static double readDouble(Scanner scanner, String message) {
        while (true) {
            try {
                print(message);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                error("Please enter a valid double value!");
            }
        }
    }

    public static Long readLong(Scanner scanner, String message) {
        while (true) {
            try {
                print(message);
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                error("Please enter a valid long value!");
            }
        }
    }

    public static Float readFloat(Scanner scanner, String message) {
        while (true) {
            try {
                print(message);
                return Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                error("Please enter a valid float value!");
            }
        }
    }
}
