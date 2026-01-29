import grump.command.Command;
import grump.exception.InvalidArgException;
import grump.exception.InvalidCommandException;
import grump.exception.MissingArgException;
import grump.parser.Parser;
import grump.storage.Storage;
import grump.task.TaskList;
import grump.ui.Ui;

public class Grump {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public Grump(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;

        // Loop, read commands from stdin and add to tasks until 'bye'
        while (!isExit) {
            ui.printLine();
            ui.printNewline();
            String userInput = ui.readCommand();
            ui.printLine();

            try {
                Command command = Parser.parseCommand(userInput);
                isExit = command.execute(tasks, ui, storage);
            } catch (MissingArgException | InvalidCommandException | InvalidArgException e) {
                ui.printErrorMessage(e.getMessage());
                continue;
            }
        }

    }

    public static void main(String[] args) {
        new Grump("data/tasks.csv").run();
    }

}
