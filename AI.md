# AI Declarations
As part of the **A-AiAssisted** increment, I tried using AI as a partner to improve the quality of my code and insert a new feature. Below details what was used, how it helped and any other observations.

## Tools Used
- Windsurf IDE
- SWE-1.5 Model
- GitHub Copilot
- Claude Opus 4.6

## How I Used It
### Windsurf IDE
As I never used AI IDEs and such AI-assisted coding before, I read some tutorials online and experimented around. I tried Windsurf first. To attempt to improve code quality, I used the Plan feature in Windsurf and requested for it to generate a plan to improve my code quality, specifically in the aspects of Readability, Reduce Method Complexity, Reduce Nesting, etc.

It generated a comprehensive plan for me in which I checked and implemented it in phases. I managed to extract many magic literals as well as shorten many methods within the Command classes which had the longest throughout my codebase. The main changes was the addition of the enums, constant and util package. The CommandValidator.java class added provides the validation, error handling and extraction of arguments for the command classes, which allowed the reduction in method length.

The files changed/added are listed below:
- src/main/java/grump/Main.java
- src/main/java/grump/command/DeadlineCommand.java
- src/main/java/grump/command/DeleteTaskCommand.java
- src/main/java/grump/command/EventCommand.java
- src/main/java/grump/command/MarkCommand.java
- src/main/java/grump/command/TagCommand.java
- src/main/java/grump/command/UnmarkCommand.java
- src/main/java/grump/command/UntagCommand.java
- src/main/java/grump/constants/CommandMessages.java (new)
- src/main/java/grump/enums/CommandType.java (new)
- src/main/java/grump/enums/TaskType.java (new)
- src/main/java/grump/parser/Parser.java
- src/main/java/grump/storage/Storage.java
- src/main/java/grump/task/Task.java
- src/main/java/grump/ui/Ui.java
- src/main/java/grump/util/CommandValidator.java (new)

### GitHub Copilot on Vscode
I also tried out GitHub Copilot with Claude Opus 4.6 to further improve on code quality. Similarly, it generated a plan and allowed me to check through and implement. No big changes were made and mostly, minor improvements like magic literals and some deletion of unnecessary code. It also tested my project via gradle and detected that my old test case files were not updated, in which it proceeded to propose updated to them.

The files changed/added are listed below:
- src/main/java/grump/DialogBox.java
- src/main/java/grump/Grump.java
- src/main/java/grump/Main.java
- src/main/java/grump/MainWindow.java
- src/main/java/grump/command/CommandResult.java
- src/main/java/grump/command/EventCommand.java
- src/main/java/grump/command/FindCommand.java
- src/main/java/grump/command/ToDoCommand.java
- src/main/java/grump/storage/Storage.java
- src/main/java/grump/task/Deadline.java
- src/main/java/grump/task/Event.java
- src/main/java/grump/task/Task.java
- src/main/java/grump/task/ToDo.java
- src/main/java/grump/ui/GuiResponseHandler.java
- src/main/java/grump/ui/Ui.java
- src/main/java/grump/util/CommandValidator.java
- src/test/java/grump/task/DeadlineTest.java
- src/test/java/grump/task/ToDoTest.java

### GitHub Copilot on Vscode
I also used GitHub Copilot with Claude Sonnet 4.6 to generate a User Guide following the Address Book 3 example given. It executed perfectly and saved much time otherwise used to write the User Guide.

The files changed/added are listed below:
- docs/README.md

## Key Observations
Overall, it was an pleasant and eye-opening experience. The speed and ability to learn the context of the entire project was impressive. However, it still required human oversight and a good grasp of the project to be able to check through the proposed changes and accept them one by one. I would not recommend simply allowing the AI to implement changes by itself as minor errors on the AI's end will produce bugs that would be hard to debug and uncover later on. Ultimately, using AI saved a lot of time in the planning of how to refactor the code but it also required some time to check through the changes before implementing.
