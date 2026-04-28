package Ejercicio2;

public class Main {
    public static void main(String[] args) {

        ListLinked<Integer> lista = new ListLinked<>();

        lista.insertLast(1);
        lista.insertLast(2);
        lista.insertLast(3);
        lista.insertLast(4);

        System.out.println("Original:");
        lista.printList();

        ListLinked<Integer> invertida = Utilidades.invertirLista(lista);

        System.out.println("Invertida:");
        invertida.printList();
    }
}
