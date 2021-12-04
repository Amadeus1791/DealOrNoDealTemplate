import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    // colors for the console font
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_YELLOW = "\u001B[33m";
    // declaring TEXT_RESET to reset the color
    private static final String TEXT_RESET = "\u001B[0m";
    private static final Scanner scan = new Scanner(System.in);


    public static void greetPlayer() {
        printHeadline("Welcome to DEAL OR NO DEAL");
    }

    public static boolean askPlayerForDebugging() {
        System.out.print("Please press D/d for a debugging session or press any other key to start the game: ");
        return scan.nextLine().equalsIgnoreCase("d");
    }

    public static int getNumberFromUser(boolean askForPrizeSuitcase, Case suitcase) {
        int numb = 0;
        boolean inputTrue = false;
        while (!inputTrue) {
            try {
                if (askForPrizeSuitcase) {
                    System.out.print("Please choose your suitcase 1-26 with your price: ");
                } else {
                    System.out.print("Pick a suitcase to eliminate form the game: ");
                }
                numb = scan.nextInt();
                if (numb < 1 || numb > 26) {
                    printErrorMessage("This number is out of range, please try again.");
                }
                if (suitcase.isTaken(numb)) {
                    Player.printErrorMessage("Sorry, this suitcase is already taken, please try again.");
                } else if (numb >= 1 && numb <= 26) {
                    inputTrue = true;
                }
            } catch (InputMismatchException e) {
                printErrorMessage("Please type in a number!");
                scan.nextLine();
            }
        }
        if (askForPrizeSuitcase) {
            System.out.println("You chose number " + numb + ".");
        }
        return numb;
    }

    public static String askPlayerYesOrNo(String message) {
        String answer;
        while (true) {
            System.out.println(message);
            System.out.print("Please press Y/y for YES and N/n for NO: ");
            answer = scan.next();
            if (answer.equalsIgnoreCase("y")) {
                return "y";
            } else if (answer.equalsIgnoreCase("n")) {
                return "n";
            } else {
                printErrorMessage("Please type in a valid character.");
            }
        }
    }

    public static void printEnding(long value) {
        if (value == 0) {
            System.out.println("Congratulations, you won " + 1 + "¢.");
        } else {
            System.out.println("Congratulations, you won " + value/100 + "$.");
        }
        System.out.println("GAME OVER");
        printHeadline("Thank you for playing DEAL OR NO DEAL");
        System.out.println("Please try again at another time, bye bye!");
    }

    public static void printErrorMessage(String message) {
        System.out.println(TEXT_RED + message + TEXT_RESET);
    }

    public static void printHeadline(String message) {
        System.out.println(TEXT_YELLOW + "$$$$$$$$ " + message + " $$$$$$$$" + TEXT_RESET);
    }
}


