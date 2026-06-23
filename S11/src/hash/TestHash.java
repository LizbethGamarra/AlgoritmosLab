package hash;

/**
 * Actividad 2.4 – Prueba de HashC (hash cerrado con sondeo lineal).
 * Inserta: 34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34 (con diferentes nombres).
 */
public class TestHash {
    public static void main(String[] args) {
        System.out.println("════════════════════════════════════");
        System.out.println("  ACTIVIDAD 2 – Hash Cerrado (HashC)");
        System.out.println("════════════════════════════════════\n");

        HashC<String> hashC = new HashC<>(13); // primo > cantidad de elementos

        int[]    keys  = { 34,    3,      7,     30,     11,     8,
                            7,    23,     41,     16,     34 };
        String[] names = {"Ana","Carlos","Luis","Rosa","Pedro","Mia",
                          "Luis2","Carla","Jorge","Sofia","Ana2"};

        for (int i = 0; i < keys.length; i++) {
            hashC.insert(new Register<>(keys[i], names[i]));
        }

        System.out.println("\n--- Estado inicial de la tabla ---");
        hashC.printTable();

        // Búsqueda de clave 23
        System.out.println("--- Búsqueda clave 23 ---");
        Register<String> found = hashC.search(23);
        System.out.println("Resultado: " + (found != null ? found : "No encontrado"));

        // Eliminación lógica de clave 30
        System.out.println("\n--- Eliminación lógica clave 30 ---");
        hashC.delete(30);

        System.out.println("\n--- Estado final (después de eliminar 30) ---");
        hashC.printTable();

        // Verificar que el sondeo no se detiene en DELETED
        System.out.println("--- Búsqueda clave 23 (tras eliminar 30, verifica sondeo) ---");
        hashC.search(23);
    }
}