package hash;

/**
 * Tabla hash ABIERTA con encadenamiento.
 * Usa arreglos nativos (sin ArrayList ni java.util).
 * Cada celda es un arreglo Register<T>[] que crece copiando cuando hay colisión.
 */
public class HashO<T> {

    private Register<T>[][] table;   // table[i] = cadena de la celda i
    private int[]           lengths; // cuántos elementos hay en cada cadena
    private int             size;

    @SuppressWarnings("unchecked")
    public HashO(int size) {
        this.size   = size;
        table       = new Register[size][];
        lengths     = new int[size];
        for (int i = 0; i < size; i++) {
            table[i]   = new Register[0];
            lengths[i] = 0;
        }
    }

    /** h(k) = k % size */
    private int hash(int key) {
        return Math.abs(key) % size;
    }

    /**
     * Inserta el registro en la cadena correspondiente.
     * Si la clave ya existe actualiza el dato.
     */
    public void insert(Register<T> reg) {
        int idx = hash(reg.getKey());

        // ¿Ya existe la clave? → actualizar
        for (int i = 0; i < lengths[idx]; i++) {
            if (table[idx][i].getKey() == reg.getKey()) {
                table[idx][i] = reg;
                System.out.printf("[HashO] Actualizado %s en índice %d, pos %d%n", reg, idx, i);
                return;
            }
        }

        // Agregar al final de la cadena (copiar arreglo + 1)
        Register<T>[] newChain = new Register[lengths[idx] + 1];
        for (int i = 0; i < lengths[idx]; i++) newChain[i] = table[idx][i];
        newChain[lengths[idx]] = reg;
        table[idx]   = newChain;
        lengths[idx]++;
        System.out.printf("[HashO] Insertado %s en índice %d (cadena size=%d)%n",
                          reg, idx, lengths[idx]);
    }

    /** Busca por clave. Devuelve el Register o null. */
    public Register<T> search(int key) {
        int idx = hash(key);
        for (int i = 0; i < lengths[idx]; i++) {
            if (table[idx][i].getKey() == key) {
                System.out.printf("[HashO] Encontrado %s en índice %d%n", table[idx][i], idx);
                return table[idx][i];
            }
        }
        System.out.printf("[HashO] Clave %d no encontrada%n", key);
        return null;
    }

    /** Elimina el registro con la clave dada de su cadena. */
    public boolean delete(int key) {
        int idx = hash(key);
        for (int i = 0; i < lengths[idx]; i++) {
            if (table[idx][i].getKey() == key) {
                Register<T> removed = table[idx][i];
                // Copiar cadena sin ese elemento
                Register<T>[] newChain = new Register[lengths[idx] - 1];
                int ni = 0;
                for (int j = 0; j < lengths[idx]; j++) {
                    if (j != i) newChain[ni++] = table[idx][j];
                }
                table[idx]   = newChain;
                lengths[idx]--;
                System.out.printf("[HashO] Eliminado %s del índice %d%n", removed, idx);
                return true;
            }
        }
        System.out.printf("[HashO] Clave %d no encontrada para eliminar%n", key);
        return false;
    }

    /** Imprime el estado completo de la tabla en consola. */
    public void printTable() {
        System.out.println("=== Tabla HashO (tamaño=" + size + ") ===");
        for (int i = 0; i < size; i++) {
            System.out.printf("  [%2d] → ", i);
            if (lengths[i] == 0) {
                System.out.print("(vacío)");
            } else {
                for (int j = 0; j < lengths[i]; j++) {
                    System.out.print(table[i][j]);
                    if (j < lengths[i] - 1) System.out.print(" → ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // ── Getters para la GUI ──────────────────────────────────────────────────
    public int getSize()            { return size; }
    public int getChainLength(int i){ return lengths[i]; }

    public Register<T> getElement(int i, int j) { return table[i][j]; }

    /** Devuelve una cadena de texto con todos los elementos de la celda i. */
    public String getChainString(int i) {
        if (lengths[i] == 0) return "(vacío)";
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < lengths[i]; j++) {
            sb.append(table[i][j].toString());
            if (j < lengths[i] - 1) sb.append(" → ");
        }
        return sb.toString();
    }
}