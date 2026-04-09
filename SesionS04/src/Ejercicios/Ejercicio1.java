package Ejercicios;

public class Ejercicio1 {
    public static boolean puedeAlcanzar(int[] arr, int objetivo) {
        int sumaObligatoria = 0;

        for (int num : arr) {
            if (num % 3 == 0) {
                sumaObligatoria += num;
            }
        }

        int nuevoObjetivo = objetivo - sumaObligatoria;

        return backtrack(arr, 0, nuevoObjetivo);
    }
    public static boolean backtrack(int[] arr, int i, int objetivo) {
        if (objetivo == 0) return true;
        if (i >= arr.length) return false;

        int actual = arr[i];

        if (actual % 3 == 0) {
            return backtrack(arr, i + 1, objetivo);
        }
        boolean puedeUsar = true;
        if (actual % 2 == 0 && i + 1 < arr.length && arr[i + 1] % 2 == 0) {
            puedeUsar = false;
        }
        if (puedeUsar && backtrack(arr, i + 1, objetivo - actual)) {
            return true;
        }
        return backtrack(arr, i + 1, objetivo);
    }
    public static void main(String[] args) {
        int[] arr = {3, 4, 6, 7, 2};
        int objetivo = 20;
        System.out.println(puedeAlcanzar(arr, objetivo)); // true
    }
}