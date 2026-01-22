import java.util.Scanner;

public class Grump {
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
    
        // Loop and read commands from standard input until 'bye' is encountered
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("____________________________________________________________\n");
            String userInput = scanner.nextLine().trim();
            System.out.println("____________________________________________________________");
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else {
                System.out.println(userInput);
            }
        }
        scanner.close();
    }
}
