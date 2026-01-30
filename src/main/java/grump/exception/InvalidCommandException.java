package grump.exception;

/**
 * Exception thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String message) {
        super(message);
    }
}
