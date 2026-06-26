package ejercicios;

/**
 * Ejercicio 5: Factor de carga y redimensionamiento de tabla hash.
 * Tabla hash cerrada de tamaño inicial 7, sondeo lineal.
 * Inserta: 2, 9, 16, 23, 4, 11. Calcula el factor de carga (alpha = n / M)
 * después de cada inserción. Si alpha > 0.75, hace rehashing a tamaño 17.
 */
public class Ejercicio5 {

    static int[] tabla;
    static int M;
    static int n; // número de elementos insertados

    public static void main(String[] args) {

        M = 7;
        tabla = new int[M];
        for (int i = 0; i < M; i++) tabla[i] = -1;
        n = 0;

        int[] valores = {2, 9, 16, 23, 4, 11};

        for (int v : valores) {
            insertar(v);
            double alpha = (double) n / M;
            System.out.printf("Insertado %d -> n=%d, M=%d, factor de carga = %.3f%n", v, n, M, alpha);

            if (alpha > 0.75) {
                rehash();
            }
        }

        System.out.println("\n===== Estado final de la tabla =====");
        imprimirTabla();

        /*
         * Por qué cambian las posiciones de los elementos al hacer rehashing:
         * la función hash usa el tamaño de la tabla (h(x) = x % M). Al cambiar M
         * de 7 a 17, el resultado de x % M cambia para casi todas las claves, así
         * que cada elemento debe recalcular su posición e insertarse de nuevo en
         * la tabla nueva (no basta con copiar la tabla anterior).
         */
    }

    static void insertar(int valor) {
        int base = valor % M;
        int idx = base;
        do {
            if (tabla[idx] == -1) {
                tabla[idx] = valor;
                n++;
                return;
            }
            idx = (idx + 1) % M;
        } while (idx != base);
        System.out.println("Tabla llena, no se pudo insertar " + valor);
    }

    static void rehash() {
        System.out.println("  -> Factor de carga supera 0.75: realizando rehashing a tamaño 17...");
        int[] anterior = tabla;
        int Manterior = M;

        M = 17; // siguiente primo razonable
        tabla = new int[M];
        for (int i = 0; i < M; i++) tabla[i] = -1;
        n = 0;

        for (int i = 0; i < Manterior; i++) {
            if (anterior[i] != -1) {
                insertar(anterior[i]);
            }
        }
        System.out.println("  -> Rehashing completado.");
        imprimirTabla();
    }

    static void imprimirTabla() {
        StringBuilder sb = new StringBuilder("Tabla (M=" + M + "): [");
        for (int i = 0; i < M; i++) {
            sb.append(tabla[i] == -1 ? "_" : tabla[i]);
            if (i != M - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }
}
