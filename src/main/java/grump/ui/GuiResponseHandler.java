package grump.ui;

import grump.task.Task;
import grump.task.TaskList;

/**
 * Handles responses for the GUI application.
 */
public class GuiResponseHandler {
    /**
     * Returns the goodbye message.
     */
    public String returnGoodbyeMessage() {
        return "Bye. Hope to see you again soon!\n____________________________________________________________";
    }

    /**
     * Returns the list of tasks formatted.
     *
     * @param tasks The TaskList containing the tasks to be printed.
     */
    public String returnTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            return "You have no tasks in your list.";
        } else {
            String responseString = "Here are the tasks in your list:\n" + concatAllTasks(tasks);
            return responseString;
        }
    }

    /**
     * Returns the message for when a task is added.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks after addition.
     */
    public String returnAddedTask(Task task, int totalTasks) {
        return "Got it. I've added this task:\n  " + task + "\n Now you have " + totalTasks
                + " tasks in the list.";
    }

    /**
     * Returns the error message provided.
     *
     * @param message The error message to be printed.
     */
    public String returnErrorMessage(String message) {
        return message;
    }

    /**
     * Returns the message for when a task is marked as done.
     *
     * @param task The task that was marked.
     */
    public String returnMarkTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Returns the message for when a task is unmarked as not done.
     *
     * @param task The task that was unmarked.
     */
    public String returnUnmarkTaskMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Returns the message for when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param totalTasks The total number of tasks after deletion.
     */
    public String returnDeletedTask(Task task, int totalTasks) {
        return "Noted! I've deleted this task:\n  " + task + "\nNow you have " + totalTasks
                + " tasks in the list.";
    }

    /**
     * Concatenates all tasks into a single formatted string.
     *
     * @param tasks The TaskList containing the tasks to be concatenated.
     */
    private String concatAllTasks(TaskList tasks) {
        String concatString = "";
        for (int i = 0; i < tasks.size(); i++) {
            concatString += (i + 1) + ". " + tasks.getTask(i).toString() + "\n";
        }
        return concatString;
    }

}
