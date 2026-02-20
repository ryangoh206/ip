package grump.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void testConstructorWithDescriptionAndBy() {
        LocalDateTime by = LocalDateTime.of(2026, 2, 5, 18, 30);
        Deadline deadline = new Deadline("Submit assignment", by);

        assertEquals("Submit assignment", deadline.description);
        assertEquals(" ", deadline.getStatusIcon());
        assertEquals(by, deadline.by);
    }

    @Test
    void testToStringFormat() {
        LocalDateTime by = LocalDateTime.of(2026, 2, 5, 18, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

        Deadline deadline = new Deadline("Submit assignment", by);
        assertEquals("[D][ ] Submit assignment (Tags: []) (by: " + by.format(formatter) + ")",
                deadline.toString());
    }

    @Test
    void testToStringWithDoneStatus() {
        LocalDateTime by = LocalDateTime.of(2026, 2, 5, 18, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

        Deadline deadlineDone = new Deadline("Finish project", true, by);
        assertEquals("[D][X] Finish project (Tags: []) (by: " + by.format(formatter) + ")",
                deadlineDone.toString());
    }

    @Test
    void testToStringWithTags() {
        LocalDateTime by = LocalDateTime.of(2026, 2, 5, 18, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

        Deadline deadline = new Deadline("Prepare presentation", by);
        deadline.addTag("work");
        deadline.addTag("urgent");
        String result = deadline.toString();

        assertEquals("[D][ ] Prepare presentation (Tags: [work, urgent]) (by: "
                + by.format(formatter) + ")", result);
    }

    @Test
    void testToCsvString() {
        LocalDateTime by = LocalDateTime.of(2026, 2, 5, 18, 30);
        Deadline deadline = new Deadline("Submit assignment", by);

        assertEquals("D,Submit assignment,0," + by + ",[]", deadline.toCsvString());
    }

    @Test
    void testToCsvStringWithDone() {
        LocalDateTime by = LocalDateTime.of(2026, 2, 5, 18, 30);
        Deadline deadlineDone = new Deadline("Finish project", true, by);

        assertEquals("D,Finish project,1," + by + ",[]", deadlineDone.toCsvString());
    }

    @Test
    void testToCsvStringWithTags() {
        LocalDateTime by = LocalDateTime.of(2026, 2, 5, 18, 30);
        Deadline deadline = new Deadline("Finish project", by);
        deadline.addTag("school");
        deadline.addTag("urgent");

        assertEquals("D,Finish project,0," + by + ",[school, urgent]", deadline.toCsvString());
    }

}
