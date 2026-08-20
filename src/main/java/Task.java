/**
 * Represents a task and whether it has been completed.
 */
public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
