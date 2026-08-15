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

        ArrayList<String> itemList = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("\t" + BAR);
                System.out.println("\t" + "Bye. Hope to see you again soon!");
                System.out.println("\t" + BAR);
                break;
            } else if (command.equals("list")) {
                System.out.println(("\t" + BAR));
                for (int i = 0; i < itemList.size(); i++) {
                    System.out.print("\t");
                    System.out.print(i + 1);
                    System.out.println(". " + itemList.get(i));
                }
                System.out.println(("\t" + BAR));

            }else {
                itemList.add(command);
                System.out.println("\t" + BAR);
                System.out.println("\t" + "added: " + command);
                System.out.println("\t" + BAR);
            }



        }
    }
}
