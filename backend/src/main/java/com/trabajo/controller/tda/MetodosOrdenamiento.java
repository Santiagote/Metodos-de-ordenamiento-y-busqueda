package com.trabajo.controller.tda;

import java.util.Arrays;

public class MetodosOrdenamiento {

    public Integer[] quickSort(Integer[] array, Integer type) {
        if (array == null || array.length == 0) return array;

        quickSortRecursive(array, 0, array.length - 1, type);
        return array;
    }

    private void quickSortRecursive(Integer[] arr, int low, int high, Integer type) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high, type);
            quickSortRecursive(arr, low, partitionIndex - 1, type);
            quickSortRecursive(arr, partitionIndex + 1, high, type);
        }
    }

    private int partition(Integer[] arr, int low, int high, Integer type) {
        Integer pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot, type)) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // MergeSort
    public Integer[] mergeSort(Integer[] array, Integer type) {
        if (array == null || array.length <= 1) return array;

        return mergeSortRecursive(array, type);
    }

    private Integer[] mergeSortRecursive(Integer[] array, Integer type) {
        if (array.length <= 1) return array;

        int mid = array.length / 2;
        Integer[] left = Arrays.copyOfRange(array, 0, mid);
        Integer[] right = Arrays.copyOfRange(array, mid, array.length);

        left = mergeSortRecursive(left, type);
        right = mergeSortRecursive(right, type);

        return merge(left, right, type);
    }

    private Integer[] merge(Integer[] left, Integer[] right, Integer type) {
        Integer[] merged = new Integer[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (compare(left[i], right[j], type)) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }

        while (i < left.length) merged[k++] = left[i++];
        while (j < right.length) merged[k++] = right[j++];

        return merged;
    }

    // ShellSort
    public Integer[] shellSort(Integer[] array, Integer type) {
        if (array == null || array.length == 0) return array;

        int n = array.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Integer temp = array[i];
                int j;
                for (j = i; j >= gap && compare(array[j - gap], temp, type); j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }

        return array;
    }

    private Boolean compare(Integer a, Integer b, Integer type) {
        return (type == 1) ? a <= b : a >= b;
    }

    private void swap(Integer[] array, int i, int j) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
