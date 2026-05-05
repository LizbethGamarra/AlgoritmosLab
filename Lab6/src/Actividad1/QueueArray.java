package Actividad1;

public class QueueArray<E> implements Queue<E> {

    private E[] array;
    private int front;
    private int rear;
    private int size;

    public QueueArray(int n) {
        array = (E[]) new Object[n];
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public void enqueue(E x) {
        if (isFull()) {
            System.out.println("La cola está llena");
            return;
        }

        // avanzar circularmente
        rear = (rear + 1) % array.length;
        array[rear] = x;
        size++;
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }

        E elemento = array[front];
        array[front] = null; // opcional (limpieza)

        // avanzar circularmente
        front = (front + 1) % array.length;
        size--;

        return elemento;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }

        return array[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

   public String toString() {
        if (isEmpty()) {
            return "Cola vacía";
        }

        String resultado = "";
        int i = front;

        for (int count = 0; count < size; count++) {
            resultado = resultado + array[i] + " ";
            i = (i + 1) % array.length;
        }

        return resultado;
    }
}
