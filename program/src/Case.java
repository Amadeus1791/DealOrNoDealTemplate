import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Case {
    private List<Case> caseList;
    private static final int NUMB_OF_CASES = 27;
    private int caseNumber;
    private long value;
    private boolean removed;

    public Case(boolean shuffled) {
        if (shuffled)
            caseList = initSuitcaseList(true);
        else
            caseList = initSuitcaseList(false);
    }

    private Case(int caseNumber, long value) {
        this.caseNumber = caseNumber;
        this.value = value;
    }

    private Case(int caseNumber, long value, boolean removed) {
        this.caseNumber = caseNumber;
        this.value = value;
        this.removed = removed;
    }


    public List<Case> initSuitcaseList(boolean shuffled) {
        List<Case> newList = new ArrayList<>();
        long[] values = {-1, 1, 100, 500, 1000, 2500, 5000, 7500, //i: 0-7  index at zero is a dummy value
                10000, 20000, 30000, 40000, 50000, 75000,      // i: 8-13
                100000, 500000, 1000000, 2500000, 5000000, 7500000, //i: 14-19
                10000000, 20000000, 30000000, 40000000, 50000000, 75000000, //i: 20-25
                100000000}; //i: 26

        for (int i = 0; i < NUMB_OF_CASES; i++) {
            if (i == 0) {
                newList.add(new Case(i, values[i], true));  // dummy suitcase
            } else
                newList.add(new Case(i, values[i]));
        }
        if (shuffled) {
            shuffleArray(newList);
        }
        return newList;
    }

    private void shuffleArray(List<Case> arr) {
        Random rand = new Random();
        for (int i = 1; i < 27; ++i) {
            int randomIndex = 1 + rand.nextInt(26);  // add always 1 to avoid the dummy value on index 0
            // perform swap
            Collections.swap(arr, i, randomIndex);
        }
    }

    public void printAvailableCases() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 1; i < NUMB_OF_CASES; i++) {
            Case current = caseList.get(i);
            if (!current.removed) {
                sb.append("[").append(i).append("]");
            }
        }
        System.out.println(sb.toString());
    }

    public boolean isTaken(int index) {
        if (index < 1 || index > 26) {
            return false;
        }
        return caseList.get(index).removed;
    }

    public long totalValueOfAvailableCases(int chosenCase) {
        long sum = 0;
        for (int i = 1; i < NUMB_OF_CASES; i++) {
            Case current = caseList.get(i);
            if ((!current.removed) || i == chosenCase) {
                sum += current.value;
            }
        }
        return sum;
    }

    public int findLastRemainingCase() {
        for (int i = 1; i <= NUMB_OF_CASES; ++i) {
            if (!caseList.get(i).removed) {
                return i;
            }
        }
        return -1;
    }

    public long getValue(int index) {
        return caseList.get(index).value;
    }

    public void setRemoved(int index) {
        caseList.get(index).removed = true;
    }


    // For debugging
    @Override
    public String toString() {
        return "caseNumber=" + caseNumber +
                ", value=" + value + "";
    }

    public void printAllCases() {
        for (int i = 0; i < NUMB_OF_CASES; i++) {
            System.out.println("Position " + i + ": " + caseList.get(i).toString());
        }
    }
}
