package grump.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import grump.task.Task;
import grump.task.TaskList;

/**
 * Text UI of the application.
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
     * Prints the list of tasks formatted.
     *
     * @param tasks The TaskList containing the tasks to be printed.
     */
    public void printTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.getTask(i).toString());
            }
        }
    }

    /**
     * Prints the message for when a task is added.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks after addition.
     */
    public void printAddedTask(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Prints a horizontal line for better readability.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the error message provided.
     *
     * @param message The error message to be printed.
     */
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints the message for when a task is marked as done.
     *
     * @param task The task that was marked.
     */
    public void printMarkTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Prints the message for when a task is unmarked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void printUnmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Prints the message for when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param totalTasks The total number of tasks after deletion.
     */
    public void printDeletedTask(Task task, int totalTasks) {
        System.out.println("Noted! I've deleted this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

}
