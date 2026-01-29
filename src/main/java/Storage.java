import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

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

                Task task = null;
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

    public void save(TaskList tasks) {
        File dataFile = new File(this.filePath);
        dataFile.getParentFile().mkdirs();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
            for (Task task : tasks.getTasks()) {
                bw.write(task.toCsvString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to hard disk.");
        }
    }
}
