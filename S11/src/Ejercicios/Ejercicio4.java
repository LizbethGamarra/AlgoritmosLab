package Ejercicios;
import hash.*;

/**
 * Ejercicio 4 – Eliminación lógica y reinserción en hash cerrado.
 * Tabla tamaño 7. h(x) = x % 7.
 * Claves: 5, 12, 19, 26  (todas → índice 5 con sondeo lineal).
 */
public class Ejercicio4 {

    public static void main(String[] args) {
        System.out.println("════════════════════════════════════");
        System.out.println("  EJERCICIO 4 – Eliminación lógica y reinserción");
        System.out.println("════════════════════════════════════\n");

        HashC<String> hashC = new HashC<>(7);

        // 5%7=5, 12%7=5(→6), 19%7=5(→6→0), 26%7=5(→6→0→1)
        hashC.insert(new Register<>(5,  "Alfa"));
        hashC.insert(new Register<>(12, "Beta"));
        hashC.insert(new Register<>(19, "Gama"));
        hashC.insert(new Register<>(26, "Delta"));

        System.out.println("\n--- Estado inicial ---");
        hashC.printTable();

        // 1. Eliminación lógica de clave 12
        System.out.println("1) Eliminación lógica de clave 12:");
        hashC.delete(12);
        System.out.println("\n   Estado tras eliminar 12:");
        hashC.printTable();

        // 2. Buscar clave 19 (pasa sobre DELETED sin detenerse)
        System.out.println("2) Búsqueda de clave 19 (el sondeo pasa sobre celda DELETED):");
        Register<String> r = hashC.search(19);
        System.out.println("   Resultado: " + (r != null ? r : "No encontrado"));
        System.out.println("   → La celda DELETED NO detiene el sondeo; solo EMPTY lo hace.");

        // 3. Reinsertar clave 33 (33%7=5 → sondea, reutiliza la celda DELETED en pos 6)
        System.out.println("\n3) Reinserción de clave 33 (reutiliza celda DELETED):");
        hashC.insert(new Register<>(33, "Épsilon"));
        System.out.println("\n   Estado final:");
        hashC.printTable();

        System.out.println("Diferencia eliminación lógica vs física:");
        System.out.println("  Lógica : marca la celda DELETED; mantiene intacta la cadena");
        System.out.println("           de sondeo. La celda puede reutilizarse en la próxima");
        System.out.println("           inserción. Coste O(1) por operación.");
        System.out.println("  Física : borra el elemento y debe reorganizar la cadena para");
        System.out.println("           no romper búsquedas posteriores → costoso O(n).");
        System.out.println("  Usar física: cuando el % de DELETED es muy alto (rehashing).");
    }
}