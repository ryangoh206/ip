package grump.exception;

/**
 * Exception thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends RuntimeException {
    /**
     * Constructs an InvalidCommandException with the given message.
     *
     * @param message The detail message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
