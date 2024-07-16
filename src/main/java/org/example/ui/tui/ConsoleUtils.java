package org.example.ui.tui;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

/**
 * A utility class for handling console input and output.
 */
public class ConsoleUtils {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Gets a choice from the user within a specified range.
     *
     * @param max The maximum value for the choice.
     * @return The user's choice.
     */
    public static int getChoice(int max) {
        return getChoice(0, max);
    }

    /**
     * Gets a choice from the user within a specified range.
     *
     * @param min The minimum value for the choice.
     * @param max The maximum value for the choice.
     * @return The user's choice.
     */
    public static int getChoice(int min, int max) {
        for (; ; ) {
            try {
                System.out.printf("Choose (%d-%d): ", min, max);
                var choice = scanner.nextInt();
                scanner.nextLine();
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

    /**
     * Gets an entry from the user without any validation.
     */
    public static void getEntry() {
        Function<String, String> defaultValidator = (it) -> null;
        getEntry(null, defaultValidator);
    }

    /**
     * Gets an entry from the user with a default validation.
     *
     * @param description The description of the entry.
     * @return The user's entry.
     */
    public static String getEntry(String description) {
        Function<String, String> defaultValidator = (it) -> it.isEmpty() ? "Cannot be empty!" : null;
        return getEntry(description, defaultValidator);
    }

    /**
     * Gets an entry from the user with a custom validation.
     *
     * @param description The description of the entry.
     * @param validator   The validation function.
     * @return The user's entry.
     */
    public static String getEntry(String description, Function<String, String> validator) {
        for (; ; ) {
            if (description != null) {
                System.out.printf("%s: ", description);
            }
            var entry = scanner.nextLine();
            var validation = validator.apply(entry);
            if (validation == null)
                return entry;
            else
                System.out.println(validation);
        }
    }

    /**
     * Gets an entry from the user with a custom validation and selection.
     *
     * @param description The description of the entry.
     * @param selector    The selection function.
     * @param validator   The validation function.
     * @param <R>         The type of the entry.
     * @return The user's entry.
     */
    public static <R> R getEntry(String description, Function<String, R> selector, Function<R, String> validator) {
        for (; ; ) {
            System.out.printf("%s: ", description);
            var entryID = scanner.nextLine();
            var entry = selector.apply(entryID);
            var validation = validator.apply(entry);
            if (validation == null)
                return entry;
            else
                System.out.println(validation);
        }
    }
}
