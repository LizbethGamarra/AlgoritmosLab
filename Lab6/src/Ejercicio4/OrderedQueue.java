package Ejercicio4;

class OrderedQueue<E> {

    private Node<E> head;

    public OrderedQueue() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // inserta ordenado por "value"
    public void enqueue(E x, int value) {
        Node<E> nuevo = new Node<>(x, value);

        if (head == null || value < head.value) {
            nuevo.next = head;
            head = nuevo;
        } else {
            Node<E> actual = head;

            while (actual.next != null && actual.next.value <= value) {
                actual = actual.next;
            }

            nuevo.next = actual.next;
            actual.next = nuevo;
        }
    }

    public E dequeue() {
        if (isEmpty()) return null;

        E dato = head.data;
        head = head.next;
        return dato;
    }
}