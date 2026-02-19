package grump.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Text UI of the application. Not used now, but to support CLI if necessary.
 */
public class Ui {
    private static final String LOGO = "  ____ ____  _   _ __  __ ____  \n"
            + " / ___|  _ \\| | | |  \\/  |  _ \\ \n" + "| |  _| |_) | | | | |\\/| | |_) |\n"
            + "| |_| |  _ <| |_| | |  | |  __/ \n" + " \\____|_| \\_\\\\___/|_|  |_|_|    \n";
    private static final String DATE_FORMAT_PATTERN = "dd MMM yyyy";
    private static final String MESSAGE_WELCOME = "Hello! I'm Grump! The date today is: ";
    private static final String MESSAGE_WHAT_CAN_I_DO = ".\nWhat can I do for you?";
    private static final String LINE_SEPARATOR =
            "____________________________________________________________";

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message with the current date.
     */
    public void printWelcomeMessage() {
        this.printLine();
        System.out.println();
        System.out.println(LOGO);
        this.printLine();

        System.out.println(MESSAGE_WELCOME
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN))
                + MESSAGE_WHAT_CAN_I_DO);
    }

    /**
     * Prints a newline for better readability.
     */
    public void printNewline() {
        System.out.println();
    }

    /**
     * Reads a command from the user.
     *
     * @return The command (full line) entered by the user.
     */
    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

    /**
     * Prints a horizontal line for better readability.
     */
    public void printLine() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints the message provided.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the error message provided.
     *
     * @param message The error message to be printed.
     */
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

}
