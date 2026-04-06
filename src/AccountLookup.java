import java.util.Arrays;

public class AccountLookup {
    // Linear Search: finds first occurrence and counts comparisons
    public static void linearSearch(String[] logs, String target) {
        int comparisons = 0;
        int index = -1;
        for (int i = 0; i < logs.length; i++) {
            comparisons++;
            if (logs[i].equals(target)) {
                index = i;
                break;
            }
        }
        System.out.println("Linear Search: Index " + index + " (" + comparisons + " comparisons)");
    }

    // Binary Search: requires sorted input, finds index and total count
    public static void binarySearch(String[] logs, String target) {
        Arrays.sort(logs); // Ensure data is sorted
        int low = 0, high = logs.length - 1;
        int comparisons = 0;
        int foundIndex = -1;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            int res = target.compareTo(logs[mid]);

            if (res == 0) {
                foundIndex = mid;
                break;
            } else if (res > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Counting occurrences
        int count = 0;
        if (foundIndex != -1) {
            for (String log : logs) {
                if (log.equals(target)) count++;
            }
        }

        System.out.println("Binary Search: Index " + foundIndex + " (" + comparisons + " comparisons), Count: " + count);
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};
        System.out.println("Searching for 'accB' in: " + Arrays.toString(logs));
        linearSearch(logs, "accB");
        binarySearch(logs, "accB");
    }
}