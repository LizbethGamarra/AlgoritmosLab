package Ejercicios;

public class Ejercicio2 {

    public static int conteoI(int[] v, int n) {

        int conteo = 0;                 // O(1)

        for (int i = 0; i <= n - 2; i++) {        // O(n)
            for (int j = i + 1; j <= n - 1; j++) { // O(n)
                if (v[i] == v[j]) {              // O(1)
                    conteo = conteo + 1;         // O(1)
                }
            }
        }

        return conteo;                 // O(1)
    }
    //complejidad: O(n^2)
}