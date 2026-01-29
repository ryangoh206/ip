package grump.command;

import grump.task.TaskList;
import grump.storage.Storage;
import grump.ui.Ui;

public abstract class Command {

    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage);

}
