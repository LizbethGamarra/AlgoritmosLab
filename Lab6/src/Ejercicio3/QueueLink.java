package Ejercicio3;

import Actividad3.Node;

class QueueLink<E> {

    private Node<E> front;
    private Node<E> rear;

    public QueueLink() {
        front = rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(E x) {
        Node<E> nuevo = new Node<>(x);
        if (isEmpty()) {
            front = rear = nuevo;
        } else {
            rear.next = nuevo;
            rear = nuevo;
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E dato = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return dato;
    }
}