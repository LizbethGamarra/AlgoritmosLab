package Ejercicio4;

public class Main {
    public static void main(String[] args) {

        Node<Integer> head = new Node<>(10);
        head.next = new Node<>(20);
        head.next.next = new Node<>(30);

        int total = OperacionesLista.contarNodos(head);

        System.out.println("Total de nodos: " + total);
    }
}