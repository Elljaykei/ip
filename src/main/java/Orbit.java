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
            System.out.println(" " + input);
            System.out.println(LINE);
        }
    }
}
