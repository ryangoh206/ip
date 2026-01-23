import java.util.Scanner;
import java.util.ArrayList;

public class Grump {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        printWelcomeMessage();

        // Loop, read commands from stdin and add to tasks until 'bye'
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("____________________________________________________________\n");
            String userInput = scanner.nextLine().trim();
            System.out.println("____________________________________________________________");
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                printTasks();
            } else if (userInput.startsWith("mark")) {
                markTaskAsDone(userInput);
            } else if (userInput.startsWith("unmark")) {
                markTaskAsUndone(userInput);
            } else if (userInput.startsWith("delete")) {
                deleteTask(userInput);
            } else {
                String parts[] = userInput.split(" ", 2);
                try {
                    String command = parts[0];
                    if (command.equals("todo")) {
                        addTodo(userInput);
                    } else if (command.equals("deadline")) {
                        addDeadline(userInput);
                    } else if (command.equals("event")) {
                        addEvent(userInput);
                    } else {
                        throw new InvalidCommandException("I'm sorry, but I don't know what that means.");
                    }
                } catch (MissingArgException | InvalidCommandException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                
                System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } 
        }
        scanner.close();
    }

    // Print Welcome Message
    public static void printWelcomeMessage() {
        String logo = "  ____ ____  _   _ __  __ ____  \n"
                + " / ___|  _ \\| | | |  \\/  |  _ \\ \n"
                + "| |  _| |_) | | | | |\\/| | |_) |\n"
                + "| |_| |  _ <| |_| | |  | |  __/ \n"
                + " \\____|_| \\_\\\\___/|_|  |_|_|    \n";
        System.out.println("____________________________________________________________\n");
        System.out.println(logo);
        System.out.println("____________________________________________________________");

        System.out.println("Hello! I'm Grump\nWhat can I do for you?");
    }

    // Print all tasks with numbering
    public static void printTasks() {
        if (tasks.size() == 0) {
            System.out.println("You have no tasks in your list.");
            return;
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
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
            throw new MissingArgException("Please provide a valid by date/time for the deadline task.");
        } else if (parts[0].trim().isEmpty()) {
            throw new MissingArgException("Please provide a description for the deadline task.");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        tasks.add(new Deadline(description, by));
    }
    
    public static void addEvent(String userInput) {
        String parts[] = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException("Please provide valid arguments for the event task.");
        }
        parts = parts[1].split("/from", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new MissingArgException("Please provide the start and end date/time for the event task.");
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
        String start = parts[0].trim();
        String end = parts[1].trim();
        tasks.add(new Event(description, start, end));
    }
}
