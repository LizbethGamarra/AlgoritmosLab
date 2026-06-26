package ejercicios;

import hash.HashO;
import hash.Register;

/**
 * Ejercicio 3: Tabla hash abierta con colisiones múltiples.
 * Tamaño 7, h(k) = k % 7.
 * Inserta: (10,"Juan"), (17,"Ana"), (24,"Luis"), (31,"Rosa"), (5,"Pedro"), (12,"Carla").
 */
public class Ejercicio3 {
    public static void main(String[] args) {

        HashO<String> tabla = new HashO<>(7);

        tabla.insert(new Register<>(10, "Juan"));
        tabla.insert(new Register<>(17, "Ana"));
        tabla.insert(new Register<>(24, "Luis"));
        tabla.insert(new Register<>(31, "Rosa"));
        tabla.insert(new Register<>(5, "Pedro"));
        tabla.insert(new Register<>(12, "Carla"));

        /*
         * Cálculo manual de índices (h(k) = k % 7):
         * h(10) = 3   h(17) = 3   h(24) = 3   h(31) = 3
         * h(5)  = 5   h(12) = 5
         *
         * Colisiones: 10, 17, 24 y 31 colisionan todos en el índice 3
         *             5 y 12 colisionan en el índice 5
         * Índices 0,1,2,4,6 quedan vacíos.
         */

        System.out.println("\n===== Estado final de la tabla =====");
        tabla.printTable();

        System.out.println("\n===== Buscando la clave 24 =====");
        Register<String> r = tabla.search(24);
        // 24 está en el índice 3, y es el 3er nodo de esa lista
        // (orden de inserción: 10 -> 17 -> 24 -> 31)
        System.out.println(r != null
                ? "Encontrado: " + r + " en el índice 3, tercer nodo de la lista (10 -> 17 -> 24 -> 31)"
                : "No encontrado");

        System.out.println("\n===== Eliminando la clave 17 =====");
        tabla.delete(17);
        tabla.printTable();
        // Antes de eliminar, la cadena del índice 3 tenía 4 nodos: 10 -> 17 -> 24 -> 31
        // Después de eliminar 17, quedan 3 nodos: 10 -> 24 -> 31
    }
}
