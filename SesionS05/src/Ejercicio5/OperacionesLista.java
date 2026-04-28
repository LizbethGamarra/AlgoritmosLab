package Ejercicio5;

public class OperacionesLista {

    public static <T> boolean sonIguales(ListLinked<T> lista1, ListLinked<T> lista2) {
        Node<T> actual1 = lista1.head;
        Node<T> actual2 = lista2.head;

        while (actual1 != null && actual2 != null) {
            if (!actual1.data.equals(actual2.data)) {
                return false;
            }
            actual1 = actual1.next;
            actual2 = actual2.next;
        }

        // Si ambas terminan al mismo tiempo, son iguales
        return actual1 == null && actual2 == null;
    }
}
