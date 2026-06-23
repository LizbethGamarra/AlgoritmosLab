package hash;

/**
 * Tabla hash CERRADA con sondeo lineal.
 * Usa arreglos nativos (sin ArrayList ni java.util).
 * Estados de celda: 0 = EMPTY, 1 = OCCUPIED, 2 = DELETED
 */
public class HashC<T> {

    public static final int EMPTY    = 0;
    public static final int OCCUPIED = 1;
    public static final int DELETED  = 2;

    private Register<T>[] table;
    private int[]         status;
    private int           size;
    private int           count;

    @SuppressWarnings("unchecked")
    public HashC(int size) {
        this.size  = size;
        this.count = 0;
        table  = new Register[size];
        status = new int[size];       // Java inicializa en 0 → todos EMPTY
    }

    /** h(x) = x % size */
    private int hash(int key) {
        return Math.abs(key) % size;
    }

    /**
     * Inserta con sondeo lineal.
     * Si la clave ya existe la actualiza.
     * Devuelve true si tuvo éxito.
     */
    public boolean insert(Register<T> reg) {
        if (count == size) {
            System.out.println("[HashC] Tabla llena. No se pudo insertar " + reg);
            return false;
        }
        int idx          = hash(reg.getKey());
        int start        = idx;
        int firstDeleted = -1;

        do {
            if (status[idx] == EMPTY) {
                int pos = (firstDeleted != -1) ? firstDeleted : idx;
                table[pos]  = reg;
                status[pos] = OCCUPIED;
                count++;
                System.out.printf("[HashC] Insertado %s en índice %d%n", reg, pos);
                return true;
            } else if (status[idx] == DELETED) {
                if (firstDeleted == -1) firstDeleted = idx;
            } else {                                   // OCCUPIED
                if (table[idx].getKey() == reg.getKey()) {
                    table[idx] = reg;
                    System.out.printf("[HashC] Actualizado %s en índice %d%n", reg, idx);
                    return true;
                }
            }
            idx = (idx + 1) % size;
        } while (idx != start);

        // Solo quedaban DELETED
        if (firstDeleted != -1) {
            table[firstDeleted]  = reg;
            status[firstDeleted] = OCCUPIED;
            count++;
            System.out.printf("[HashC] Insertado %s en celda DELETED=%d%n", reg, firstDeleted);
            return true;
        }
        return false;
    }

    /** Busca por clave. Devuelve el Register o null. */
    public Register<T> search(int key) {
        int idx   = hash(key);
        int start = idx;
        do {
            if (status[idx] == EMPTY) break;
            if (status[idx] == OCCUPIED && table[idx].getKey() == key) {
                System.out.printf("[HashC] Encontrado %s en índice %d%n", table[idx], idx);
                return table[idx];
            }
            idx = (idx + 1) % size;   // DELETED → seguir sondeando
        } while (idx != start);
        System.out.printf("[HashC] Clave %d no encontrada%n", key);
        return null;
    }

    /** Eliminación lógica: marca la celda como DELETED. */
    public boolean delete(int key) {
        int idx   = hash(key);
        int start = idx;
        do {
            if (status[idx] == EMPTY) break;
            if (status[idx] == OCCUPIED && table[idx].getKey() == key) {
                status[idx] = DELETED;
                count--;
                System.out.printf("[HashC] Eliminado (lógico) clave %d en índice %d%n", key, idx);
                return true;
            }
            idx = (idx + 1) % size;
        } while (idx != start);
        System.out.printf("[HashC] Clave %d no encontrada para eliminar%n", key);
        return false;
    }

    /** Imprime el estado completo de la tabla en consola. */
    public void printTable() {
        System.out.println("=== Tabla HashC (tamaño=" + size + ", elementos=" + count + ") ===");
        for (int i = 0; i < size; i++) {
            String st  = status[i] == EMPTY ? "EMPTY"
                       : status[i] == DELETED ? "DELETED" : "OCCUPIED";
            String val = status[i] == OCCUPIED ? table[i].toString() : "---";
            System.out.printf("  [%2d] %-8s  %s%n", i, st, val);
        }
        System.out.println();
    }

    // ── Getters para la GUI ──────────────────────────────────────────────────
    public int getSize()  { return size; }
    public int getCount() { return count; }

    public int getStatus(int i) { return status[i]; }

    public String getStatusLabel(int i) {
        return status[i] == EMPTY ? "EMPTY" : status[i] == DELETED ? "DELETED" : "OCCUPIED";
    }

    public String getCellValue(int i) {
        return status[i] == OCCUPIED ? table[i].toString() : "";
    }
}