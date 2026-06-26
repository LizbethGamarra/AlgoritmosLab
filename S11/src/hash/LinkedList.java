package hash;

/**
 * Lista enlazada simple, genérica, implementada por el estudiante.
 * NO es java.util.LinkedList. Se usa como estructura de cada celda
 * de la tabla hash abierta (encadenamiento).
 */
public class LinkedList<E> {

    /** Nodo de la lista. Público para que HashO pueda recorrer la lista. */
    public class Node {
        private E data;
        private Node next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    /** Inserta al inicio de la lista. */
    public void addFirst(E data) {
        Node nuevo = new Node(data);
        nuevo.next = head;
        head = nuevo;
        size++;
    }

    /** Inserta al final de la lista. */
    public void addLast(E data) {
        Node nuevo = new Node(data);
        if (head == null) {
            head = nuevo;
        } else {
            Node actual = head;
            while (actual.next != null) {
                actual = actual.next;
            }
            actual.next = nuevo;
        }
        size++;
    }

    /** Elimina un nodo específico de la lista (por referencia). */
    public boolean remove(Node objetivo) {
        if (head == null || objetivo == null) {
            return false;
        }
        if (head == objetivo) {
            head = head.next;
            size--;
            return true;
        }
        Node actual = head;
        while (actual.next != null) {
            if (actual.next == objetivo) {
                actual.next = actual.next.next;
                size--;
                return true;
            }
            actual = actual.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node actual = head;
        while (actual != null) {
            sb.append(actual.data);
            if (actual.next != null) {
                sb.append(" -> ");
            }
            actual = actual.next;
        }
        return sb.toString();
    }
}
