package sort;

import java.util.ArrayList;
import java.util.Collections;

public class BucketSortFloat {
    public static void sort(float[] arr) {
        int n = arr.length;
        if (n <= 0) return;

        // Create n empty buckets
        ArrayList<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Put array elements in different buckets
        for (float num : arr) {
            int bucketIndex = (int) (num * n); // Assumes [0, 1)
            buckets[bucketIndex].add(num);
        }

        // Sort individual buckets and concatenate
        int index = 0;
        for (ArrayList<Float> bucket : buckets) {
            Collections.sort(bucket);
            for (float num : bucket) {
                arr[index++] = num;
            }
        }
    }
}
// Usage:
// float[] arr = {0.42f, 0.32f, 0.24f, 0.12f, 0.34f, 0.56f, 0.78f};
// BucketSortFloat.sort(arr);