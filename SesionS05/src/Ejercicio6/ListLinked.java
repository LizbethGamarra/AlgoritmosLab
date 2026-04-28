package Ejercicio6;

public class ListLinked<T> {
    Node<T> head;
    
    // Insertar al final
    public void insertarAlFinal(T data) {
        Node<T> nuevo = new Node<>(data);

        if (head == null) {
            head = nuevo;
            return;
        }

        Node<T> actual = head;
        while (actual.next != null) {
            actual = actual.next;
        }

        actual.next = nuevo;
    }

    // MÉTODO DEL EJERCICIO
    public static <T> ListLinked<T> concatenarListas(ListLinked<T> lista1, ListLinked<T> lista2) {
        ListLinked<T> nuevaLista = new ListLinked<>();

        // Copiar lista1
        Node<T> actual1 = lista1.head;
        while (actual1 != null) {
            nuevaLista.insertarAlFinal(actual1.data);
            actual1 = actual1.next;
        }

        // Copiar lista2
        Node<T> actual2 = lista2.head;
        while (actual2 != null) {
            nuevaLista.insertarAlFinal(actual2.data);
            actual2 = actual2.next;
        }

        return nuevaLista;
    }
}