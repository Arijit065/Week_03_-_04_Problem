import java.util.Arrays;

public class RiskLookup {

    public static void linearSearch(int[] risks, int threshold) {
        int comparisons = 0;
        boolean found = false;
        for (int risk : risks) {
            comparisons++;
            if (risk == threshold) {
                found = true;
                break;
            }
        }
        System.out.println("Linear Search: threshold=" + threshold +
                (found ? " -> found" : " -> not found") + " (" + comparisons + " comps)");
    }

    public static void binarySearchFloorCeiling(int[] risks, int threshold) {
        int low = 0, high = risks.length - 1;
        int floor = -1, ceiling = -1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;

            if (risks[mid] == threshold) {
                floor = ceiling = risks[mid];
                break;
            } else if (risks[mid] < threshold) {
                floor = risks[mid];
                low = mid + 1;
            } else {
                ceiling = risks[mid];
                high = mid - 1;
            }
        }

        System.out.println("Binary floor(" + threshold + "): " + (floor == -1 ? "None" : floor) +
                ", ceiling: " + (ceiling == -1 ? "None" : ceiling) + " (" + comparisons + " comps)");
    }

    public static void main(String[] args) {
        int[] sortedRisks = {10, 25, 50, 100};
        int target = 30;

        System.out.println("Sorted risks: " + Arrays.toString(sortedRisks));

        linearSearch(sortedRisks, target);


        binarySearchFloorCeiling(sortedRisks, target);
    }
}