package LP5;

import java.util.Scanner;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class MergeSort {

    static class MergeSortTask extends RecursiveAction {
        int[] arr;
        int left, right;

        public MergeSortTask(int[] arr, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (left < right) {
                int mid = (left + right) / 2;

                MergeSortTask leftTask = new MergeSortTask(arr, left, mid);
                MergeSortTask rightTask = new MergeSortTask(arr, mid + 1, right);

                invokeAll(leftTask, rightTask); // parallel recursive call

                merge(left, mid, right);
            }
        }

        private void merge(int left, int mid, int right) {
            int[] temp = new int[right - left + 1];
            int i = left, j = mid + 1, k = 0;

            while (i <= mid && j <= right) {
                if (arr[i] < arr[j]) {
                    temp[k++] = arr[i++];
                } else {
                    temp[k++] = arr[j++];
                }
            }
            while (i <= mid) {
                temp[k++] = arr[i++];
            }
            while (j <= right) {
                temp[k++] = arr[j++];
            }
            for (i = left, k = 0; i <= right; i++, k++) {
                arr[i] = temp[k];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter total number of elements => ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("\nEnter elements => ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        ForkJoinPool pool = new ForkJoinPool(); // Create a pool

        long startTime = System.nanoTime();
        pool.invoke(new MergeSortTask(arr, 0, n - 1));
        long endTime = System.nanoTime();

        System.out.println("\nSorted array is =>");
        for (int num : arr) {
            System.out.println(num);
        }

        double timeTaken = (endTime - startTime) / 1e6; // convert to milliseconds
        System.out.println("\nTime taken: " + timeTaken + " ms");

        sc.close();
    }
}

