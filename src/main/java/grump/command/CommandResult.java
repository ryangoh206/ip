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

    public boolean getIsExit() {
        return this.isExit;
    }

    public String getResponseString() {
        return this.responseString;
    }


}
