public class GoodbyeCommand extends Command {

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbyeMessage();
        return true; // Indicate that the application should exit
    }

}
