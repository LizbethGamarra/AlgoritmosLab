package Ejercicios;

/**
 * Ejercicio 5 – Factor de carga y redimensionamiento (rehashing).
 * Tamaño inicial 7. Umbral α ≤ 0.75. Nuevo tamaño 17 (primo).
 * Valores: 2, 9, 16, 23, 4, 11.
 */
public class Ejercicio5 {

    // Tabla interna simple (solo enteros) para soportar rehash
    static class SimpleHash {
        private int[] table;
        private int   size;
        private int   count;
        static final int EMPTY = -1;

        SimpleHash(int size) {
            this.size  = size;
            this.count = 0;
            table = new int[size];
            for (int i = 0; i < size; i++) table[i] = EMPTY;
        }

        int hash(int x) { return Math.abs(x) % size; }

        boolean insert(int key) {
            int idx   = hash(key);
            int start = idx;
            do {
                if (table[idx] == EMPTY) {
                    table[idx] = key;
                    count++;
                    return true;
                }
                idx = (idx + 1) % size;
            } while (idx != start);
            return false;
        }

        double loadFactor() { return (double) count / size; }

        // Copia todos los elementos ocupados en un arreglo
        int[] getAll() {
            int[] tmp = new int[count];
            int   k   = 0;
            for (int v : table) if (v != EMPTY) tmp[k++] = v;
            return tmp;
        }

        void printTable(String label) {
            System.out.printf("  %s  (M=%d, n=%d, α=%.2f)%n",
                              label, size, count, loadFactor());
            for (int i = 0; i < size; i++) {
                String v = (table[i] == EMPTY) ? "---" : String.valueOf(table[i]);
                System.out.printf("    [%2d] %s%n", i, v);
            }
        }

        int getSize()       { return size; }
        int getCount()      { return count; }
        int getValue(int i) { return table[i]; }
    }

    public static void main(String[] args) {
        System.out.println("════════════════════════════════════");
        System.out.println("  EJERCICIO 5 – Factor de carga y Rehashing");
        System.out.println("════════════════════════════════════\n");

        int[]  values    = {2, 9, 16, 23, 4, 11};
        double threshold = 0.75;

        SimpleHash t        = new SimpleHash(7);
        boolean    rehashed = false;

        System.out.println("Insertando y calculando α tras cada inserción:");
        for (int v : values) {
            t.insert(v);
            System.out.printf("  Insertar %2d → α = %d/%d = %.2f%n",
                              v, t.getCount(), t.getSize(), t.loadFactor());

            if (!rehashed && t.loadFactor() > threshold) {
                System.out.println("\n  ⚡ α > 0.75 → Rehashing a tamaño 17!\n");
                t.printTable("Tabla ANTES del rehashing:");

                SimpleHash newTable = new SimpleHash(17);
                int[]      all      = t.getAll();
                for (int old : all) newTable.insert(old);
                t        = newTable;
                rehashed = true;

                System.out.println();
                t.printTable("Tabla DESPUÉS del rehashing:");
                System.out.println();
            }
        }

        System.out.println("\n--- Tabla final ---");
        t.printTable("Estado final:");

        System.out.println("\n¿Por qué cambian las posiciones tras el rehashing?");
        System.out.println("  h(x) = x % M depende de M. Al cambiar M de 7 a 17,");
        System.out.println("  cada elemento se reinserta con la nueva función hash,");
        System.out.println("  por lo que su índice generalmente cambia.");
    }
}