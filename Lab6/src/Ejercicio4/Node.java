package Ejercicio4;

class Node<E> {
    E data;
    int value;
    Node<E> next;

    Node(E data, int value) {
        this.data = data;
        this.value = value;
        this.next = null;
    }
}