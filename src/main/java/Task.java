/**
 * Represents a task and whether it has been completed.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    protected abstract String getTypeSymbol();

    @Override
    public String toString() {
        return "[" + getTypeSymbol() + "][" + (isDone ? "X" : " ") + "] " + description;
    }
}
