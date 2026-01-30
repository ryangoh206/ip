package grump.exception;

/**
 * Exception thrown when an invalid argument is encountered.
 */
public class InvalidArgException extends RuntimeException {
    public InvalidArgException(String message) {
        super(message);
    }
}
