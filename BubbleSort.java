package LP5;

import java.util.Scanner;
import java.util.stream.IntStream;

public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int phase = i % 2;

            // Parallel processing based on phase
            IntStream.range(phase, n - 1)
                    .parallel() // make it parallel
                    .filter(j -> j % 2 == phase) // select correct indices (even or odd)
                    .forEach(j -> {
                        synchronized (arr) { // synchronize to avoid race conditions
                            if (arr[j] > arr[j + 1]) {
                                swap(arr, j, j + 1);
                            }
                        }
                    });
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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

        bubbleSort(arr);

        System.out.println("\nSorted array is =>");
        for (int num : arr) {
            System.out.println(num);
        }

        sc.close();
    }
}

