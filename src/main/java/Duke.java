import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Runs the Duke task manager command-line application.
 */
public class Duke {
    private static final Path TASK_LIST_DATA_PATH = Path.of("src", "data", "taskListData.txt");

    public static void main(String[] args) {
        final String BANNER = "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n"
                + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░▓▓▓▓▓▓▓▓▓▓░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n"
                + "▓▓▓▓▓▓▓▓▓▓▓░░▓▓▓░░░░░░░░░░▓▓░░░░░░▓▓▓▓░░▓▓▓▓▓▓▓▓▓▓▓▓\n"
                + "▓▓▓▓▓▓▓▓▓░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░▓▓▓▓▓▓▓▓▓▓\n"
                + "▓▓▓▓▓▓▓▓▓▓░░▓▓▓▓▓▓░░░░░░░░░░░░░░░░▓▓▓▓▓░░▓▓▓▓▓▓▓▓▓▓▓\n"
                + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n"
                + "▓▓▓▓░░░░░▓▓▓▓▓░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓░░░░░▓▓▓▓\n"
                + "▓▓▓░░░░░░░▓▓▓░░░░░░░▓▓▓▓░░░░▓▓▓▓░░░░░░░▓▓▓░░░░░░░▓▓▓\n"
                + "▓▓░░░░░░░░▓▓░░░░░░░░▓▓▓▓░░░░▓▓▓▓░░░░░░░░▓▓░░░░░░░░▓▓\n"
                + "▓░░░▓▓▓▓▓▓▓▓░░░░░░░░▓▓▓▓░░░░▓▓▓▓░░░░░░░░▓▓▓▓▓▓▓▓▓░░▓\n"
                + "▓░░░░░░░░▓▓▓░░░░░░░░▓▓▓▓░░░░▓▓▓▓░░░░░░░░▓▓▓░░░░░░░░▓\n"
                + "▓░░░░░░░░░▓▓░░░░░░░░▓▓▓▓░░░░▓▓▓▓░░░░░░░░▓▓░░░░░░░░░▓\n"
                + "▓▓▓░░░░░░░▓▓░░░░░▓░░░░░░░░░░░░░░░░▓░░░░░▓▓░░░░░░░▓▓▓\n"
                + "▓▓▓▓░░░░░░▓▓▓░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░▓▓▓░░░░░░▓▓▓▓\n"
                + "▓▓▓▓▓▓░░░░░▓▓░░░░░░▓░░░░░░░░░░░░▓░░░░░▓▓▓░░░░░▓▓▓▓▓▓\n"
                + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n"
                + "▓▓▓▓▓▓▓▓▓▓▓░▓▓▓░░░░░░░░░░░░░░░░░░░░░▓▓▓▓░▓▓▓▓▓▓▓▓▓▓▓\n"
                + "▓▓▓▓▓▓▓▓░░░░▓▓▓▓▓▓▓░░░░░░░░░░░░░░▓▓▓▓▓▓▓░░░░▓▓▓▓▓▓▓▓\n"
                + "▓▓▓▓▓▓▓░░░░▓░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░▓░░░░▓▓▓▓▓▓▓\n"
                + "▓▓▓▓▓▓░░░░▓░░░░░░░░░░░░▓▓▓▓▓▓░░░░░░░░░░░░▓░░░░▓▓▓▓▓▓\n"
                + "▓▓▓▓▓░░░░░▓░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░▓░░░░░▓▓▓▓▓\n"
                + "▓▓▓▓▓▓░░░░░░░▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░▓▓▓▓▓▓\n"
                + "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n";

        final String BAR = "____________________________________________________________";
        final String CHATBOT_NAME = "Flowey";
        System.out.println(BAR);
        System.out.println(BANNER);
        System.out.println(String.format("Howdy! I'm %s!", CHATBOT_NAME));
        System.out.println(BAR);

        ArrayList<Task> taskList = new ArrayList<>();

        readTaskListData(taskList);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (command.isEmpty()) {
                printError("Please enter a command.", BAR);
                continue;
            }

            String[] splitCommand = command.split("\\s+", 2);
            String commandWord = splitCommand[0];
            if (commandWord.equals("bye")) {
                if (splitCommand.length != 1) {
                    printError("Use: bye", BAR);
                    continue;
                }
                System.out.println("\t" + BAR);
                System.out.println("\t" + "Bye. Hope to see you again soon!");
                System.out.println("\t" + BAR);
                break;
            } else if (commandWord.equals("list")) {
                if (splitCommand.length == 1) {
                    listTasks(taskList, BAR);
                } else {
                    printError("Use: list", BAR);
                }
            } else if (commandWord.equals("todo")) {
                addTodo(command, taskList, BAR);
            } else if (commandWord.equals("deadline")) {
                addDeadline(command, taskList, BAR);
            } else if (commandWord.equals("event")) {
                addEvent(command, taskList, BAR);
            } else if (commandWord.equals("mark")) {
                updateTaskStatus(splitCommand, taskList, true, BAR);
            } else if (commandWord.equals("unmark")) {
                updateTaskStatus(splitCommand, taskList, false, BAR);
            } else if (commandWord.equals("delete")) {
                deleteTask(splitCommand, taskList, BAR);
            } else {
                printError("Hey pal, I don't understand what you're saying.", BAR);
            }
        }
    }

    /** Loads valid saved tasks, skipping malformed records so one bad line cannot stop Duke. */
    private static void readTaskListData(ArrayList<Task> taskList) {
        if (Files.notExists(TASK_LIST_DATA_PATH)) {
            return;
        }

        try {
            List<String> lines = Files.readAllLines(TASK_LIST_DATA_PATH, StandardCharsets.UTF_8);
            for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
                if (lines.get(lineNumber).isBlank()) {
                    continue;
                }
                Optional<Task> savedTask = parseSavedTask(lines.get(lineNumber));
                if (savedTask.isPresent()) {
                    taskList.add(savedTask.get());
                } else {
                    System.err.printf("Ignoring invalid saved task on line %d.%n", lineNumber + 1);
                }
            }
        } catch (IOException exception) {
            System.err.printf("Unable to load saved tasks: %s%n", exception.getMessage());
        }
    }

    /** Converts one validated storage record into a task. */
    private static Optional<Task> parseSavedTask(String line) {
        String[] parts = line.split("\\|", -1);
        for (int index = 0; index < parts.length; index++) {
            parts[index] = parts[index].trim();
        }
        if (parts.length < 3 || !(parts[1].equals("0") || parts[1].equals("1")) || parts[2].isBlank()) {
            return Optional.empty();
        }

        Task task;
        switch (parts[0]) {
        case "T":
            if (parts.length != 3) {
                return Optional.empty();
            }
            task = new ToDoTask(parts[2]);
            break;
        case "D":
            if (parts.length != 4 || parts[3].isBlank()) {
                return Optional.empty();
            }
            task = new DeadlineTask(parts[2], parts[3]);
            break;
        case "E":
            if (parts.length != 5 || parts[3].isBlank() || parts[4].isBlank()) {
                return Optional.empty();
            }
            task = new EventTask(parts[2], parts[3], parts[4]);
            break;
        default:
            return Optional.empty();
        }

        if (parts[1].equals("1")) {
            task.markAsComplete();
        }
        return Optional.of(task);
    }

    /** Writes a complete replacement file so an interrupted save cannot corrupt existing data. */
    private static boolean saveTaskListData(List<Task> taskList) {
        Path parent = TASK_LIST_DATA_PATH.getParent();
        Path temporaryFile = null;
        try {
            if (parent != null) {
                Files.createDirectories(parent);
                temporaryFile = Files.createTempFile(parent, "task-list-", ".tmp");
            } else {
                temporaryFile = Files.createTempFile("task-list-", ".tmp");
            }
            StringBuilder savedTasks = new StringBuilder();
            for (Task task : taskList) {
                savedTasks.append(task.toFileString()).append(System.lineSeparator());
            }
            Files.writeString(temporaryFile, savedTasks.toString(), StandardCharsets.UTF_8);
            try {
                Files.move(temporaryFile, TASK_LIST_DATA_PATH, StandardCopyOption.ATOMIC_MOVE,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException exception) {
                Files.move(temporaryFile, TASK_LIST_DATA_PATH, StandardCopyOption.REPLACE_EXISTING);
            }
            return true;
        } catch (IOException exception) {
            System.err.printf("Unable to save tasks: %s%n", exception.getMessage());
            return false;
        } finally {
            if (temporaryFile != null) {
                try {
                    Files.deleteIfExists(temporaryFile);
                } catch (IOException ignored) {
                    // A failed cleanup does not affect the saved task list.
                }
            }
        }
    }

    /** Prints all tasks, numbered from one. */
    private static void listTasks(ArrayList<Task> taskList, String bar) {
        System.out.println("\t" + bar);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + taskList.get(i));
        }
        System.out.println("\t" + bar);
    }

    /** Adds a todo from a command in the form {@code todo DESCRIPTION}. */
    private static void addTodo(String command, ArrayList<Task> taskList, String bar) {
        String description = command.substring("todo".length()).trim();
        if (description.isEmpty()) {
            printError("The description of a todo cannot be empty.", bar);
            return;
        }
        if (description.contains("|")) {
            printError("Task details cannot contain '|'.", bar);
            return;
        }
        addTask(new ToDoTask(description), taskList, bar);
    }

    /** Adds a deadline from a command in the form {@code deadline DESCRIPTION /by TIME}. */
    private static void addDeadline(String command, ArrayList<Task> taskList, String bar) {
        String details = command.substring("deadline".length()).trim();
        String[] deadlineParts = details.split("\\s+/by\\s+", 2);
        if (deadlineParts.length != 2 || deadlineParts[0].isBlank() || deadlineParts[1].isBlank()) {
            printError("Use: deadline DESCRIPTION /by DATE_OR_TIME", bar);
            return;
        }
        if (deadlineParts[0].contains("|") || deadlineParts[1].contains("|")) {
            printError("Task details cannot contain '|'.", bar);
            return;
        }
        addTask(new DeadlineTask(deadlineParts[0], deadlineParts[1]), taskList, bar);
    }

    /** Adds an event from a command in the form {@code event DESCRIPTION /from START /to END}. */
    private static void addEvent(String command, ArrayList<Task> taskList, String bar) {
        String details = command.substring("event".length()).trim();
        int fromMarker = details.indexOf("/from");
        int toMarker = details.indexOf("/to");
        if (fromMarker <= 0 || toMarker <= fromMarker) {
            printError("Use: event DESCRIPTION /from START /to END", bar);
            return;
        }

        String description = details.substring(0, fromMarker).trim();
        String from = details.substring(fromMarker + "/from".length(), toMarker).trim();
        String to = details.substring(toMarker + "/to".length()).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            printError("Use: event DESCRIPTION /from START /to END", bar);
            return;
        }
        if (description.contains("|") || from.contains("|") || to.contains("|")) {
            printError("Task details cannot contain '|'.", bar);
            return;
        }
        addTask(new EventTask(description, from, to), taskList, bar);
    }

    /** Adds a task and prints a confirmation containing its formatted representation. */
    private static void addTask(Task task, ArrayList<Task> taskList, String bar) {
        ArrayList<Task> updatedTaskList = new ArrayList<>(taskList);
        updatedTaskList.add(task);
        if (!saveTaskListData(updatedTaskList)) {
            printError("Unable to save the task. Please try again.", bar);
            return;
        }
        taskList.add(task);
        System.out.println("\t" + bar);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
        System.out.println("\t" + bar);
    }

    /** Marks or unmarks a task selected by a one-based task number. */
    private static void updateTaskStatus(String[] splitCommand, ArrayList<Task> taskList,
                                         boolean markAsComplete, String bar) {
        if (splitCommand.length != 2 || !splitCommand[1].matches("\\d+")) {
            printError("Use: " + (markAsComplete ? "mark" : "unmark") + " TASK_NUMBER", bar);
            return;
        }

        try {
            int index = Integer.parseInt(splitCommand[1]) - 1;
            if (index < 0 || index >= taskList.size()) {
                printError("That task number does not exist.", bar);
                return;
            }

            Task task = taskList.get(index);
            boolean wasComplete = task.isDone();
            System.out.println("\t" + bar);
            if (markAsComplete) {
                task.markAsComplete();
                System.out.println("\tNice! I've marked this task as done:");
            } else {
                task.markAsIncomplete();
                System.out.println("\tOK, I've marked this task as not done yet:");
            }
            if (!saveTaskListData(taskList)) {
                if (wasComplete) {
                    task.markAsComplete();
                } else {
                    task.markAsIncomplete();
                }
                printError("Unable to save the task update. Please try again.", bar);
                return;
            }
            System.out.println("\t  " + task);
            System.out.println("\t" + bar);
        } catch (NumberFormatException exception) {
            printError("That task number is too large.", bar);
        }
    }

    /** Deletes one task after its replacement task list has been saved successfully. */
    private static void deleteTask(String[] splitCommand, ArrayList<Task> taskList, String bar) {
        if (splitCommand.length != 2 || !splitCommand[1].matches("\\d+")) {
            printError("Use: delete TASK_NUMBER", bar);
            return;
        }
        try {
            int index = Integer.parseInt(splitCommand[1]) - 1;
            if (index < 0 || index >= taskList.size()) {
                printError("That task number does not exist.", bar);
                return;
            }

            Task task = taskList.get(index);
            ArrayList<Task> updatedTaskList = new ArrayList<>(taskList);
            updatedTaskList.remove(index);
            if (!saveTaskListData(updatedTaskList)) {
                printError("Unable to save the deletion. Please try again.", bar);
                return;
            }
            taskList.remove(index);
            System.out.println("\t" + bar);
            System.out.println("\tNoted: I have deleted this task:");
            System.out.println("\t\t" + task.toString());
            System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
            System.out.println("\t" + bar);


        } catch (NumberFormatException ex) {
            printError("That task number is too large.", bar);
        }
    }

    /** Prints a bordered error message. */
    private static void printError(String message, String bar) {
        System.out.println("\t" + bar);
        System.out.println("\t" + message);
        System.out.println("\t" + bar);
    }
}
