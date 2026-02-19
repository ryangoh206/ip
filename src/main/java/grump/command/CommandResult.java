package grump.command;

/**
 * Represents the result of executing a command.
 */
public class CommandResult {
    private final boolean isExit;
    private final String responseString;

    /**
     * Constructs a CommandResult with the specified isExit value and responseString value.
     *
     * @param isExit The value signifying whether the program should exit.
     * @param responseString The response string to be displayed in the GUI.
     */
    public CommandResult(boolean isExit, String responseString) {
        this.isExit = isExit;
        this.responseString = responseString;
    }

    /**
     * Returns whether the application should exit after this command.
     *
     * @return {@code true} if the application should exit, {@code false} otherwise.
     */
    public boolean getIsExit() {
        return this.isExit;
    }

    /**
     * Returns the response string to be displayed.
     *
     * @return The response string.
     */
    public String getResponseString() {
        return this.responseString;
    }


}
