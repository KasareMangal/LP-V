package LP5;

import java.util.Scanner;
import java.util.Arrays;

public class ParallelReduction {

    public static void minReduction(int[] arr) {
        int minValue = Arrays.stream(arr).parallel().min().getAsInt();
        System.out.println("Minimum value: " + minValue);
    }

    public static void maxReduction(int[] arr) {
        int maxValue = Arrays.stream(arr).parallel().max().getAsInt();
        System.out.println("Maximum value: " + maxValue);
    }

    public static void sumReduction(int[] arr) {
        int sum = Arrays.stream(arr).parallel().sum();
        System.out.println("Sum: " + sum);
    }

    public static void averageReduction(int[] arr) {
        double average = Arrays.stream(arr).parallel().average().getAsDouble();
        System.out.println("Average: " + average);
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

        minReduction(arr);
        maxReduction(arr);
        sumReduction(arr);
        averageReduction(arr);

        sc.close();
    }
}

