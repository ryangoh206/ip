package grump.ui;

import grump.task.Task;
import grump.task.TaskList;

/**
 * Handles responses for the GUI application.
 */
public class GuiResponseHandler {
    private static final String LINE_SEPARATOR =
            "____________________________________________________________";

    /**
     * Returns the goodbye message.
     *
     * @return A goodbye message string.
     */
    public String returnGoodbyeMessage() {
        return "Bye. Hope to see you again soon!\n" + LINE_SEPARATOR;
    }

    /**
     * Returns the list of tasks formatted.
     *
     * @param tasks The TaskList containing the tasks to be printed.
     * @return A formatted string listing all tasks.
     */
    public String returnTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            return "You have no tasks in your list.";
        }
        return "Here are the tasks in your list:\n" + concatAllTasks(tasks);
    }

    /**
     * Returns the message for when a task is added.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks after addition.
     * @return A formatted string confirming the task was added.
     */
    public String returnAddedTask(Task task, int totalTasks) {
        String taskWord = totalTasks == 1 ? "task" : "tasks";
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + totalTasks + " "
                + taskWord + " in the list.";
    }

    /**
     * Returns the error message provided.
     *
     * @param message The error message to be printed.
     * @return The error message string.
     */
    public String returnErrorMessage(String message) {
        return message;
    }

    /**
     * Returns the message for when a task is marked as done.
     *
     * @param task The task that was marked.
     * @return A formatted string confirming the task was marked done.
     */
    public String returnMarkTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Returns the message for when a task is unmarked as not done.
     *
     * @param task The task that was unmarked.
     * @return A formatted string confirming the task was marked as not done.
     */
    public String returnUnmarkTaskMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Returns the message for when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param totalTasks The total number of tasks after deletion.
     * @return A formatted string confirming the task was deleted.
     */
    public String returnDeletedTask(Task task, int totalTasks) {
        String taskWord = totalTasks == 1 ? "task" : "tasks";
        return "Noted! I've deleted this task:\n  " + task + "\nNow you have " + totalTasks + " "
                + taskWord + " in the list.";
    }

    /**
     * Returns the message for when a task is tagged.
     *
     * @param task The task that was tagged.
     * @param tag The tag that was added.
     * @return A formatted string confirming the tag was added.
     */
    public String returnTaggedTaskMessage(Task task, String tag) {
        return "Great! I've added the tag '" + tag + "' to the task:\n  " + task;
    }

    /**
     * Returns the message for when a task is untagged.
     *
     * @param task The task that was untagged.
     * @param tag The tag that was removed.
     * @param isUntagged Whether the tag was successfully removed.
     * @return A formatted string reflecting the result of the untag operation.
     */
    public String returnUntaggedTaskMessage(Task task, String tag, boolean isUntagged) {
        if (isUntagged) {
            return "Great! I've removed the tag '" + tag + "' from the task:\n  " + task;
        } else {
            return "Good news! The task does not have the tag '" + tag
                    + "', so no changes made.\n  " + task;
        }
    }

    /**
     * Concatenates all tasks into a single formatted string.
     *
     * @param tasks The TaskList containing the tasks to be concatenated.
     * @return A numbered string of all tasks.
     */
    private String concatAllTasks(TaskList tasks) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.getTask(i).toString()).append("\n");
        }
        return result.toString();
    }

}
