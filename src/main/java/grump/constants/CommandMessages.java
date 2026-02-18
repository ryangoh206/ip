package grump.constants;

/**
 * Centralized constants for command error messages to ensure consistency across the application.
 * This eliminates magic strings and provides a single source of truth for error messaging.
 */
public class CommandMessages {

    // Task number validation messages
    public static final String MISSING_TASK_NUMBER = "Please provide the task number.";
    public static final String INVALID_TASK_NUMBER = "The task number you provided is invalid.";

    // Task description validation messages
    public static final String MISSING_DESCRIPTION = "Please provide a description for the task.";

    // Command argument validation messages
    public static final String MISSING_ARGS = "Please provide valid arguments for this command.";

    // Deadline command messages
    public static final String MISSING_BY_DATE =
            "Please provide a valid by date/time for the deadline task.";

    // Event command messages
    public static final String MISSING_FROM_TO =
            "Please provide the start and end date/time for the event task.";
    public static final String MISSING_TO_DATE =
            "Please provide the end date/time for the event task.";
    public static final String MISSING_FROM_DATE =
            "Please provide the start date/time for the event task.";

    // Tag command messages
    public static final String MISSING_TASK_NUMBER_TO_TAG =
            "Please provide the task number to tag.";
    public static final String MISSING_TAG_NAME = "Please provide the tag name for the task.";
    public static final String MISSING_TASK_NUMBER_TO_UNTAG =
            "Please provide the task number to untag.";

    // General validation messages
    public static final String TASK_NUMBER_OUT_OF_BOUNDS = "Task number is out of valid range.";
}
