package orbit;

import java.util.ArrayList;
import java.util.List;

/**
 * Owns the user's tasks and exposes task-list operations.
 */
public class TaskList {
    private final List<Task> tasks;

    /** Creates an empty task list. */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Creates a task list containing the supplied tasks.
     *
     * @param tasks Initial tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /** @param task Task to append. */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at an index.
     *
     * @param index Zero-based task index.
     * @return Task at the index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes and returns the task at an index.
     *
     * @param index Zero-based task index.
     * @return Removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /** @return Number of tasks. */
    public int size() {
        return tasks.size();
    }

    /** @return Immutable snapshot of the tasks. */
    public List<Task> asList() {
        return List.copyOf(tasks);
    }
}
