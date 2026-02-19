package grump;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Entry point of the application. Launches in GUI mode by default, or CLI mode if the first
     * argument is {@code cli}.
     *
     * @param args Command-line arguments. Pass {@code cli} to run in text CLI mode.
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("cli")) {
            new Grump("data/tasks.csv").run();
        } else {
            Application.launch(Main.class, args);
        }
    }
}
