package Ejercicios;
import java.util.*;

public class Ejercicio2 {
    public static int quickSelect(int[] arr, int k) {
        return seleccionar(arr, 0, arr.length - 1, k);
    }
    private static int seleccionar(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        int pivote = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] > pivote) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        int count = i - left + 1;
        if (k == count) {
            return arr[i];
        } else if (k < count) {
            return seleccionar(arr, left, i - 1, k);
        } else {
            return seleccionar(arr, i + 1, right, k - count);
        }
    }
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void main(String[] args) {
        int[] arr = {4, 2, 7, 10, 4, 17};
        int k = 3;
        System.out.println(quickSelect(arr, k)); 
    }
}