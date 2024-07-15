package org.example.ui.tui;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

public class ConsoleUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getChoice(int max) {
        return getChoice(0, max);
    }

    public static int getChoice(int min, int max) {
        for (; ; ) {
            try {
                System.out.printf("Choose (%d-%d): ", min, max);
                var choice = scanner.nextInt();
                if (choice < min || choice > max)
                    System.out.println("Invalid choice.");
                else
                    return choice;
            } catch (InputMismatchException e) {
                System.out.println("Invalid entry.");
                scanner.nextLine();
            }
        }
    }

    public static void getEntry() {
        Function<String, String> defaultValidator = (it) -> null;
        getEntry(null, defaultValidator);
    }

    public static String getEntry(String description) {
        Function<String, String> defaultValidator = (it) -> it.isEmpty() ? "Cannot be empty!" : null;
        return getEntry(description, defaultValidator);
    }

    public static String getEntry(String description, Function<String, String> validator) {
        for (; ; ) {
            if (description != null) {
                System.out.printf("%s: ", description);
            }
            var entry = scanner.next();
            var validation = validator.apply(entry);
            if (validation == null)
                return entry;
            else
                System.out.println(validation);
        }
    }

    public static <R> R getEntry(String description, Function<String, R> selector, Function<R, String> validator) {
        for (; ; ) {
            System.out.printf("%s: ", description);
            var entryID = scanner.next();
            var entry = selector.apply(entryID);
            var validation = validator.apply(entry);
            if (validation == null)
                return entry;
            else
                System.out.println(validation);
        }
    }
}
