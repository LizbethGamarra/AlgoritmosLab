package hash;

/**
 * Implementación de una tabla hash abierta (encadenamiento).
 * Cada posición del arreglo contiene una LinkedList<Register<T>> propia
 * (NO java.util.LinkedList) donde se guardan los elementos que colisionan.
 */
public class HashO<T> {

    private LinkedList<Register<T>>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashO(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    /** Función hash: residuo de la división. */
    private int hash(int key) {
        return Math.abs(key) % size;
    }

    /** Inserta un registro en la lista correspondiente al índice hash de su clave. */
    public void insert(Register<T> reg) {
        int idx = hash(reg.getKey());
        table[idx].addLast(reg);
        System.out.println("Insertado " + reg + " en la lista del índice " + idx);
    }

    /** Busca un registro por su clave recorriendo la lista de su índice. */
    public Register<T> search(int key) {
        int idx = hash(key);
        LinkedList<Register<T>>.Node nodo = table[idx].getHead();
        while (nodo != null) {
            if (nodo.getData().getKey() == key) {
                return nodo.getData();
            }
            nodo = nodo.getNext();
        }
        return null;
    }

    /** Elimina un registro de la lista correspondiente, si existe. */
    public void delete(int key) {
        int idx = hash(key);
        LinkedList<Register<T>>.Node nodo = table[idx].getHead();
        while (nodo != null) {
            if (nodo.getData().getKey() == key) {
                table[idx].remove(nodo);
                System.out.println("Clave " + key + " eliminada de la lista del índice " + idx);
                return;
            }
            nodo = nodo.getNext();
        }
        System.out.println("Clave " + key + " no encontrada (no se elimina).");
    }

    /** Imprime el contenido de cada lista de la tabla hash. */
    public void printTable() {
        System.out.println("---- Tabla hash abierta (tamaño " + size + ") ----");
        for (int i = 0; i < size; i++) {
            String contenido = table[i].isEmpty() ? "vacío" : table[i].toString();
            System.out.println(i + ": " + contenido);
        }
    }
}
