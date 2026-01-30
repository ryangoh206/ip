package grump.task;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public abstract String toCsvString();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string in the format "[statusIcon] description".
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

}
