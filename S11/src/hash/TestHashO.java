package hash;

/**
 * Clase de prueba para HashO (hashing abierto / encadenamiento).
 */
public class TestHashO {
    public static void main(String[] args) {

        HashO<String> hashO = new HashO<>(7); // tamaño 7

        int[] claves = {10, 17, 24, 31, 5, 12, 19, 3};
        String[] nombres = {
            "Mario", "Lucia", "Pedro", "Sofia", "Andrea", "Bruno", "Carmen", "Diego"
        };

        System.out.println("===== Insertando valores (con colisiones) =====");
        for (int i = 0; i < claves.length; i++) {
            hashO.insert(new Register<>(claves[i], nombres[i]));
        }

        System.out.println("\n===== Tabla hash abierta =====");
        hashO.printTable();

        System.out.println("\n===== Buscando clave 24 =====");
        Register<String> r = hashO.search(24);
        System.out.println(r != null ? "Encontrado: " + r : "No encontrado");

        System.out.println("\n===== Eliminando clave 17 =====");
        hashO.delete(17);
        hashO.printTable();
    }
}
