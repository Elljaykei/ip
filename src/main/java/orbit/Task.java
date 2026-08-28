package orbit;

/**
 * Represents a task and whether it has been completed.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates an incomplete task.
     *
     * @param description Human-readable task description.
     */
    protected Task(String description) {
        this.description = description;
    }

    /** Marks this task as completed. */
    public void markAsDone() {
        isDone = true;
    }

    /** Marks this task as incomplete. */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns whether this task has been completed.
     *
     * @return {@code true} if completed.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Converts this task to its persistent text representation.
     *
     * @return Serialized task fields.
     */
    public String toDataString() {
        return getType().getSymbol() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    protected String getDescription() {
        return description;
    }

    protected abstract TaskType getType();

    @Override
    public String toString() {
        return "[" + getType().getSymbol() + "][" + getStatusIcon() + "] " + description;
    }
}
