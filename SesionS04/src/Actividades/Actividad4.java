package Actividades;

import java.util.*;

public class Actividad4 {

    public static void mergeSort(int[] arr, int izq, int der) {
        if (izq < der) {
            int mid = (izq + der) / 2;

            mergeSort(arr, izq, mid);
            mergeSort(arr, mid + 1, der);

            merge(arr, izq, mid, der);
        }
    }

    public static void merge(int[] arr, int izq, int mid, int der) {
        int[] temp = new int[der - izq + 1];

        int i = izq, j = mid + 1, k = 0;

        while (i <= mid && j <= der) {
            if (arr[i] < arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        while (i <= mid)
            temp[k++] = arr[i++];

        while (j <= der)
            temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++)
            arr[izq + x] = temp[x];
    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 5, 2};

        System.out.println("Antes: " + Arrays.toString(arr));

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("Después: " + Arrays.toString(arr));
    }
}