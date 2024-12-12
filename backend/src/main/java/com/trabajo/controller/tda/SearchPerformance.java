package com.trabajo.controller.tda;

import java.util.Arrays;
import java.util.Random;

public class SearchPerformance {
    private final int size = 25000;
    private final Integer[] arr = new Integer[size];
    private final Random random = new Random();

    public SearchPerformance() {
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(25000);
        }
    }

    public void evaluateSearchPerformance() {
        final Busqueda searcher = new Busqueda(); 

        System.out.println("Datos generados:");
        System.out.println(Arrays.toString(Arrays.copyOf(arr, 20)) + "...");
        System.out.println();

        System.out.println("+------------------+------------------+--------------------+");
        System.out.println("| Algoritmo        | Tiempo (ms)      | Resultado de la búsqueda |");
        System.out.println("+------------------+------------------+--------------------+");

        Integer target = arr[random.nextInt(size)];

        evaluateSearch(arr.clone(), "Búsqueda Lineal", new SearchAlgorithm() {
            @Override
            public Integer search(Integer[] array, Integer target, Integer type) {
                return searcher.linearSearch(array, target, type);
            }
        }, target);

        evaluateSearch(arr.clone(), "Búsqueda Binaria", new SearchAlgorithm() {
            @Override
            public Integer search(Integer[] array, Integer target, Integer type) {
                return searcher.binarySearch(array, target, type);
            }
        }, target);

        System.out.println("+------------------+------------------+--------------------+");
    }

    private void evaluateSearch(Integer[] input, String algorithmName, SearchAlgorithm algorithm, Integer target) {
        long startTime = System.currentTimeMillis();

        Integer result = algorithm.search(input, target, 1); 

        long endTime = System.currentTimeMillis();

        System.out.printf("| %-16s | %-16d | %-22d |\n", algorithmName, (endTime - startTime), result);
    }

    interface SearchAlgorithm {
        Integer search(Integer[] array, Integer target, Integer type);
    }

    public static void main(String[] args) {
        SearchPerformance performance = new SearchPerformance();
        performance.evaluateSearchPerformance();
    }
}
