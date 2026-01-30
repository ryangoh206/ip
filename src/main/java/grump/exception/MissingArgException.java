package grump.exception;

/**
 * Exception thrown when a required argument is missing.
 */
public class MissingArgException extends RuntimeException {
    public MissingArgException(String message) {
        super(message);
    }
}
