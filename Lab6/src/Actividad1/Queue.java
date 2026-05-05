package Actividad1;

public interface Queue<E> {

    // Inserta un elemento al final de la cola
    void enqueue(E x);

    // Elimina y retorna el elemento al frente
    E dequeue() throws ExceptionIsEmpty;

    // Retorna el elemento al frente sin eliminarlo
    E front() throws ExceptionIsEmpty;

    // Verifica si la cola está vacía
    boolean isEmpty();
}