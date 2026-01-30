package grump.task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void testConstructorAndStatus() {
        LocalDateTime by = LocalDateTime.of(2026, 2, 5, 18, 30);
        Deadline deadline = new Deadline("Submit assignment", by);

        assertEquals("Submit assignment", deadline.description);
        assertEquals(" ", deadline.getStatusIcon()); // not done
        assertEquals(by, deadline.by);

        Deadline deadlineDone = new Deadline("Finish project", true, by);
        assertEquals("Finish project", deadlineDone.description);
        assertEquals("X", deadlineDone.getStatusIcon()); // done
        assertEquals(by, deadlineDone.by);
    }

    @Test
    void testMarkAsDoneAndNotDone() {
        LocalDateTime by = LocalDateTime.of(2026, 2, 5, 18, 30);
        Deadline deadline = new Deadline("Submit assignment", by);

        assertEquals(" ", deadline.getStatusIcon());

        deadline.markAsDone();
        assertEquals("X", deadline.getStatusIcon());

        deadline.markAsNotDone();
        assertEquals(" ", deadline.getStatusIcon());
    }

    @Test
    void testToString() {
        LocalDateTime by = LocalDateTime.of(2026, 2, 5, 18, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

        Deadline deadline = new Deadline("Submit assignment", by);
        assertEquals("[D][ ] Submit assignment (by: " + by.format(formatter) + ")",
                deadline.toString());

        Deadline deadlineDone = new Deadline("Finish project", true, by);
        assertEquals("[D][X] Finish project (by: " + by.format(formatter) + ")",
                deadlineDone.toString());
    }

    @Test
    void testToCsvString() {
        LocalDateTime by = LocalDateTime.of(2026, 2, 5, 18, 30);
        Deadline deadline = new Deadline("Submit assignment", by);
        assertEquals("D,Submit assignment,0," + by, deadline.toCsvString());

        Deadline deadlineDone = new Deadline("Finish project", true, by);
        assertEquals("D,Finish project,1," + by, deadlineDone.toCsvString());
    }
}
