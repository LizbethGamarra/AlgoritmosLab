package hash;

/**
 * Implementación de una tabla hash cerrada (sondeo lineal).
 * La tabla es un arreglo de Element<T>, cada uno con su Register<T>
 * y su estado (EMPTY, OCCUPIED, DELETED).
 */
public class HashC<T> {

    private Element<T>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashC(int size) {
        this.size = size;
        this.table = new Element[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Element<>();
        }
    }

    /** Función hash: residuo de la división. */
    private int hash(int key) {
        return Math.abs(key) % size;
    }

    /**
     * Inserta un registro usando sondeo lineal.
     * Una celda EMPTY o DELETED se considera disponible para insertar.
     * Si la clave ya existe (OCCUPIED), no se inserta de nuevo.
     */
    public void insert(Register<T> reg) {
        int start = hash(reg.getKey());
        int idx = start;
        int pasos = 0;

        do {
            if (table[idx].getStatus() != Element.OCCUPIED) {
                table[idx].setRegister(reg);
                table[idx].setStatus(Element.OCCUPIED);
                System.out.println("Insertado " + reg + " en posición " + idx
                        + " (colisiones/saltos: " + pasos + ")");
                return;
            }
            if (table[idx].getRegister().getKey() == reg.getKey()) {
                System.out.println("Clave duplicada " + reg.getKey() + ": no se inserta de nuevo.");
                return;
            }
            idx = (idx + 1) % size;
            pasos++;
        } while (idx != start);

        System.out.println("Error: tabla llena. No se pudo insertar la clave " + reg.getKey());
    }

    /**
     * Busca un registro por su clave usando sondeo lineal.
     * Las celdas DELETED no detienen la búsqueda (solo EMPTY la detiene).
     */
    public Register<T> search(int key) {
        int start = hash(key);
        int idx = start;

        do {
            if (table[idx].getStatus() == Element.EMPTY) {
                return null; // se garantiza que no está más adelante
            }
            if (table[idx].getStatus() == Element.OCCUPIED
                    && table[idx].getRegister().getKey() == key) {
                return table[idx].getRegister();
            }
            idx = (idx + 1) % size;
        } while (idx != start);

        return null;
    }

    /**
     * Elimina lógicamente un registro (lo marca como DELETED, no borra el objeto).
     */
    public void delete(int key) {
        int start = hash(key);
        int idx = start;

        do {
            if (table[idx].getStatus() == Element.EMPTY) {
                System.out.println("Clave " + key + " no encontrada (no se elimina).");
                return;
            }
            if (table[idx].getStatus() == Element.OCCUPIED
                    && table[idx].getRegister().getKey() == key) {
                table[idx].setStatus(Element.DELETED);
                System.out.println("Clave " + key + " eliminada lógicamente de la posición " + idx);
                return;
            }
            idx = (idx + 1) % size;
        } while (idx != start);

        System.out.println("Clave " + key + " no encontrada (no se elimina).");
    }

    /** Imprime el estado actual de toda la tabla. */
    public void printTable() {
        System.out.println("---- Tabla hash cerrada (tamaño " + size + ") ----");
        for (int i = 0; i < size; i++) {
            String contenido = (table[i].getStatus() == Element.OCCUPIED)
                    ? table[i].getRegister().toString()
                    : "-";
            System.out.println(i + ": [" + table[i].statusName() + "] " + contenido);
        }
    }
}
