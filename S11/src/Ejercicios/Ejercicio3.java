package Ejercicios;
import hash.*;
/**
 * Ejercicio 3 – Tabla hash abierta con colisiones múltiples.
 * Tamaño 7. h(k) = k % 7.
 * Pares: (10,Juan),(17,Ana),(24,Luis),(31,Rosa),(5,Pedro),(12,Carla)
 */
public class Ejercicio3 {

    public static void main(String[] args) {
        System.out.println("════════════════════════════════════");
        System.out.println("  EJERCICIO 3 – Hash Abierto / Colisiones múltiples");
        System.out.println("════════════════════════════════════\n");

        HashO<String> hashO = new HashO<>(7);

        int[]    keys  = {10,    17,    24,    31,     5,      12};
        String[] names = {"Juan","Ana","Luis","Rosa","Pedro","Carla"};

        for (int i = 0; i < keys.length; i++) {
            hashO.insert(new Register<>(keys[i], names[i]));
        }

        System.out.println("\nAnálisis de colisiones (h(k)=k%7):");
        System.out.println("  10%7=3, 17%7=3, 24%7=3, 31%7=3  → índice 3 (4 colisiones)");
        System.out.println("   5%7=5, 12%7=5                  → índice 5 (2 colisiones)");

        System.out.println("\n--- Estado final de la tabla ---");
        hashO.printTable();

        // 1. Buscar clave 24
        System.out.println("1) Buscar clave 24:");
        Register<String> r = hashO.search(24);
        if (r != null)
            System.out.printf("   Resultado: %s  (índice %d)%n", r, 24 % 7);

        // 2. Eliminar clave 17
        System.out.println("\n2) Eliminar clave 17:");
        hashO.delete(17);
        System.out.println("   Cadena en índice 3 tras eliminar:");
        int len = hashO.getChainLength(3);
        for (int j = 0; j < len; j++)
            System.out.println("     → " + hashO.getElement(3, j));
        System.out.println("   Nodos restantes en índice 3: " + len);
    }
}