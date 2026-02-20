package grump.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    void testToStringFormat() {
        ToDo todo = new ToDo("Read book");

        assertEquals("[T][ ] Read book (Tags: [])", todo.toString());
    }

    @Test
    void testToStringWithDoneStatus() {
        ToDo todoDone = new ToDo("Write report", true);

        assertEquals("[T][X] Write report (Tags: [])", todoDone.toString());
    }

    @Test
    void testToStringWithTags() {
        ToDo todo = new ToDo("Prepare presentation");
        todo.addTag("work");
        todo.addTag("urgent");
        String result = todo.toString();

        assertEquals("[T][ ] Prepare presentation (Tags: [work, urgent])", result);
    }

    @Test
    void testToCsvStringFormat() {
        ToDo todo = new ToDo("Clean room");

        assertEquals("T,Clean room,0,[]", todo.toCsvString());
    }

    @Test
    void testToCsvStringWithDone() {
        ToDo todoDone = new ToDo("Pay bills", true);

        assertEquals("T,Pay bills,1,[]", todoDone.toCsvString());
    }

    @Test
    void testToCsvStringWithTags() {
        ToDo todo = new ToDo("Prepare presentation");
        todo.addTag("work");
        todo.addTag("urgent");

        assertEquals("T,Prepare presentation,0,[work, urgent]", todo.toCsvString());
    }

}
