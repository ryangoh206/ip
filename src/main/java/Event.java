import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toCsvString() {
        return "E," + description + "," + (isDone ? "1" : "0") + "," + start + "," + end;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }
}
