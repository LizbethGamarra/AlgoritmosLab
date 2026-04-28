package Ejercicio1;

public class Utilidades {

    public static <T> boolean buscarElemento(ListLinked<T> lista, T valor) {
        Nodo<T> actual = lista.getHead();

        while (actual != null) {
            if (actual.getDato().equals(valor)) {
                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }
}