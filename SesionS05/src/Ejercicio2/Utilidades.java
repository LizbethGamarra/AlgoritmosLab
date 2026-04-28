package Ejercicio2;

public class Utilidades {

    public static <T> ListLinked<T> invertirLista(ListLinked<T> lista) {
        ListLinked<T> nueva = new ListLinked<>();
        Node<T> actual = lista.getHead();

        while (actual != null) {
            nueva.insertFirst(actual.data);
            actual = actual.next;
        }

        return nueva;
    }
}