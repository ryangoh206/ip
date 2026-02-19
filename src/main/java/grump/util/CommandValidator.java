package grump.util;

import grump.constants.CommandMessages;
import grump.exception.InvalidArgException;
import grump.exception.MissingArgException;
import javafx.util.Pair;

/**
 * Utility class for common validation operations across command classes. Provides reusable
 * validation methods to reduce code duplication and ensure consistency.
 */
public class CommandValidator {

    /**
     * Validates and parses a task number from user input.
     *
     * @param userInput The user input string containing command and task number.
     * @param maxTasks The maximum number of tasks (for bounds checking).
     * @return The zero-based task index.
     * @throws MissingArgException If task number is missing.
     * @throws InvalidArgException If task number is invalid or out of bounds.
     */
    public static int validateTaskIndex(String userInput, int maxTasks) {
        String[] commandParts = userInput.split(" ", 2);
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new MissingArgException(CommandMessages.MISSING_TASK_NUMBER);
        }

        try {
            int taskNumber = Integer.parseInt(commandParts[1].trim());
            if (taskNumber < 1 || taskNumber > maxTasks) {
                throw new InvalidArgException(CommandMessages.TASK_NUMBER_OUT_OF_BOUNDS);
            }
            return taskNumber - 1; // Convert to zero-based index
        } catch (NumberFormatException e) {
            throw new InvalidArgException(CommandMessages.INVALID_TASK_NUMBER);
        }
    }

    /**
     * Validates and parses a task number and tag from user input.
     *
     * @param userInput The user input string containing command, task number, and tag.
     * @param maxTasks The maximum number of tasks (for bounds checking).
     * @return A Pair containing the zero-based task index and the tag.
     * @throws MissingArgException If task number or tag is missing.
     * @throws InvalidArgException If task number is invalid or out of bounds.
     */
    public static Pair<Integer, String> validateTaskIndexAndTag(String userInput, int maxTasks) {
        String arguments = validateHasArguments(userInput);

        try {
            String[] argumentParts = arguments.split(" ", 2);

            if (argumentParts.length < 2 || argumentParts[1].trim().isEmpty()) {
                throw new MissingArgException(CommandMessages.MISSING_TAG_NAME);
            }
            int taskNumber = Integer.parseInt(argumentParts[0].trim());
            if (taskNumber < 1 || taskNumber > maxTasks) {
                throw new InvalidArgException(CommandMessages.TASK_NUMBER_OUT_OF_BOUNDS);
            }
            return new Pair<>(taskNumber - 1, argumentParts[1].trim());
        } catch (NumberFormatException e) {
            throw new InvalidArgException(CommandMessages.INVALID_TASK_NUMBER);
        }
    }


    /**
     * Validates that user input has sufficient arguments.
     *
     * @param userInput The user input string.
     * @return The arguments part of input (excluding command).
     * @throws MissingArgException If arguments are missing or empty.
     */
    public static String validateHasArguments(String userInput) {
        String[] commandParts = userInput.split(" ", 2);
        if (commandParts.length < 2 || commandParts[1].trim().isEmpty()) {
            throw new MissingArgException(CommandMessages.MISSING_ARGS);
        }
        return commandParts[1].trim();
    }

    /**
     * Validates command parts array for minimum length and non-empty elements.
     *
     * @param parts The array to validate.
     * @param minLength The minimum required length.
     * @param emptyElementIndex Index of element that must not be empty.
     * @param emptyErrorMessage Error message for empty element.
     * @throws MissingArgException If validation fails.
     */
    public static void validateCommandParts(String[] parts, int minLength, int emptyElementIndex,
            String emptyErrorMessage) {
        if (parts.length < minLength) {
            throw new MissingArgException(CommandMessages.MISSING_ARGS);
        }
        if (parts.length <= emptyElementIndex || parts[emptyElementIndex] == null
                || parts[emptyElementIndex].trim().isEmpty()) {
            throw new MissingArgException(emptyErrorMessage);
        }
    }

}
