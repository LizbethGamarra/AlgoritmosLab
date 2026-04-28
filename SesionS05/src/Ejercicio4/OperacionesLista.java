package Ejercicio4;

public class OperacionesLista {

    public static <T> int contarNodos(Node<T> head) {
        int contador = 0;
        Node<T> actual = head;

        while (actual != null) {
            contador++;
            actual = actual.next;
        }

        return contador;
    }
}