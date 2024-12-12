package com.trabajo.controller.tda;

import java.util.Arrays;
import java.util.Random;

public class SortingPerformance {

    private final int size = 25000;
    private final int[] arr = new int[size];
    private final Random random = new Random();

    public SortingPerformance() {
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(25000);
        }
    }

    public void evaluateSortingPerformance() {
        final MetodosOrdenamiento sorter = new MetodosOrdenamiento(); 

        System.out.println("Datos generados: " + Arrays.toString(Arrays.copyOf(arr, 20)) + "...");

        System.out.println("+-----------------+------------------+");
        System.out.println("| Algoritmo       | Tiempo (ms)      |");
        System.out.println("+-----------------+------------------+");

        evaluateAlgorithm(arr.clone(), "QuickSort", new SortingAlgorithm() {
            @Override
            public Integer[] sort(Integer[] array, Integer type) {
                return sorter.quickSort(array, type);
            }
        });

        evaluateAlgorithm(arr.clone(), "MergeSort", new SortingAlgorithm() {
            @Override
            public Integer[] sort(Integer[] array, Integer type) {
                return sorter.mergeSort(array, type);
            }
        });

        evaluateAlgorithm(arr.clone(), "ShellSort", new SortingAlgorithm() {
            @Override
            public Integer[] sort(Integer[] array, Integer type) {
                return sorter.shellSort(array, type);
            }
        });

        System.out.println("+-----------------+------------------+");
    }

    private void evaluateAlgorithm(int[] input, String algorithmName, SortingAlgorithm algorithm) {
        long startTime = System.currentTimeMillis();

        Integer[] integerArray = new Integer[input.length];
        for (int i = 0; i < input.length; i++) {
            integerArray[i] = input[i];
        }

        Integer[] sortedArray = algorithm.sort(integerArray, 1);

        long endTime = System.currentTimeMillis();

        System.out.printf("| %-15s | %-16d |\n", algorithmName, (endTime - startTime));
    }

    interface SortingAlgorithm {
        Integer[] sort(Integer[] array, Integer type);
    }

    public static void main(String[] args) {
        SortingPerformance performance = new SortingPerformance();
        performance.evaluateSortingPerformance();
    }
}
