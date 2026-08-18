import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the Duke task manager command-line application.
 */
public class Duke {
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
                System.out.println("\t" + BAR);
                System.out.println("\t" + "Bye. Hope to see you again soon!");
                System.out.println("\t" + BAR);
                break;
            } else if (commandWord.equals("list")) {
                listTasks(taskList, BAR);
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
            } else {
                printError("Hey pal, I don't understand what you're saying.", BAR);
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
        addTask(new EventTask(description, from, to), taskList, bar);
    }

    /** Adds a task and prints a confirmation containing its formatted representation. */
    private static void addTask(Task task, ArrayList<Task> taskList, String bar) {
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
            System.out.println("\t" + bar);
            if (markAsComplete) {
                task.markAsComplete();
                System.out.println("\tNice! I've marked this task as done:");
            } else {
                task.markAsIncomplete();
                System.out.println("\tOK, I've marked this task as not done yet:");
            }
            System.out.println("\t  " + task);
            System.out.println("\t" + bar);
        } catch (NumberFormatException exception) {
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
