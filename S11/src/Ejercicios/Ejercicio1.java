package Ejercicios;
/**
 * Ejercicio 1 – Tabla hash sin colisiones, análisis de función hash.
 * Tamaño 11 (primo). h(x) = x % 11.
 * Valores: 3, 14, 25, 36, 47, 58.
 */
public class Ejercicio1 {

    private int[] table;
    private int   size;
    static final int EMPTY = -1;

    public Ejercicio1(int size) {
        this.size = size;
        table     = new int[size];
        for (int i = 0; i < size; i++) table[i] = EMPTY;
    }

    public int hash(int x) { return Math.abs(x) % size; }

    public boolean insert(int value) {
        int idx = hash(value);
        if (table[idx] == EMPTY) {
            table[idx] = value;
            System.out.printf("  Insertar %2d → h(%2d) = %2d%n", value, value, idx);
            return true;
        } else {
            System.out.printf("  Insertar %2d → h(%2d) = %2d  ¡COLISIÓN con %d!%n",
                              value, value, idx, table[idx]);
            return false;
        }
    }

    public void printTable() {
        System.out.println("\nEstado final de la tabla (tamaño=" + size + "):");
        for (int i = 0; i < size; i++) {
            String val = (table[i] == EMPTY) ? "--- (vacío)" : String.valueOf(table[i]);
            System.out.printf("  [%2d] %s%n", i, val);
        }
    }

    // Getters para GUI
    public int getSize()       { return size; }
    public int getValue(int i) { return table[i]; }

    public static void main(String[] args) {
        System.out.println("════════════════════════════════════");
        System.out.println("  EJERCICIO 1 – Sin colisiones / Primo");
        System.out.println("════════════════════════════════════\n");

        Ejercicio1 ex  = new Ejercicio1(11);
        int[]  values = {3, 14, 25, 36, 47, 58};

        System.out.println("Cálculo de direcciones (h(x) = x % 11):");
        for (int v : values) ex.insert(v);

        ex.printTable();

        System.out.println("\n¿Por qué el tamaño debe ser primo?");
        System.out.println("  Con tamaño primo, h(x)=x%M distribuye las claves de forma");
        System.out.println("  más uniforme. Si M tuviese factores comunes con las claves");
        System.out.println("  (ej. M=10, claves múltiplos de 5) muchas colisionarían en");
        System.out.println("  pocos índices. Un primo minimiza esa coincidencia estructural.");
    }
}	