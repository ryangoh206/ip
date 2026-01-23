import java.util.Scanner;
import java.util.ArrayList;

public class Grump {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = "  ____ ____  _   _ __  __ ____  \n"
                + " / ___|  _ \\| | | |  \\/  |  _ \\ \n"
                + "| |  _| |_) | | | | |\\/| | |_) |\n"
                + "| |_| |  _ <| |_| | |  | |  __/ \n"
                + " \\____|_| \\_\\\\___/|_|  |_|_|    \n";
        System.out.println("____________________________________________________________\n");
        System.out.println(logo);
        System.out.println("____________________________________________________________");

        System.out.println("Hello! I'm Grump\nWhat can I do for you?");

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
                String parts[] = userInput.split(" ");
                int taskNum = Integer.parseInt(parts[1]) - 1;
                tasks.get(taskNum).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(taskNum));
            } else if (userInput.startsWith("unmark")) {
                String parts[] = userInput.split(" ");
                int taskNum = Integer.parseInt(parts[1]) - 1;
                tasks.get(taskNum).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(taskNum));
            } else {
                tasks.add(new Task(userInput));
                System.out.println("added: " + userInput);
            }
        }
        scanner.close();
    }

    // Print all tasks with numbering
    public static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
