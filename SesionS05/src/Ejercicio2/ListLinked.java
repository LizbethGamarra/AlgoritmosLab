package Ejercicio2;

public class ListLinked<T> {
    private Node<T> head;

    public ListLinked() {
        head = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public void insertFirst(T data) {
        Node<T> nuevo = new Node<>(data);
        nuevo.next = head;
        head = nuevo;
    }

    public void insertLast(T data) {
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

    public void printList() {
        Node<T> actual = head;
        while (actual != null) {
            System.out.print(actual.data + " -> ");
            actual = actual.next;
        }
        System.out.println("null");
    }
}