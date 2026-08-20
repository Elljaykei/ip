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
        List<String> tasks = new ArrayList<>();
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
                    System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(input);
                System.out.println(" added: " + input);
            }
            System.out.println(LINE);
        }
    }
}
