package Ejercicios;

/**
 * Ejercicio 2 – Comparación de sondeo lineal vs cuadrático en hash cerrado.
 * Tabla tamaño 7. h(x) = x % 7.
 * Valores: 10, 17, 24, 31, 4.
 */
public class Ejercicio2 {

    // ─── Sondeo Lineal ───────────────────────────────────────────────────────
    public static class LinearHash {
        private int[] table;
        private int   size;
        private int   totalProbes;
        static final int EMPTY = -1;

        public LinearHash(int size) {
            this.size        = size;
            this.totalProbes = 0;
            table = new int[size];
            for (int i = 0; i < size; i++) table[i] = EMPTY;
        }

        public int hash(int x) { return Math.abs(x) % size; }

        public void insert(int key) {
            int idx    = hash(key);
            int probes = 1;
            while (table[idx] != EMPTY) {
                System.out.printf("    Lineal: [%d] ocupado con %d → sig%n", idx, table[idx]);
                idx = (idx + 1) % size;
                probes++;
            }
            table[idx]    = key;
            totalProbes  += probes;
            System.out.printf("  [Lineal] %2d → índice %d  (%d sondeo/s)%n", key, idx, probes);
        }

        public void printTable(String label) {
            System.out.println("  " + label);
            for (int i = 0; i < size; i++) {
                String v = (table[i] == EMPTY) ? "---" : String.valueOf(table[i]);
                System.out.printf("    [%d] %s%n", i, v);
            }
            System.out.println("  Total sondeos: " + totalProbes);
        }

        public int getSize()        { return size; }
        public int getValue(int i)  { return table[i]; }
        public int getTotalProbes() { return totalProbes; }
    }

    // ─── Sondeo Cuadrático ───────────────────────────────────────────────────
    public static class QuadraticHash {
        private int[] table;
        private int   size;
        private int   totalProbes;
        static final int EMPTY = -1;

        public QuadraticHash(int size) {
            this.size        = size;
            this.totalProbes = 0;
            table = new int[size];
            for (int i = 0; i < size; i++) table[i] = EMPTY;
        }

        public int hash(int x) { return Math.abs(x) % size; }

        public void insert(int key) {
            int base   = hash(key);
            int probes = 1;
            int i      = 0;
            int idx    = base;
            while (table[idx] != EMPTY) {
                i++;
                System.out.printf("    Cuad.: [%d] ocupado → f(%d)=%d%n", idx, i, i * i);
                idx = (base + i * i) % size;
                probes++;
                if (probes > size * size) {
                    System.out.println("    ¡Sin espacio disponible!");
                    return;
                }
            }
            table[idx]   = key;
            totalProbes += probes;
            System.out.printf("  [Cuadrát] %2d → índice %d  (%d sondeo/s)%n", key, idx, probes);
        }

        public void printTable(String label) {
            System.out.println("  " + label);
            for (int i = 0; i < size; i++) {
                String v = (table[i] == EMPTY) ? "---" : String.valueOf(table[i]);
                System.out.printf("    [%d] %s%n", i, v);
            }
            System.out.println("  Total sondeos: " + totalProbes);
        }

        public int getSize()        { return size; }
        public int getValue(int i)  { return table[i]; }
        public int getTotalProbes() { return totalProbes; }
    }

    public static void main(String[] args) {
        System.out.println("════════════════════════════════════════════════════");
        System.out.println("  EJERCICIO 2 – Sondeo Lineal vs Cuadrático");
        System.out.println("════════════════════════════════════════════════════\n");

        int[] values = {10, 17, 24, 31, 4};

        LinearHash    lh = new LinearHash(7);
        QuadraticHash qh = new QuadraticHash(7);

        System.out.println("── SONDEO LINEAL ──");
        for (int v : values) lh.insert(v);
        lh.printTable("Estado final (Lineal):");

        System.out.println("\n── SONDEO CUADRÁTICO ──");
        for (int v : values) qh.insert(v);
        qh.printTable("Estado final (Cuadrático):");

        System.out.println("\nConclusión:");
        System.out.printf("  Lineal:     %d sondeos totales%n", lh.getTotalProbes());
        System.out.printf("  Cuadrático: %d sondeos totales%n", qh.getTotalProbes());
        System.out.println("  El sondeo cuadrático evita el agrupamiento primario del lineal,");
        System.out.println("  dispersando colisiones con saltos f(i)=i² en lugar de i.");
    }
}