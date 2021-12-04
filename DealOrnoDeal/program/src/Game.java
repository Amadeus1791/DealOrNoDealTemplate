public class Game {
    private Case suitcase;
    private long bankOffer = 0;
    private int currentRound = 1;
    private int remainingSuitcases = 26;
    private int chosenCase = 0;
    private boolean deal = false;
    private final static int MAX_ROUNDS = 9;

    public void startGame() {
        Player.greetPlayer();
        if (Player.askPlayerForDebugging()) {
            suitcase = new Case(false);
        } else {
            suitcase = new Case(true);
        }
        suitcase.printAvailableCases();
        chosenCase = Player.getNumberFromUser(true, suitcase);
        suitcase.setRemoved(chosenCase);
        startAllRounds();
        if (deal) {
            performEarlyDeal();
        } else {
            requestSwitchingCases();
            // get input for asking if the user wants to switch the case
            System.out.println("\nOpening suitcase number " + chosenCase+".");
            long prize = suitcase.getValue(chosenCase);
            Player.printEnding(prize);
        }
    }

    private void requestSwitchingCases() {
        int lastCase = suitcase.findLastRemainingCase();
        System.out.println("The suitcase with your price was number " + chosenCase + ".");
        System.out.println("Suitcase number " + lastCase + " is the last one remaining.");
        System.out.println("Now we give you a chance to switch to this suitcase.");
        String question = Player.askPlayerYesOrNo("Do you want to switch suitcases?");
        if (question.equals("y")) {
            System.out.println();
            System.out.println("Switching from suitcase " + chosenCase + " to " + lastCase+".");
            chosenCase = lastCase;
        }
    }

    public void startAllRounds() {
        int numberOfEliminations = 6;
        while (currentRound <= MAX_ROUNDS && !deal) {
            suitcase.printAvailableCases();
            startCurrentRound(numberOfEliminations);
            bankOffer = Bank.calculateBankOffer(suitcase, chosenCase, remainingSuitcases, currentRound);
            Bank.printBankOffer(bankOffer);
            String answer = Player.askPlayerYesOrNo("Do you accept the offer?");
            if (answer.equals("y")) {
                deal = true;
            } else {            //question was NO
                currentRound++;
                if (currentRound < 6) {
                    numberOfEliminations--;
                } else {
                    numberOfEliminations = 1;
                }
            }
        }
    }

    public void startCurrentRound(int numberOfEliminations) {
        int selectedCaseNumber;
        while (numberOfEliminations > 0) {
            selectedCaseNumber = Player.getNumberFromUser(false, suitcase);
            eliminateCase(selectedCaseNumber);
            suitcase.setRemoved(selectedCaseNumber);
            numberOfEliminations--;
            remainingSuitcases--;
        }
    }

    private void eliminateCase(int selectedCaseNumber) {
        System.out.println("Case " + selectedCaseNumber + " was eliminated.");
        long value = suitcase.getValue(selectedCaseNumber)/ 100;
        if (value == 0) {
            System.out.println("It contains 1¢.\n ");
        } else {
            System.out.println("It contains " + value+ "$.\n ");
        }
    }

    public void performEarlyDeal() {
        System.out.println("You decided to end the game.");
        Player.printEnding(bankOffer);
    }
}



