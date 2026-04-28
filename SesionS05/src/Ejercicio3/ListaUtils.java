package Ejercicio3;

public class ListaUtils {

    public static <T> Node<T> insertarAlFinal(Node<T> head, T valor) {
        Node<T> nuevo = new Node<>(valor);

        // Lista vacía
        if (head == null) {
            return nuevo;
        }

        // Recorrer hasta el último nodo
        Node<T> actual = head;
        while (actual.next != null) {
            actual = actual.next;
        }

        // Insertar al final
        actual.next = nuevo;

        return head;
    }
}