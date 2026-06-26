package ejercicios;

import hash.HashC;
import hash.Register;

/**
 * Ejercicio 4: Eliminación lógica y reinserción en hash cerrado.
 * Tabla de tamaño 7, sondeo lineal, h(x) = x % 7.
 * Inserta: 5, 12, 19, 26. Elimina 12. Busca 19. Reinserta 33.
 */
public class Ejercicio4 {
    public static void main(String[] args) {

        HashC<String> tabla = new HashC<>(7);

        System.out.println("===== Insertando 5, 12, 19, 26 =====");
        // h(5)=5, h(12)=5 (colisión, avanza a 6), h(19)=5 (colisión, avanza a 0), h(26)=5 (colisión, avanza a 1)
        tabla.insert(new Register<>(5, "dato5"));
        tabla.insert(new Register<>(12, "dato12"));
        tabla.insert(new Register<>(19, "dato19"));
        tabla.insert(new Register<>(26, "dato26"));
        tabla.printTable();

        System.out.println("\n===== Eliminando lógicamente la clave 12 =====");
        tabla.delete(12);
        tabla.printTable();
        // La celda de la clave 12 queda marcada como DELETED, no se borra el objeto Element.

        System.out.println("\n===== Buscando la clave 19 después de eliminar 12 =====");
        Register<String> r = tabla.search(19);
        System.out.println(r != null ? "Encontrado: " + r : "No encontrado");
        /*
         * La celda DELETED NO debe detener el sondeo porque significa "aquí hubo un
         * elemento, pero ya no", no "la cadena de búsqueda termina aquí". Si la
         * búsqueda se detuviera en una celda DELETED, no podría encontrar elementos
         * que están más adelante en la secuencia de sondeo pero que fueron insertados
         * ANTES de que esa celda se vaciara. Solo una celda EMPTY garantiza que, para
         * esa clave, ya no hay más posiciones que revisar.
         */

        System.out.println("\n===== Reinsertando la clave 33 =====");
        tabla.insert(new Register<>(33, "dato33"));
        tabla.printTable();
        // h(33) = 33 % 7 = 5, que está ocupada; al avanzar llega a la celda que antes
        // tenía la clave 12 (ahora DELETED) y la reutiliza, demostrando que las celdas
        // DELETED sí pueden reutilizarse para nuevas inserciones.

        /*
         * Diferencia entre eliminación lógica y física:
         * - Lógica: solo se cambia el estado de la celda a DELETED; el objeto Register
         *   puede seguir en memoria, pero ya no se considera válido para búsquedas.
         *   Permite reutilizar la celda en futuras inserciones SIN romper las cadenas
         *   de sondeo de otras claves que pasaron por esa posición.
         * - Física: se borra realmente el contenido de la celda y se marca como EMPTY.
         *   Esto es peligroso en hash cerrado porque una celda EMPTY detiene la
         *   búsqueda de otras claves que colisionaron y avanzaron más allá de esa
         *   posición, pudiendo hacer que búsquedas válidas fallen incorrectamente.
         *
         * ¿Cuándo conviene cada una?
         * - La eliminación lógica es la apropiada en hash cerrado con sondeo, porque
         *   preserva la integridad de las cadenas de sondeo de otras claves.
         * - La eliminación física solo es segura en hash cerrado si se reorganiza
         *   (rehashing) toda la cadena de sondeo afectada, o se usa en estructuras
         *   donde no hay ese problema (por ejemplo, hash abierto/encadenamiento,
         *   donde eliminar un nodo de la lista no afecta a otros nodos).
         */
    }
}
