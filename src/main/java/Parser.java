/**
 * Interprets user input as an Orbit command.
 */
public class Parser {
    public CommandType parse(String input) {
        return CommandType.from(input);
    }
}
