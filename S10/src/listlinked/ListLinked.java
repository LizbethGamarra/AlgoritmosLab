package listlinked;

public class ListLinked<E> {

    private Node<E> first;
    private int size;

    public ListLinked() {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addLast(E data) {

        Node<E> newNode = new Node<>(data);

        if (isEmpty()) {
            first = newNode;
        } else {

            Node<E> aux = first;

            while (aux.getNext() != null) {
                aux = aux.getNext();
            }

            aux.setNext(newNode);
        }

        size++;
    }

    public void addFirst(E data) {

        Node<E> newNode = new Node<>(data);

        newNode.setNext(first);
        first = newNode;

        size++;
    }

    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> aux = first;

        for (int i = 0; i < index; i++) {
            aux = aux.getNext();
        }

        return aux.getData();
    }
    public boolean remove(E data) {
        if (first == null) return false;

        // Si es el primero
        if (first.getData().equals(data)) {
            first = first.getNext();
            size--;
            return true;
        }

        // Buscar en el resto
        Node<E> current = first;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        Node<E> aux = first;

        while (aux != null) {
            sb.append(aux.getData()).append(" ");
            aux = aux.getNext();
        }

        return sb.toString();
    }
}