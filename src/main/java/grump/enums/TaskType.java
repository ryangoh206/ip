package grump.enums;

/**
 * Enumeration of supported task types in the Grump application.
 * Each enum value represents a specific type of task that can be created and managed.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String typeIdentifier;

    /**
     * Constructs a TaskType with the associated type identifier.
     *
     * @param typeIdentifier The single-character identifier used in storage.
     */
    TaskType(String typeIdentifier) {
        this.typeIdentifier = typeIdentifier;
    }

    /**
     * Returns the type identifier used for storage representation.
     *
     * @return The type identifier string.
     */
    public String getTypeIdentifier() {
        return typeIdentifier;
    }

    /**
     * Finds the TaskType corresponding to the given type identifier.
     *
     * @param typeIdentifier The type identifier to look up.
     * @return The matching TaskType, or null if not found.
     */
    public static TaskType fromIdentifier(String typeIdentifier) {
        for (TaskType type : TaskType.values()) {
            if (type.typeIdentifier.equals(typeIdentifier)) {
                return type;
            }
        }
        return null;
    }
}
