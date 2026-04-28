package Ejercicio7;

public class SortedListLinked<T extends Comparable<T>> extends ListLinked<T> {

    public void insertOrden(T x) {
        Node<T> nuevo = new Node<>(x);

        // Caso 1: lista vacía o x es menor que el primero
        if (head == null || x.compareTo(head.data) < 0) {
            nuevo.next = head;
            head = nuevo;
            return;
        }

        // Caso 2: buscar posición correcta
        Node<T> actual = head;

        while (actual.next != null && x.compareTo(actual.next.data) >= 0) {
            actual = actual.next;
        }

        // Insertar en la posición encontrada
        nuevo.next = actual.next;
        actual.next = nuevo;
    }
}