import java.util.Scanner;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class Grump {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String DATA_FILE_PATH = "data/tasks.csv";

    public static void main(String[] args) {

        Ui ui = new Ui();

        ui.printWelcomeMessage();

        loadFromHardDisk();

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
                    markTaskAsDone(userInput);
                    break;
                case UNMARK:
                    markTaskAsUndone(userInput);
                    break;
                case DELETE:
                    deleteTask(userInput);
                    break;
                case TODO:
                    addTodo(userInput);
                    break;
                case DEADLINE:
                    addDeadline(userInput);
                    break;
                case EVENT:
                    addEvent(userInput);
                    break;
                }
                if (command == Command.TODO || command == Command.DEADLINE
                        || command == Command.EVENT) {
                    ui.printAddedTask(tasks.get(tasks.size() - 1), tasks.size());
                }
                saveToHardDisk();
            } catch (MissingArgException | InvalidCommandException e) {
                ui.printErrorMessage(e.getMessage());
                continue;
            }
        }
        scanner.close();
    }

    public static void markTaskAsUndone(String userInput) {
        try {
            String parts[] = userInput.split(" ");
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to mark as done.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            tasks.get(taskNum).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(taskNum));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task number you provided is invalid.");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        } catch (MissingArgException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void markTaskAsDone(String userInput) {
        try {
            String parts[] = userInput.split(" ");
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to mark as done.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            tasks.get(taskNum).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(taskNum));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("The task number you provided is invalid.");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        } catch (MissingArgException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask(String userInput) {
        try {
            String parts[] = userInput.split(" ");
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to delete.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            Task oldTask = tasks.get(taskNum);
            tasks.remove(taskNum);
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

    public static void addTodo(String userInput) {
        String parts[] = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException("Please provide a description for the todo task.");
        }
        tasks.add(new ToDo(parts[1].trim()));
    }

    public static void addDeadline(String userInput) {
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
        tasks.add(new Deadline(description, by));
    }

    public static void addEvent(String userInput) {
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

        tasks.add(new Event(description, start, end));
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

    // Load tasks from hard disk
    public static void loadFromHardDisk() {
        File dataFile = new File(DATA_FILE_PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 5);
                String taskType = parts[0];
                String description = parts[1];
                boolean isDone = parts[2].equals("1");

                Task task = null;
                switch (taskType) {
                case "T":
                    task = new ToDo(description, isDone);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(parts[3]);
                    task = new Deadline(description, isDone, by);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(parts[3]);
                    LocalDateTime to = LocalDateTime.parse(parts[4]);
                    task = new Event(description, isDone, from, to);
                    break;
                }
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Creating a new data file.");
            // Create data directory and file if not exist
            try {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            } catch (IOException ioException) {
                System.out.println("An error occurred while creating the data directory and file.");
            }

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println(
                    "An error occurred while loading tasks from hard disk. Ensure data file is in correct format.");
        }
    }

    // Write all tasks to hard disk
    public static void saveToHardDisk() {
        File dataFile = new File(DATA_FILE_PATH);
        dataFile.getParentFile().mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
            for (Task task : tasks) {
                bw.write(task.toCsvString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to hard disk.");
        }
    }
}
