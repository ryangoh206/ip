package grump.task;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Retrieves a task at a specific index.
     *
     * @param index Index of the task to retrieve.
     * @return Task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes a task at a specific index.
     *
     * @param index Index of the task to remove.
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return ArrayList of tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        String lowerCaseKeyword = keyword.toLowerCase();

        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(lowerCaseKeyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
