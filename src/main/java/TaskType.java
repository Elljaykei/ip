/**
 * Types of tasks supported by Orbit and their display symbols.
 */
public enum TaskType {
    TODO("T"), DEADLINE("D"), EVENT("E");

    private final String symbol;

    TaskType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
