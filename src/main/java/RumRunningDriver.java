import java.util.Scanner;

/**
 * A driver class for rum running that executes a number of commands
 * provided by the user of the format "add X Y" or "remove X" where
 * X is one of "A", "B", or "C" and Y is an integer of the range
 * 0 <= Y <= 100.
 */
public class RumRunningDriver {
    public static void main(String[] args) {
        System.out.println("The rum running driver has started.");

        int numCommands = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of commands to to follow:");
        {
            if (in.hasNextInt()) {
                numCommands = in.nextInt();
            }
            else {
                System.out.println("Please enter a valid integer.");
            }
            if (numCommands < 0){
                System.out.println("Please enter a valid integer.");
            }
        }
        while (numCommands < 0);

        if (numCommands != 0){
            System.out.println("Enter your commands now:");
        }
        for (int i = 1; i <= numCommands; i++) {
            String command = in.next();
            if (command.toLowerCase().equals("add") || command.toLowerCase().equals("remove")) {
                if (command.toLowerCase().equals("add")) {
                    switch (in.next().toLowerCase()) {
                        case "a":
                            RumInventoryTunnel.addA(in.next());
                            break;
                        case "b":
                            RumInventoryTunnel.addB(in.next());
                            break;
                        case "c":
                            RumInventoryTunnel.addC(in.next());
                            break;
                        default:
                            System.out.println("Command " + Integer.toString(i) + " is invalid");
                    }
                } else {
                    switch (in.next().toLowerCase()) {
                        case "a":
                            RumInventoryTunnel.removeA();
                            break;
                        case "b":
                            RumInventoryTunnel.removeB();
                            break;
                        case "c":
                            RumInventoryTunnel.removeC();
                            break;
                        default:
                            System.out.println("Command " + Integer.toString(i) + " is invalid");
                    }
                }
            }
            else {
                System.out.println("Command " + Integer.toString(i) + " is invalid");
            }
        }
        System.out.println("Commands complete.");
    }
}
