import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp; // format: HH:mm

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionSorter {

    // 🔹 Bubble Sort (Stable)
    public static void bubbleSortByFee(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            // Early termination
            if (!swapped) break;
        }

        System.out.println("Bubble Sort Result: " + list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // 🔹 Insertion Sort (Stable)
    public static void insertionSortByFeeAndTimestamp(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    (list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).timestamp.compareTo(key.timestamp) > 0))) {

                list.set(j + 1, list.get(j)); // shift right
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort Result: " + list);
    }

    // 🔹 High-fee Outliers
    public static void findHighFeeOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }

        System.out.println("High-fee Outliers (>50): " + outliers);
    }

    // 🔹 Controller Method
    public static void processTransactions(List<Transaction> transactions) {
        int size = transactions.size();

        if (size <= 100) {
            bubbleSortByFee(transactions);
        } else if (size <= 1000) {
            insertionSortByFeeAndTimestamp(transactions);
        } else {
            System.out.println("Large dataset: Consider Merge Sort or Quick Sort.");
        }

        findHighFeeOutliers(transactions);
    }

    // 🔹 Main Method (Sample Input)
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        processTransactions(transactions);
    }
}