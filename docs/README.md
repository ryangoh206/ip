# Grump User Guide

Grump is a **desktop task manager chatbot**, optimized for use via a **Command Line Interface (CLI)**, while still having the benefits of a Graphical User Interface (GUI). Choose between a pure Text interface or a GUI interface. Keep track of your to-dos, deadlines, and events — all with quick typed commands.

---

## Quick Start

1. Ensure you have **Java 17** or above installed.
2. Download the latest `grump.jar` from the releases page.
3. Open a terminal, `cd` into the folder containing the jar, and run:
    
    For GUI:
   ```
   java -jar grump.jar
   ```
    For Text CLI:
   ```
   java -jar grump.jar cli
   ```
4. Type a command and press **Enter** to execute it.

---

## Notes on Command Format

- Words in `UPPER_CASE` are parameters you supply. e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter such as `todo Buy groceries`.
- Dates must be in `dd-MM-yyyy` or `dd-MM-yyyy HH:mm` format. e.g. `19-02-2026` or `19-02-2026 14:30`.
- `INDEX` refers to the task number shown in the task list displayed (starting from 1).

---

## Features

### Adding a to-do : `todo`

Adds a simple task with no date attached.

Format: `todo DESCRIPTION`

**Example:**
```
todo Buy groceries
```
Output:
```
Got it. I've added this task:
  [T][ ] Buy groceries (Tags: [])
Now you have 1 task in the list.
```

---

### Adding a deadline : `deadline`

Adds a task with a due date.

Format: `deadline DESCRIPTION /by DATE`

**Example:**
```
deadline Submit assignment /by 28-02-2026 23:59
```
Output:
```
Got it. I've added this task:
  [D][ ] Submit assignment (by: 28 Feb 2026 23:59) (Tags: [])
Now you have 2 tasks in the list.
```

---

### Adding an event : `event`

Adds a task with a start and end date.

Format: `event DESCRIPTION /from DATE /to DATE`

**Example:**
```
event Team meeting /from 20-02-2026 10:00 /to 20-02-2026 11:00
```
Output:
```
Got it. I've added this task:
  [E][ ] Team meeting (from: 20 Feb 2026 10:00 to: 20 Feb 2026 11:00) (Tags: [])
Now you have 3 tasks in the list.
```

---

### Listing all tasks : `list`

Shows all tasks in your task list.

Format: `list`

Output:
```
Here are the tasks in your list:
1. [T][ ] Buy groceries (Tags: [])
2. [D][ ] Submit assignment (by: 28 Feb 2026 23:59) (Tags: [])
3. [E][ ] Team meeting (from: 20 Feb 2026 10:00 to: 20 Feb 2026 11:00) (Tags: [])
```

> Task types: `[T]` = To-do, `[D]` = Deadline, `[E]` = Event
> Completion status: `[X]` = done, `[ ]` = not done

---

### Marking a task as done : `mark`

Marks the specified task as completed.

Format: `mark INDEX`

**Example:**
```
mark 1
```
Output:
```
Nice! I've marked this task as done:
  [T][X] Buy groceries (Tags: [])
```

---

### Marking a task as not done : `unmark`

Marks the specified task as incomplete.

Format: `unmark INDEX`

**Example:**
```
unmark 1
```
Output:
```
OK, I've marked this task as not done yet:
  [T][ ] Buy groceries (Tags: [])
```

---

### Deleting a task : `delete`

Removes the specified task from the list.

Format: `delete INDEX`

**Example:**
```
delete 1
```
Output:
```
Noted! I've deleted this task:
  [T][ ] Buy groceries (Tags: [])
Now you have 2 tasks in the list.
```

---

### Finding tasks : `find`

Finds all tasks whose description contains the given keyword.

Format: `find KEYWORD`

- The search is case-insensitive.
- Only one keyword can be provided at a time.

**Example:**
```
find assignment
```
Output:
```
Here are the tasks in your list:
1. [D][ ] Submit assignment (by: 28 Feb 2026 23:59) (Tags: [])
```

---

### Tagging a task : `tag`

Adds a tag to the specified task.

Format: `tag INDEX TAG`

**Example:**
```
tag 1 school
```
Output:
```
Great! I've added the tag 'school' to the task:
  [D][ ] Submit assignment (by: 28 Feb 2026 23:59) (Tags: [school])
```

---

### Removing a tag from a task : `untag`

Removes a tag from the specified task.

Format: `untag INDEX TAG`

**Example:**
```
untag 1 school
```
Output:
```
Great! I've removed the tag 'school' from the task:
  [D][ ] Submit assignment (by: 28 Feb 2026 23:59) (Tags: [])
```

---

### Exiting the program: `bye`

Exits the application.

**For GUI:** Simply close the program.

**For Text CLI:** Format: `bye`

---

### Saving the data

Tasks are saved automatically to `data/tasks.csv` after every command that changes the data. There is no need to save manually. You can also choose to edit the file using external programs but keep to the format.

---

## Command Summary

| Action | Format | Example |
|---|---|---|
| Add to-do | `todo DESCRIPTION` | `todo Buy groceries` |
| Add deadline | `deadline DESCRIPTION /by DATE` | `deadline Submit report /by 28-02-2026 23:59` |
| Add event | `event DESCRIPTION /from DATE /to DATE` | `event Meeting /from 20-02-2026 10:00 /to 20-02-2026 11:00` |
| List tasks | `list` | `list` |
| Mark as done | `mark INDEX` | `mark 2` |
| Mark as not done | `unmark INDEX` | `unmark 2` |
| Delete task | `delete INDEX` | `delete 3` |
| Find tasks | `find KEYWORD` | `find assignment` |
| Tag a task | `tag INDEX TAG` | `tag 1 school` |
| Remove a tag | `untag INDEX TAG` | `untag 1 school` |
| Exit | `bye` | `bye` |
