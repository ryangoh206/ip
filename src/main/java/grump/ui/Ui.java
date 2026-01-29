package grump.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import grump.task.Task;
import grump.task.TaskList;

public class Ui {
    Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

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

    public void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        this.scanner.close();
    }

    public void printNewline() {
        System.out.println();
    }

    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

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

    public void printAddedTask(Task task, int totalTasks) {
        System.out.println("Got it. I've added this task:\n  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printMarkTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void printUnmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void printDeletedTask(Task task, int totalTasks) {
        System.out.println("Noted! I've deleted this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }

}
