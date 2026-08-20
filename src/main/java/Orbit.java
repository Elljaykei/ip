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
     * Greets the user and exits.
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
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }
}
