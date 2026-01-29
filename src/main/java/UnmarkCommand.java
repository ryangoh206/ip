public class UnmarkCommand extends Command {
    private final String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String parts[] = userInput.split(" ");
            if (parts.length < 2) {
                throw new MissingArgException("Please provide the task number to mark as done.");
            }
            int taskNum = Integer.parseInt(parts[1]) - 1;
            Task task = tasks.getTask(taskNum);
            task.markAsNotDone();
            ui.printUnmarkTaskMessage(task);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidArgException("The task number you provided is invalid.");
        }
        return false;
    }

}
