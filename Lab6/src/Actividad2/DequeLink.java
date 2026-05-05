package Actividad2;

import Actividad1.ExceptionIsEmpty;

class DequeLink<E> implements Deque<E> {

    private Node<E> first;
    private Node<E> last;

    public DequeLink() {
        first = null;
        last = null;
    }

    @Override
    public void addFirst(E x) {
        Node<E> nuevo = new Node<>(x);

        if (isEmpty()) {
            first = last = nuevo;
        } else {
            nuevo.next = first;
            first.prev = nuevo;
            first = nuevo;
        }
    }

    @Override
    public void addLast(E x) {
        Node<E> nuevo = new Node<>(x);

        if (isEmpty()) {
            first = last = nuevo;
        } else {
            last.next = nuevo;
            nuevo.prev = last;
            last = nuevo;
        }
    }

    @Override
    public E removeFirst() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Deque vacío");
        }

        E dato = first.data;

        if (first == last) { // solo un elemento
            first = last = null;
        } else {
            first = first.next;
            first.prev = null;
        }

        return dato;
    }

    @Override
    public E removeLast() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Deque vacío");
        }

        E dato = last.data;

        if (first == last) { // solo un elemento
            first = last = null;
        } else {
            last = last.prev;
            last.next = null;
        }

        return dato;
    }

    @Override
    public E getFirst() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Deque vacío");
        }
        return first.data;
    }

    @Override
    public E getLast() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Deque vacío");
        }
        return last.data;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

   @Override
public String toString() {
    if (isEmpty()) {
        return "Deque vacío";
    }

    String resultado = "";
    Node<E> temp = first;

    while (temp != null) {
        resultado = resultado + temp.data + " ";
        temp = temp.next;
    }

    return resultado;
    }
}
