package sort;

public class RadixSort {
    // Main radix sort method
    public static void sort(int[] array) {
        int max = getMax(array);

        // Apply counting sort for every digit
        for (int place = 1; max / place > 0; place *= 10) {
            countingSort(array, place);
        }
    }

    private static int getMax(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) max = value;
        }
        return max;
    }

    // Counting sort by digit
    private static void countingSort(int[] array, int place) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Count occurrences
        for (int i = 0; i < n; i++) {
            int digit = (array[i] / place) % 10;
            count[digit]++;
        }

        // Cumulative count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build output array
        for (int i = n - 1; i >= 0; i--) {
            int digit = (array[i] / place) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        // Copy to original array
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
    }
}
// Usage example:
// int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
// RadixSort.sort(arr);