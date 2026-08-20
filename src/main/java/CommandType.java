/**
 * Integer command identifiers used by the parser.
 */
public final class CommandType {
    public static final int BYE = 0;
    public static final int LIST = 1;
    public static final int MARK = 2;
    public static final int UNMARK = 3;
    public static final int TODO = 4;
    public static final int DEADLINE = 5;
    public static final int EVENT = 6;
    public static final int DELETE = 7;
    public static final int UNKNOWN = 8;

    private CommandType() {
    }

    public static int from(String input) {
        String command = input.split("\\s+", 2)[0].toLowerCase();
        return switch (command) {
        case "bye" -> BYE;
        case "list" -> LIST;
        case "mark" -> MARK;
        case "unmark" -> UNMARK;
        case "todo" -> TODO;
        case "deadline" -> DEADLINE;
        case "event" -> EVENT;
        default -> UNKNOWN;
        };
    }
}
