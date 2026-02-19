package grump.exception;

/**
 * Exception thrown when a required argument is missing.
 */
public class MissingArgException extends RuntimeException {
    /**
     * Constructs a MissingArgException with the given message.
     *
     * @param message The detail message.
     */
    public MissingArgException(String message) {
        super(message);
    }
}
