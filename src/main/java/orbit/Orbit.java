package orbit;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Orbit is a command-line chatbot that keeps track of a user's tasks.
 */
public class Orbit {
    private static final String DATA_FILE_PATH = "data/orbit.txt";

    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    /**
     * Creates an Orbit chatbot that reads commands from the supplied scanner.
     *
     * @param scanner Source of user commands.
     */
    public Orbit(Scanner scanner) {
        this.ui = new Ui(scanner);
        this.parser = new Parser();
        this.storage = new Storage(DATA_FILE_PATH);
        List<Task> loadedTasks;
        try {
            loadedTasks = storage.load();
        } catch (IOException | OrbitException e) {
            loadedTasks = List.of();
            System.out.println("OOPS!!! Could not load saved tasks: " + e.getMessage());
        }
        this.tasks = new TaskList(loadedTasks);
    }

    /**
     * Starts the chatbot and processes commands until the user enters {@code bye}.
     */
    public void run() {
        showWelcome();
        while (ui.hasNextCommand()) {
            String input = ui.readCommand();
            try {
                CommandType command = parser.parse(input);
                if (command == CommandType.BYE) {
                    showGoodbye();
                    return;
                }
                execute(command, input);
            } catch (OrbitException e) {
                showMessage("OOPS!!! " + e.getMessage());
            }
        }
    }

    private void execute(CommandType command, String input) throws OrbitException {
        switch (command) {
        case LIST:
            showList();
            break;
        case MARK:
            updateStatus(input, true);
            break;
        case UNMARK:
            updateStatus(input, false);
            break;
        case TODO:
            addTodo(input);
            break;
        case DEADLINE:
            addDeadline(input);
            break;
        case EVENT:
            addEvent(input);
            break;
        case DELETE:
            deleteTask(input);
            break;
        default:
            throw new OrbitException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private void showWelcome() {
        ui.showWelcome();
    }

    private void showGoodbye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    private void showMessage(String message) {
        ui.showMessage(message);
    }

    private void showList() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            message.append("\n ").append(i + 1).append('.').append(tasks.get(i));
        }
        ui.showMessage(message.toString());
    }

    private void updateStatus(String input, boolean isDone) throws OrbitException {
        String command = isDone ? "mark" : "unmark";
        int index = parseTaskIndex(input, command);
        Task task = tasks.get(index);
        if (isDone) {
            task.markAsDone();
            showMessage("Nice! I've marked this task as done:\n   " + task);
        } else {
            task.markAsNotDone();
            showMessage("OK, I've marked this task as not done yet:\n   " + task);
        }
        saveTasks();
    }

    private void addTodo(String input) throws OrbitException {
        String description = extractDescription(input, "todo");
        addTask(new Todo(description));
    }

    private void addDeadline(String input) throws OrbitException {
        String details = extractDescription(input, "deadline");
        int separator = details.indexOf(" /by ");
        if (separator < 0) {
            throw new OrbitException("A deadline needs a description and '/by' date or time.");
        }
        String description = details.substring(0, separator).trim();
        String by = details.substring(separator + 5).trim();
        requireNonEmpty(description, "The description of a deadline cannot be empty.");
        requireNonEmpty(by, "The '/by' date or time of a deadline cannot be empty.");
        try {
            addTask(new Deadline(description, by));
        } catch (DateTimeParseException e) {
            throw new OrbitException("Use yyyy-mm-dd for deadline dates, for example 2026-08-28.");
        }
    }

    private void addEvent(String input) throws OrbitException {
        String details = extractDescription(input, "event");
        int fromSeparator = details.indexOf(" /from ");
        int toSeparator = details.indexOf(" /to ");
        if (fromSeparator < 0 || toSeparator < 0 || toSeparator < fromSeparator) {
            throw new OrbitException("An event needs a description, '/from' time, and '/to' time.");
        }
        String description = details.substring(0, fromSeparator).trim();
        String from = details.substring(fromSeparator + 7, toSeparator).trim();
        String to = details.substring(toSeparator + 5).trim();
        requireNonEmpty(description, "The description of an event cannot be empty.");
        requireNonEmpty(from, "The '/from' time of an event cannot be empty.");
        requireNonEmpty(to, "The '/to' time of an event cannot be empty.");
        addTask(new Event(description, from, to));
    }

    private void addTask(Task task) {
        tasks.add(task);
        saveTasks();
        showMessage("Got it. I've added this task:\n   " + task
                + "\n Now you have " + tasks.size() + " tasks in the list.");
    }

    private void deleteTask(String input) throws OrbitException {
        int index = parseTaskIndex(input, "delete");
        Task removed = tasks.remove(index);
        saveTasks();
        showMessage("Noted. I've removed this task:\n   " + removed
                + "\n Now you have " + tasks.size() + " tasks in the list.");
    }

    private void saveTasks() {
        try {
            storage.save(tasks.asList());
        } catch (IOException e) {
            showMessage("OOPS!!! Could not save tasks: " + e.getMessage());
        }
    }

    private int parseTaskIndex(String input, String command) throws OrbitException {
        String argument = input.substring(command.length()).trim();
        if (argument.isEmpty()) {
            throw new OrbitException("Please specify a task number to " + command + ".");
        }
        try {
            int index = Integer.parseInt(argument) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new OrbitException("That task number does not exist.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new OrbitException("The task number must be a whole number.");
        }
    }

    private String extractDescription(String input, String command) throws OrbitException {
        String description = input.substring(command.length()).trim();
        requireNonEmpty(description, "The description of a " + command + " cannot be empty.");
        return description;
    }

    private void requireNonEmpty(String text, String errorMessage) throws OrbitException {
        if (text.isEmpty()) {
            throw new OrbitException(errorMessage);
        }
    }

    /**
     * Runs Orbit using standard input.
     *
     * @param args Command-line arguments; not used.
     */
    public static void main(String[] args) {
        new Orbit(new Scanner(System.in)).run();
    }
}
