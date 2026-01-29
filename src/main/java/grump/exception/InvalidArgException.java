package grump.exception;

public class InvalidArgException extends RuntimeException {
    public InvalidArgException(String message) {
        super(message);
    }
}
