package grump.exception;

/**
 * Exception thrown when an invalid argument is encountered.
 */
public class InvalidArgException extends RuntimeException {
    /**
     * Constructs an InvalidArgException with the given message.
     *
     * @param message The detail message.
     */
    public InvalidArgException(String message) {
        super(message);
    }
}
