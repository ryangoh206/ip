package grump.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import grump.task.Deadline;
import grump.task.Event;
import grump.task.Task;
import grump.task.TaskList;
import grump.task.ToDo;

/**
 * Handles loading and saving of tasks to and from the hard disk.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * Loads tasks from the data file on the hard disk.
     *
     * @return An ArrayList of Task objects loaded from the data file.
     */
    public ArrayList<Task> load() {
        File dataFile = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 5);
                String taskType = parts[0];
                String description = parts[1];
                boolean isDone = parts[2].equals("1");

                Task task;
                switch (taskType) {
                case "T":
                    task = new ToDo(description, isDone);
                    break;
                case "D":
                    LocalDateTime by = LocalDateTime.parse(parts[3]);
                    task = new Deadline(description, isDone, by);
                    break;
                case "E":
                    LocalDateTime from = LocalDateTime.parse(parts[3]);
                    LocalDateTime to = LocalDateTime.parse(parts[4]);
                    task = new Event(description, isDone, from, to);
                    break;
                default:
                    task = null;
                    throw new IOException("Corrupted task type in storage: " + taskType);
                }
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Creating a new data file.");
            // Create data directory and file if not exist
            try {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            } catch (IOException ioException) {
                System.out.println("An error occurred while creating the data directory and file.");
            }

        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.out.println(
                    "An error occurred while loading tasks from hard disk. Ensure data file is in correct format.");
        }
        return tasks;
    }

    /**
     * Saves the given TaskList to the data file on the hard disk.
     *
     * @param tasks The TaskList to be saved.
     */
    public void save(TaskList tasks) {
        assert tasks != null : "TaskList to save should not be null";
        File dataFile = new File(this.filePath);
        dataFile.getParentFile().mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
            for (Task task : tasks.getTasks()) {
                assert task != null : "Task in TaskList should not be null";
                bw.write(task.toCsvString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to hard disk.");
        }
    }
}
