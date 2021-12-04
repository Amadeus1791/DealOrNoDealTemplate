public class Bank {

    public static long calculateBankOffer(Case suitcase, int chosenCase, int remainingCases, int currentRound) {
        long totalValue = suitcase.totalValueOfAvailableCases(chosenCase);
        return ((totalValue / remainingCases) * currentRound) / 10;
    }

    public static void printBankOffer(long value) {
        System.out.println("The bank offers you "+ value/100+ "$." );
    }


}
