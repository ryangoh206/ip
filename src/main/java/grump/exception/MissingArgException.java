package grump.exception;

public class MissingArgException extends RuntimeException {
    public MissingArgException(String message) {
        super(message);
    }
}
