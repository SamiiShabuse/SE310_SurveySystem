package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValidator {

    public static boolean validateTF(String input) {
        return !input.equalsIgnoreCase("true") && !input.equalsIgnoreCase("false");
    }

    public static boolean validateDate(String input, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate.parse(input.trim(), formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validateMultipleChoice(String input, int optionCount, boolean multipleAllowed) {
        if (input == null || input.trim().isEmpty()) return false;
        String[] parts = input.split("[,\\s]+");

        for (String part : parts) {
            try {
                int index = Integer.parseInt(part.trim());
                if (index < 1 || index > optionCount) return false;
            } catch (NumberFormatException e) {
                return false;
            }
            if (!multipleAllowed && parts.length > 1) return false;
        }
        return true;
    }

    public static boolean validateMatching(String input) {
        if (input == null || input.isEmpty()) return false;
        return input.contains("=") && input.split("=").length == 2;
    }

    public static boolean validateShortAnswer(String input) {
        return input == null || input.trim().isEmpty();
    }
}
