package Ejercicio3;

public class Main {

    public static <T> void imprimirLista(Node<T> head) {
        Node<T> actual = head;
        while (actual != null) {
            System.out.print(actual.data + " -> ");
            actual = actual.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node<Integer> lista = null;

        lista = ListaUtils.insertarAlFinal(lista, 10);
        lista = ListaUtils.insertarAlFinal(lista, 20);
        lista = ListaUtils.insertarAlFinal(lista, 30);

        imprimirLista(lista);
    }
}