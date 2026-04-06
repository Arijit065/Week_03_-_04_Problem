class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return String.format("%s: %.1f%% (Vol: %.2f)", name, returnRate, volatility);
    }
}

public class PortfolioSorting {
    // 1. Merge Sort (Ascending)
    public static void mergeSort(Asset[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(Asset[] arr, int l, int m, int r) {
        Asset[] temp = new Asset[r - l + 1];
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r) {
            if (arr[i].returnRate <= arr[j].returnRate) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, l, temp.length);
    }

    // 2. Quick Sort (DESC Return + ASC Volatility)
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Asset[] arr, int low, int high) {
        // Median-of-three logic
        int mid = low + (high - low) / 2;
        Asset pivot = arr[mid];

        int i = low, j = high;
        while (i <= j) {
            // Sort by Return DESC, then Volatility ASC
            while (arr[i].returnRate > pivot.returnRate || (arr[i].returnRate == pivot.returnRate && arr[i].volatility < pivot.volatility)) i++;
            while (arr[j].returnRate < pivot.returnRate || (arr[j].returnRate == pivot.returnRate && arr[j].volatility > pivot.volatility)) j--;

            if (i <= j) {
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12.0, 0.5),
                new Asset("TSLA", 8.0, 0.8),
                new Asset("GOOG", 15.0, 0.4),
                new Asset("MSFT", 12.0, 0.3)
        };

        System.out.println("Original: " + java.util.Arrays.toString(assets));

        mergeSort(assets, 0, assets.length - 1);
        System.out.println("Merge Sort (Asc): " + java.util.Arrays.toString(assets));

        quickSort(assets, 0, assets.length - 1);
        System.out.println("Quick Sort (Desc Return/Asc Vol): " + java.util.Arrays.toString(assets));
    }
}