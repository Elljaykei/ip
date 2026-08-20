import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Orbit is a command-line chatbot that helps the user track tasks.
 */
public class Orbit {
    private static final String LINE = "____________________________________________________________";
    private static final String BANNER = "  ___  ____  ____ ___ _____\n"
            + " / _ \\|  _ \\| __ )_ _|_   _|\n"
            + "| | | | |_) |  _ \\| |  | |\n"
            + "| |_| |  _ <| |_) | |  | |\n"
            + " \\___/|_| \\_\\____/___| |_|";

    /**
     * Echoes commands until the user enters {@code bye}.
     *
     * @param args Command-line arguments; not used.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        System.out.println(LINE);
        System.out.println(BANNER);
        System.out.println(" Hello! I'm Orbit");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            }
            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + tasks.get(i));
                }
            } else if (input.startsWith("mark ")) {
                Task task = tasks.get(Integer.parseInt(input.substring(5)) - 1);
                task.markAsDone();
                System.out.println(" Nice! I've marked this task as done:\n   " + task);
            } else if (input.startsWith("unmark ")) {
                Task task = tasks.get(Integer.parseInt(input.substring(7)) - 1);
                task.markAsNotDone();
                System.out.println(" OK, I've marked this task as not done yet:\n   " + task);
            } else {
                tasks.add(new Task(input));
                System.out.println(" added: " + input);
            }
            System.out.println(LINE);
        }
    }
}
