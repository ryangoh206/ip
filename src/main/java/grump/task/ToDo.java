package grump.task;

import java.util.ArrayList;

/**
 * Represents a To-Do type task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public ToDo(String description, ArrayList<String> tags, boolean isDone) {
        super(description, tags, isDone);
    }

    @Override
    public String toCsvString() {
        return "T," + description + "," + (isDone ? "1" : "0") + "," + tags.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
