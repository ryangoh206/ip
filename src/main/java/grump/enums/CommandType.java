package grump.enums;

/**
 * Enumeration of supported command types in the Grump application.
 * Each enum value represents a specific user command that can be parsed and executed.
 */
public enum CommandType {
    BYE("BYE"),
    LIST("LIST"),
    MARK("MARK"),
    UNMARK("UNMARK"),
    DELETE("DELETE"),
    TODO("TODO"),
    DEADLINE("DEADLINE"),
    EVENT("EVENT"),
    FIND("FIND"),
    TAG("TAG"),
    UNTAG("UNTAG");

    private final String commandString;

    /**
     * Constructs a CommandType with the associated command string.
     *
     * @param commandString The string representation of the command.
     */
    CommandType(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Returns the string representation of this command.
     *
     * @return The command string.
     */
    public String getCommandString() {
        return commandString;
    }

    /**
     * Finds the CommandType corresponding to the given command string.
     *
     * @param commandString The command string to look up.
     * @return The matching CommandType, or null if not found.
     */
    public static CommandType fromString(String commandString) {
        for (CommandType type : CommandType.values()) {
            if (type.commandString.equalsIgnoreCase(commandString)) {
                return type;
            }
        }
        return null;
    }
}
