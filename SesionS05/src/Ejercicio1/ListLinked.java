package Ejercicio1;

public class ListLinked<T> {
    private Nodo<T> head;

    public ListLinked() {
        head = null;
    }

    public Nodo<T> getHead() {
        return head;
    }

    public void add(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (head == null) {
            head = nuevo;
        } else {
            Nodo<T> actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }
}