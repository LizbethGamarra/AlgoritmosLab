package Ejercicios;
import java.util.HashMap;
import java.util.Map;

public class Ejercicio3 {

    public static int moda(int[] v) {
        Map<Integer, Integer> frecuencia = new HashMap<>(); // O(1) inicializar

        int maxFrecuencia = 0; // O(1)
        int moda = v[0];       // O(1)

        for (int num : v) { // O(n)
            // Obtener frecuencia actual y aumentar en 1
            int f = frecuencia.getOrDefault(num, 0) + 1; // O(1) promedio
            frecuencia.put(num, f);                       // O(1) promedio

            // Actualizar moda si es necesario
            if (f > maxFrecuencia) { // O(1)
                maxFrecuencia = f;   // O(1)
                moda = num;          // O(1)
            }
        }

        return moda; // O(1)
    }

    public static void main(String[] args) {

        int[] datos = {1, 3, 2, 3, 4, 3, 2, 1, 3}; // O(1)
        int resultado = moda(datos);                // O(n)
        System.out.println("La moda es: " + resultado); // O(1)
    }
    //complejidad: O(n)
}