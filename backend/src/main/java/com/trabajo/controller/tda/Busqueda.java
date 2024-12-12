package com.trabajo.controller.tda;


import java.util.Arrays;

public class Busqueda {

    public Integer linearSearch(Integer[] array, Integer target, Integer type) {
        for (int i = 0; i < array.length; i++) {
            if (compare(array[i], target, type)) {
                return i;
            }
        }
        return -1;
    }

    public Integer binarySearch(Integer[] array, Integer target, Integer type) {
        Arrays.sort(array); 
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compareResult = array[mid].compareTo(target);

            if (compareResult == 0) {
                return mid; 
            }
            if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; 
    }

    private boolean compare(Integer a, Integer b, Integer type) {
        return (type == 1) ? a.equals(b) : a.compareTo(b) >= 0;
    }
}
