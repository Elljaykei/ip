package orbit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads and saves Orbit tasks in a local text file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates storage backed by the given relative file path.
     *
     * @param filePath Path of the task data file.
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Loads all tasks from disk, returning an empty list if the file is absent.
     *
     * @return Loaded tasks.
     * @throws IOException If the data file cannot be read.
     * @throws OrbitException If saved task data is invalid.
     */
    public List<Task> load() throws IOException, OrbitException {
        if (Files.notExists(filePath)) {
            return new ArrayList<>();
        }
        List<Task> tasks = new ArrayList<>();
        for (String line : Files.readAllLines(filePath)) {
            tasks.add(parseTask(line));
        }
        return tasks;
    }

    /**
     * Replaces the data file with the current task list.
     *
     * @param tasks Tasks to persist.
     * @throws IOException If the tasks cannot be written.
     */
    public void save(List<Task> tasks) throws IOException {
        Path parent = filePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.write(filePath, tasks.stream().map(Task::toDataString).toList());
    }

    private Task parseTask(String line) throws OrbitException {
        String[] fields = line.split(" \\| ", -1);
        if (fields.length < 3) {
            throw new OrbitException("Invalid saved task: " + line);
        }
        Task task;
        switch (fields[0]) {
            case "T":
                task = new Todo(fields[2]);
                break;
            case "D":
                task = new Deadline(fields[2], fields[3]);
                break;
            case "E":
                task = new Event(fields[2], fields[3], fields[4]);
                break;
            default:
                throw new OrbitException("Unknown saved task type: " + fields[0]);
        }
        if (fields[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
