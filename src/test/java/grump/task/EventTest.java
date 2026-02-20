package grump.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void testConstructorWithStartAndEnd() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 15, 14, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 15, 16, 0);
        Event event = new Event("Team meeting", start, end);

        assertEquals("Team meeting", event.getDescription());
        assertEquals(" ", event.getStatusIcon());
        assertEquals(start, event.start);
        assertEquals(end, event.end);
    }

    @Test
    void testToStringFormat() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 14, 30);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 16, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        Event event = new Event("Project presentation", start, end);
        String result = event.toString();

        assertEquals("[E][ ] Project presentation (Tags: []) (from: " + start.format(formatter)
                + " to: " + end.format(formatter) + ")", result);
    }

    @Test
    void testToStringWithDoneStatus() {
        LocalDateTime start = LocalDateTime.of(2026, 2, 28, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 2, 28, 10, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        Event event = new Event("Workshop", true, start, end);
        String result = event.toString();

        assertEquals("[E][X] Workshop (Tags: []) (from: " + start.format(formatter) + " to: "
                + end.format(formatter) + ")", result);
    }

    @Test
    void testToStringWithTags() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 5, 15, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 5, 17, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        Event event = new Event("Seminar", start, end);
        event.addTag("work");
        event.addTag("important");
        String result = event.toString();

        assertEquals("[E][ ] Seminar (Tags: [work, important]) (from: " + start.format(formatter)
                + " to: " + end.format(formatter) + ")", result);
    }

    @Test
    void testToCsvString() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 5, 15, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 5, 17, 0);
        Event event = new Event("Seminar", start, end);

        assertEquals("E,Seminar,0," + start + "," + end + ",[]", event.toCsvString());
    }

    @Test
    void testToCsvStringWithDone() {
        LocalDateTime start = LocalDateTime.of(2026, 2, 25, 11, 0);
        LocalDateTime end = LocalDateTime.of(2026, 2, 25, 12, 0);
        Event event = new Event("Training session", true, start, end);

        assertEquals("E,Training session,1," + start + "," + end + ",[]", event.toCsvString());
    }

    @Test
    void testToCsvStringWithTags() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 5, 15, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 5, 17, 0);
        Event event = new Event("Seminar", start, end);
        event.addTag("work");
        event.addTag("important");
        assertEquals("E,Seminar,0," + start + "," + end + ",[work, important]",
                event.toCsvString());
    }
}
