package Actividad3;


import Actividad1.ExceptionIsEmpty;

class PriorityQueueLinkSort<E, N extends Comparable<N>>
        implements PriorityQueue<E, N> {

    class EntryNode {
        E data;
        N priority;

        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    // Se asume que Node<T> tiene:
    // T getData(), Node<T> getNext(), void setNext(Node<T>)
    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void enqueue(E x, N pr) {

        EntryNode entry = new EntryNode(x, pr);
        Node<EntryNode> nuevo = new Node<>(entry);

        // Caso 1 lista vacía
        if (isEmpty()) {
            first = last = nuevo;
            return;
        }

        // Caso 2 insertar al inicio (mayor prioridad)
        if (pr.compareTo(first.getData().priority) > 0) {
            nuevo.setNext(first);
            first = nuevo;
            return;
        }

        // Caso 3 insertar en medio o final
        Node<EntryNode> actual = first;

        while (actual.getNext() != null &&
               pr.compareTo(actual.getNext().getData().priority) <= 0) {
            actual = actual.getNext();
        }

        nuevo.setNext(actual.getNext());
        actual.setNext(nuevo);

        // actualizar last si se inserto al final
        if (nuevo.getNext() == null) {
            last = nuevo;
        }
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Queue is empty");

        E aux = this.first.getData().data;
        this.first = this.first.getNext();

        if (this.first == null)
            this.last = null;

        return aux;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Queue is empty");

        return first.getData().data;
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty())
            throw new ExceptionIsEmpty("Queue is empty");

        return last.getData().data;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "Queue vacía";

        StringBuilder sb = new StringBuilder();
        Node<EntryNode> temp = first;

        while (temp != null) {
            sb.append("[")
              .append(temp.getData().data)
              .append(", p=")
              .append(temp.getData().priority)
              .append("] ");
            temp = temp.getNext();
        }

        return sb.toString();
    }
}