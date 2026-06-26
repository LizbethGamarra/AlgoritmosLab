package hash;

/**
 * Clase de prueba para HashC.
 * Inserta: 34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34 (con distintos nombres),
 * muestra la tabla, elimina la clave 30, vuelve a mostrar la tabla y busca la clave 23.
 */
public class TestHash {
    public static void main(String[] args) {

        HashC<String> hashC = new HashC<>(13); // 13 es primo, tamaño fijo

        int[] claves = {34, 3, 7, 30, 11, 8, 7, 23, 41, 16, 34};
        String[] nombres = {
            "Ana", "Bruno", "Carla", "Diego", "Elena",
            "Franco", "Gabriela", "Hugo", "Iris", "Jorge", "Karen"
        };

        System.out.println("===== Insertando valores =====");
        for (int i = 0; i < claves.length; i++) {
            hashC.insert(new Register<>(claves[i], nombres[i]));
        }

        System.out.println("\n===== Tabla ANTES de eliminar la clave 30 =====");
        hashC.printTable();

        hashC.delete(30);

        System.out.println("\n===== Tabla DESPUÉS de eliminar la clave 30 =====");
        hashC.printTable();

        System.out.println("\n===== Buscando la clave 23 =====");
        Register<String> encontrado = hashC.search(23);
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado);
        } else {
            System.out.println("Clave 23 no encontrada.");
        }
    }
}
