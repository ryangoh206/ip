package grump.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    void testConstructorAndGetters() {
        ToDo todo = new ToDo("Buy groceries");
        assertEquals("Buy groceries", todo.description);
        assertEquals(" ", todo.getStatusIcon());

        ToDo todoDone = new ToDo("Finish homework", true);
        assertEquals("Finish homework", todoDone.description);
        assertEquals("X", todoDone.getStatusIcon());
    }

    @Test
    void testToString() {
        ToDo todo = new ToDo("Read book");
        assertEquals("[T][ ] Read book", todo.toString());

        ToDo todoDone = new ToDo("Write report", true);
        assertEquals("[T][X] Write report", todoDone.toString());
    }

    @Test
    void testToCsvString() {
        ToDo todo = new ToDo("Clean room");
        assertEquals("T,Clean room,0", todo.toCsvString());

        ToDo todoDone = new ToDo("Pay bills", true);
        assertEquals("T,Pay bills,1", todoDone.toCsvString());
    }
}
