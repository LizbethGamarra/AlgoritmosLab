package Ejercicio5;

public class ListLinked<T> {
    Node<T> head;

    public ListLinked() {
        this.head = null;
    }

    // Método para insertar al final (solo para probar)
    public void insertar(T data) {
        Node<T> nuevo = new Node<>(data);

        if (head == null) {
            head = nuevo;
        } else {
            Node<T> actual = head;
            while (actual.next != null) {
                actual = actual.next;
            }
            actual.next = nuevo;
        }
    }
}