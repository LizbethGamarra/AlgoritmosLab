package Ejercicio7;

public class ListLinked<T> {
    protected Node<T> head;

    public ListLinked() {
        head = null;
    }

    public void mostrarLista() {
        Node<T> actual = head;
        while (actual != null) {
            System.out.print(actual.data + " -> ");
            actual = actual.next;
        }
        System.out.println("null");
    }
}