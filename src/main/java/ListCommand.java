public class ListCommand extends Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasks(tasks);
        return false; // Indicate that the application should continue running
    }
}
