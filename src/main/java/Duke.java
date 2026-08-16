import java.util.ArrayList;
import java.util.Scanner;

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

        ArrayList<Task> taskList = new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            String[] splitCommand = command.trim().split("\\s+", 2);
            if (command.equals("bye")) {
                System.out.println("\t" + BAR);
                System.out.println("\t" + "Bye. Hope to see you again soon!");
                System.out.println("\t" + BAR);
                break;
            } else if (command.equals("list")) {
                System.out.println(("\t" + BAR));
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print("\t");
                    System.out.print(i + 1);
                    System.out.println(". " + taskList.get(i).toString());
                }
                System.out.println(("\t" + BAR));

            }else if (splitCommand[0].equals("mark") && splitCommand.length == 2 && splitCommand[1].chars().allMatch(Character::isDigit)) {
                int index = Integer.parseInt(splitCommand[1]) - 1;
                taskList.get(index).markAsComplete();
                System.out.println("\t" + BAR);
                System.out.println("\t" + "Nice! I've marked this task as done:");
                System.out.println("\t\t" + taskList.get(index).toString());
                System.out.println("\t" + BAR);
            }else if (splitCommand[0].equals("unmark") && splitCommand.length == 2 && splitCommand[1].chars().allMatch(Character::isDigit)) {
                int index = Integer.parseInt(splitCommand[1]) - 1;
                taskList.get(index).markAsIncomplete();
                System.out.println("\t" + BAR);
                System.out.println("\t" + "OK, I've marked this task as not done yet:");
                System.out.println("\t\t" + taskList.get(index).toString());
                System.out.println("\t" + BAR);
            }else {
                taskList.add(new Task(command));
                System.out.println("\t" + BAR);
                System.out.println("\t" + "added: " + command);
                System.out.println("\t" + BAR);
            }



        }
    }
}
