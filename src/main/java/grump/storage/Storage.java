package grump.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import grump.task.Deadline;
import grump.task.Event;
import grump.task.Task;
import grump.task.TaskList;
import grump.task.ToDo;

/**
 * Handles loading and saving of tasks to and from the hard disk.
 */
public class Storage {
    private static final String DELIMITER = ",";
    private static final String DELIMITER_TAG = ",\\[";
    private static final int MAX_PARTS = 5;
    private static final String DONE_FLAG = "1";

    private static final int POSITION_TASK_TYPE = 0;
    private static final int POSITION_DESCRIPTION = 1;
    private static final int POSITION_DONE_FLAG = 2;
    private static final int POSITION_BY = 3;
    private static final int POSITION_FROM = 3;
    private static final int POSITION_TO = 4;

    private static final String TODO = "T";
    private static final String DEADLINE = "D";
    private static final String EVENT = "E";

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
        ensureFileExists();

        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                tasks.add(task);
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
                writeToBufferedWriter(bw, task);
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to hard disk.");
        }
    }

    /**
     * Ensures that the data file exists; if not, creates a new file.
     */
    private void ensureFileExists() {
        File dataFile = new File(this.filePath);
        if (!dataFile.exists()) {
            System.out.println("Data file not found. Creating a new data file.");
            try {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the data directory and file.");
            }
        }
    }

    /**
     * Parses a line from the data file and creates the corresponding Task object.
     *
     * @param line The line from the data file.
     * @return The Task object represented by the line.
     * @throws IOException If the line is in an invalid format.
     */
    private Task parseTask(String line) throws IOException {
        // Extract tags from the line first as it is at the end and may contain commas
        String[] tempParts = line.split(DELIMITER_TAG, 2);
        assert tempParts.length > 1 : "Line should contain '[' to indicate start of tags";
        line = tempParts[0];

        String[] parts = line.split(DELIMITER, MAX_PARTS);

        String taskType = parts[POSITION_TASK_TYPE];
        String description = parts[POSITION_DESCRIPTION];
        String tagsString = tempParts[1].replace("]", "");

        ArrayList<String> tagArrayList = Arrays.stream(tagsString.split(",")).map(String::trim)
                .filter(tag -> !tag.isEmpty()).collect(Collectors.toCollection(ArrayList::new));


        boolean isDone = DONE_FLAG.equals(parts[POSITION_DONE_FLAG]);

        switch (taskType) {
        case TODO:
            return new ToDo(description, tagArrayList, isDone);
        case DEADLINE:
            LocalDateTime by = LocalDateTime.parse(parts[POSITION_BY]);
            return new Deadline(description, tagArrayList, isDone, by);
        case EVENT:
            LocalDateTime from = LocalDateTime.parse(parts[POSITION_FROM]);
            LocalDateTime to = LocalDateTime.parse(parts[POSITION_TO]);
            return new Event(description, tagArrayList, isDone, from, to);
        default:
            throw new IOException("Invalid task type in data file.");
        }
    }

    /**
     * Writes a single task to the BufferedWriter in CSV format.
     *
     * @param bw The BufferedWriter to write to.
     * @param task The Task to be written.
     * @throws IOException If an I/O error occurs.
     */
    private void writeToBufferedWriter(BufferedWriter bw, Task task) throws IOException {
        bw.write(task.toCsvString());
        bw.newLine();
    }

}
