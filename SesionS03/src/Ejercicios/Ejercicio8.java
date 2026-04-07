package Ejercicios;

public class Ejercicio8 {
    // Merge Sort mejorado usando un solo arreglo auxiliar
    public static void mergeSort(int[] arr) {
        int[] aux = new int[arr.length]; // arreglo auxiliar único
        mergeSort(arr, aux, 0, arr.length - 1);
    }
    private static void mergeSort(int[] arr, int[] aux, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, aux, left, mid);      // ordenar izquierda
            mergeSort(arr, aux, mid + 1, right); // ordenar derecha
            merge(arr, aux, left, mid, right);   // fusionar
        }
    }
    private static void merge(int[] arr, int[] aux, int left, int mid, int right) {
        // copiar al auxiliar
        for (int k = left; k <= right; k++) aux[k] = arr[k];

        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > right) arr[k] = aux[i++];
            else if (aux[i] <= aux[j]) arr[k] = aux[i++];
            else arr[k] = aux[j++];
        }
    }
    // Imprime el arreglo
    public static void printArray(int[] arr) {
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Original:");
        printArray(arr);

        mergeSort(arr);

        System.out.println("Ordenado:");
        printArray(arr);
    }
    /*
    Explicación resumida:
    - Divide el arreglo recursivamente y fusiona subarreglos ordenados.
    - Complejidad temporal: O(n log n) -> log n niveles de división, O(n) fusión por nivel.
    - Complejidad espacial: O(n) -> arreglo auxiliar único.
    - Mejoras: menos copias que la versión clásica, más eficiente en memoria.
    */
}

