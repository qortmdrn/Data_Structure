package sort;

public class BubbleSort {
    public static void sort(int[] array) {
        int size = array.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
// Usage example in main method:
// int[] arr = {5, 1, 4, 2, 8};
// BubbleSort.sort(arr);