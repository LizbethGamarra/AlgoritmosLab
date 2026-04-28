package Actividades;

class ListLinked<T> {
    private Node<T> head;

    public void insertFirst(T x) {
        Node<T> newNode = new Node<>(x);
        newNode.next = head;
        head = newNode;
    }

    public void insertLast(T x) {
        Node<T> newNode = new Node<>(x);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public boolean search(T x) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.value.equals(x)) return true;
            temp = temp.next;
        }
        return false;
    }

    public boolean removeNode(T x) {
        if (head == null) return false;

        if (head.value.equals(x)) {
            head = head.next;
            return true;
        }

        Node<T> temp = head;
        while (temp.next != null) {
            if (temp.next.value.equals(x)) {
                temp.next = temp.next.next;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public int length() {
        int count = 0;
        Node<T> temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public boolean isEmptyList() {
        return head == null;
    }

    public void print() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void reverse() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public Node<T> getHead() {
        return head;
    }
}