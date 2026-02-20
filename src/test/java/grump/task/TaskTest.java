package grump.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TaskTest {

    // Use ToDo as concrete implementation to test abstract parent Task
    @Test
    void testConstructorWithDescriptionOnly() {
        Task task = new ToDo("Buy groceries");
        assertEquals("Buy groceries", task.getDescription());
        assertEquals(" ", task.getStatusIcon());
        assertFalse(task.isDone());
    }

    @Test
    void testConstructorWithIsDone() {
        Task task = new ToDo("Study for exam", true);
        assertEquals("Study for exam", task.getDescription());
        assertEquals("X", task.getStatusIcon());
        assertTrue(task.isDone());
    }

    @Test
    void testConstructorWithTagsAndIsDone() {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("important");
        tags.add("urgent");
        Task task = new ToDo("Finish project", tags, true);
        assertEquals("Finish project", task.getDescription());
        assertEquals("X", task.getStatusIcon());
        assertTrue(task.isDone());
    }

    @Test
    void testMarkAsDone() {
        Task task = new ToDo("Read book");
        assertEquals(" ", task.getStatusIcon());
        assertFalse(task.isDone());

        task.markAsDone();
        assertEquals("X", task.getStatusIcon());
        assertTrue(task.isDone());
    }

    @Test
    void testMarkAsNotDone() {
        Task task = new ToDo("Write report", true);
        assertEquals("X", task.getStatusIcon());
        assertTrue(task.isDone());

        task.markAsNotDone();
        assertEquals(" ", task.getStatusIcon());
        assertFalse(task.isDone());
    }

    @Test
    void testAddTag() {
        Task task = new ToDo("Meeting");
        task.addTag("work");
        task.addTag("meeting");

        assertEquals("[T][ ] Meeting (Tags: [work, meeting])", task.toString());
    }

    @Test
    void testRemoveTag() {
        Task task = new ToDo("Project submission");
        task.addTag("deadline");
        task.addTag("important");

        assertTrue(task.removeTag("deadline"));
        assertEquals("[T][ ] Project submission (Tags: [important])", task.toString());
    }

    @Test
    void testRemoveTagNotFound() {
        Task task = new ToDo("Clean house");
        task.addTag("chore");

        assertFalse(task.removeTag("work"));
    }
}
