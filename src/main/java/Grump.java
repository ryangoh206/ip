import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class Grump {

    public static void main(String[] args) {

        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks.csv");

        ui.printWelcomeMessage();

        TaskList tasks = new TaskList(storage.load());

        // Loop, read commands from stdin and add to tasks until 'bye'
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            ui.printLine();
            System.out.println();
            String userInput = scanner.nextLine().trim();
            ui.printLine();

            try {
                String parts[] = userInput.split(" ", 2);
                Command command = Command.parse(parts[0]);

                switch (command) {
                case BYE:
                    ui.printGoodbyeMessage();
                    isExit = true;
                    break;
                case LIST:
                    ui.printTasks(tasks);
                    break;
                case MARK:
                    markTaskAsDone(userInput, tasks);
                    break;
                case UNMARK:
                    markTaskAsUndone(userInput, tasks);
                    break;
                case DELETE:
                    deleteTask(userInput, tasks);
                    break;
                case TODO:
                    addTodo(userInput, tasks);
                    break;
                case DEADLINE:
                    addDeadline(userInput, tasks);
                    break;
                case EVENT:
                    addEvent(userInput, tasks);
                    break;
                }
                if (command == Command.TODO || command == Command.DEADLINE
                        || command == Command.EVENT) {
                    ui.printAddedTask(tasks.getTask(tasks.size() - 1), tasks.size());
                }
                storage.save(tasks);
            } catch (MissingArgException | InvalidCommandException e) {
                ui.printErrorMessage(e.getMessage());
                continue;
            }
        }
        scanner.close();
    }

    public static void markTaskAsUndone(String userInput, TaskList tasks) {
        try {
            String parts[] = userInput.split(" ");
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to mark as done.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            tasks.getTask(taskNum).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.getTask(taskNum));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task number you provided is invalid.");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        } catch (MissingArgException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void markTaskAsDone(String userInput, TaskList tasks) {
        try {
            String parts[] = userInput.split(" ");
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to mark as done.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            tasks.getTask(taskNum).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.getTask(taskNum));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task number you provided is invalid.");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        } catch (MissingArgException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask(String userInput, TaskList tasks) {
        try {
            String parts[] = userInput.split(" ");
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to delete.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            Task oldTask = tasks.getTask(taskNum);
            tasks.removeTask(taskNum);
            System.out.println("Noted! I've deleted this task:");
            System.out.println("  " + oldTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task number you provided is invalid.");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
            return;
        } catch (MissingArgException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void addTodo(String userInput, TaskList tasks) {
        String parts[] = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException("Please provide a description for the todo task.");
        }
        tasks.addTask(new ToDo(parts[1].trim()));
    }

    public static void addDeadline(String userInput, TaskList tasks) {
        String parts[] = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException("Please provide valid arguments for the deadline task.");
        }
        parts = parts[1].split("/by", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException(
                    "Please provide a valid by date/time for the deadline task.");
        } else if (parts[0].trim().isEmpty()) {
            throw new MissingArgException("Please provide a description for the deadline task.");
        }
        String description = parts[0].trim();
        LocalDateTime by = parseStringToDateTime(parts[1].trim());
        tasks.addTask(new Deadline(description, by));
    }

    public static void addEvent(String userInput, TaskList tasks) {
        String parts[] = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException("Please provide valid arguments for the event task.");
        }
        parts = parts[1].split("/from", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException(
                    "Please provide the start and end date/time for the event task.");
        } else if (parts[0].trim().isEmpty()) {
            throw new MissingArgException("Please provide a description for the event task.");
        }
        String description = parts[0].trim();
        parts = parts[1].split("/to", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException("Please provide the end date/time for the event task.");
        } else if (parts[0].trim().isEmpty()) {
            throw new MissingArgException("Please provide the start date/time for the event task.");
        }
        LocalDateTime start = parseStringToDateTime(parts[0].trim());
        LocalDateTime end = parseStringToDateTime(parts[1].trim());

        tasks.addTask(new Event(description, start, end));
    }

    public static LocalDateTime parseStringToDateTime(String dateTimeStr) {
        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy")
                    .optionalStart().appendPattern(" HH:mm").optionalEnd()
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).toFormatter();

            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new MissingArgException(
                    "Please provide date and time in the format dd-MM-yyyy HH:mm or dd-MM-yyyy.");
        }
    }

}
