package Ejercicio6;

public class Main {
    public static void main(String[] args) {

        ListLinked<Integer> lista1 = new ListLinked<>();
        lista1.insertarAlFinal(1);
        lista1.insertarAlFinal(2);

        ListLinked<Integer> lista2 = new ListLinked<>();
        lista2.insertarAlFinal(3);
        lista2.insertarAlFinal(4);

        ListLinked<Integer> resultado = ListLinked.concatenarListas(lista1, lista2);

        // Mostrar resultado
        Node<Integer> actual = resultado.head;
        while (actual != null) {
            System.out.print(actual.data + " ");
            actual = actual.next;
        }
    }
}