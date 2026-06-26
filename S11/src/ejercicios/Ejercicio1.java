package ejercicios;

/**
 * Ejercicio 1: Tabla hash sin colisiones - análisis de función hash.
 * Tabla de tamaño 11 (primo), h(x) = x % 11.
 * Arreglo de enteros inicializado en -1 (posición vacía).
 */
public class Ejercicio1 {

    static final int M = 11;

    static int hash(int x) {
        return x % M;
    }

    public static void main(String[] args) {
        int[] tabla = new int[M];
        for (int i = 0; i < M; i++) {
            tabla[i] = -1; // -1 representa posición vacía
        }

        int[] valores = {3, 14, 25, 36, 47, 58};

        System.out.println("===== Cálculo manual de direcciones hash =====");
        for (int v : valores) {
            int h = hash(v);
            System.out.println("h(" + v + ") = " + v + " % " + M + " = " + h);
            if (tabla[h] != -1) {
                System.out.println("  -> ¡Colisión! la posición " + h + " ya está ocupada por " + tabla[h]);
            } else {
                tabla[h] = v;
            }
        }

        System.out.println("\n===== Tabla hash final =====");
        for (int i = 0; i < M; i++) {
            System.out.println("Índice " + i + ": " + (tabla[i] == -1 ? "(vacío)" : tabla[i]));
        }

        System.out.println("\nPosiciones vacías:");
        for (int i = 0; i < M; i++) {
            if (tabla[i] == -1) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        /*
         * Análisis manual:
         * h(3)  = 3 % 11 = 3
         * h(14) = 14 % 11 = 3   -> aquí SÍ habría colisión con 3 si M no fuera adecuado,
         *                          pero nótese que 14 mod 11 = 3, que coincide con 3 mod 11 = 3.
         * h(25) = 25 % 11 = 3
         * h(36) = 36 % 11 = 3
         * h(47) = 47 % 11 = 3
         * h(58) = 58 % 11 = 3
         *
         * En este conjunto particular TODOS los valores son congruentes módulo 11
         * (3, 14, 25, 36, 47, 58 difieren en 11 cada uno), por lo que TODOS colisionan
         * en la posición 3, sin importar que 11 sea primo. Esto demuestra que un número
         * primo como tamaño de tabla reduce colisiones para datos con patrones
         * aritméticos regulares (por ejemplo claves que son múltiplos de un mismo paso),
         * pero no las elimina si las claves fueron elegidas exactamente como un
         * patrón aritmético con paso múltiplo de M. Para conjuntos de claves más
         * "naturales" (no construidos como progresión aritmética de paso M), un M primo
         * sí distribuye mucho mejor las claves entre los índices, porque evita que
         * factores comunes entre las claves y el tamaño de tabla concentren los
         * resultados en pocos índices (lo que sí ocurre con tamaños no primos,
         * como potencias de 2, ante claves con patrones de bits regulares).
         */
    }
}
