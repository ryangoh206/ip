package grump.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Text UI of the application. Not used now, but to support CLI if necessary.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message with the current date.
     */
    public void printWelcomeMessage() {
        String logo = "  ____ ____  _   _ __  __ ____  \n" + " / ___|  _ \\| | | |  \\/  |  _ \\ \n"
                + "| |  _| |_) | | | | |\\/| | |_) |\n" + "| |_| |  _ <| |_| | |  | |  __/ \n"
                + " \\____|_| \\_\\\\___/|_|  |_|_|    \n";
        this.printLine();
        System.out.println();
        System.out.println(logo);
        this.printLine();

        System.out.println("Hello! I'm Grump! The date today is: "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + ".\nWhat can I do for you?");
    }

    /**
     * Prints the goodbye message and closes the scanner.
     */
    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        this.scanner.close();
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
        System.out.println("____________________________________________________________");
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
