package Ejercicios;

public class Ejercicio7 {
    // Método para ordenar el arreglo usando Merge Sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Ordenar mitad izquierda (recursión)
            mergeSort(arr, left, mid);

            // Ordenar mitad derecha (recursión)
            mergeSort(arr, mid + 1, right);

            // Combinar las dos mitades ordenadas
            merge(arr, left, mid, right);
        }
    }

    // Método para fusionar dos subarreglos ordenados
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copiar datos a arreglos temporales
        for (int i = 0; i < n1; i++)
            leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // Mezclar los dos arreglos
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copiar lo que sobra del arreglo izquierdo
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copiar lo que sobra del arreglo derecho
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // Imprime el arreglo
    public static void printArray(int[] arr) {
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Original:");
        printArray(arr);

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("Ordenado:");
        printArray(arr);
    }
    /*
    COMPLEJIDAD:
    - Dividimos el arreglo en dos mitades (log n divisiones).
    - En cada nivel combinamos todos los elementos (n operaciones).
    - Total: O(n log n)
    */
}
