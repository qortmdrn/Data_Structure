package sort;

public class CountingSort {
    // k is the maximum value in the input array
    public static int[] sort(int[] input, int k) {
        int[] count = new int[k + 1];
        int[] output = new int[input.length];

        // Count each element
        for (int value : input) {
            count[value]++;
        }

        // Cumulative count
        for (int i = 1; i <= k; i++) {
            count[i] += count[i - 1];
        }

        // Build output array
        for (int i = input.length - 1; i >= 0; i--) {
            output[count[input[i]] - 1] = input[i];
            count[input[i]]--;
        }

        return output;
    }
}
// Usage example:
// int[] arr = {4, 2, 2, 8, 3, 3, 1};
// int[] sorted = CountingSort.sort(arr, 8);