package grump.task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents an abstract task with a description and completion status.
 */
public abstract class Task {
    protected static final String DONE_ICON = "X";
    protected static final String NOT_DONE_ICON = " ";
    protected static final String CSV_DONE_FLAG = "1";
    protected static final String CSV_NOT_DONE_FLAG = "0";
    protected static final String CSV_DELIMITER = ",";
    protected static final DateTimeFormatter DISPLAY_DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    protected String description;
    protected ArrayList<String> tags;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.tags = new ArrayList<>();
        this.isDone = false;
    }

    /**
     * Constructs a Task with the given description and completion status.
     *
     * @param description Description of the task.
     * @param isDone Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.tags = new ArrayList<>();
        this.isDone = isDone;
    }

    /**
     * Constructs a Task with the given description, tags, and completion status.
     *
     * @param description Description of the task.
     * @param tags List of tags associated with the task.
     * @param isDone Whether the task is done.
     */
    public Task(String description, ArrayList<String> tags, boolean isDone) {
        this.description = description;
        this.tags = tags;
        this.isDone = isDone;
    }

    public abstract String toCsvString();

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? DONE_ICON : NOT_DONE_ICON);
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
     * Returns whether the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Adds a tag to the task.
     *
     * @param tag The tag to be added.
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Removes a tag from the task.
     *
     * @param tag The tag to be removed.
     * @return true if the tag was successfully removed, false otherwise.
     */
    public boolean removeTag(String tag) {
        return this.tags.remove(tag);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string in the format "[statusIcon] description (Tags: [tag1, tag2, ...])".
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription() + " (Tags: "
                + this.tags.toString() + ")";
    }

}
