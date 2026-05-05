package Actividad3;


import Actividad1.ExceptionIsEmpty;

public class TestPriorityQueue {

    public static void main(String[] args) throws ExceptionIsEmpty {

        // Crear la cola de prioridad
        PriorityQueue<String, Integer> pq = new PriorityQueueLinkSort<>();

        // Insertar elementos (dato, prioridad)
        pq.enqueue("A", 5);
        pq.enqueue("B", 10);
        pq.enqueue("C", 7);
        pq.enqueue("D", 3);

        // Mostrar la cola
        System.out.println("Cola de prioridad:");
        System.out.println(pq);

        // Ver el primero (mayor prioridad)
        System.out.println("\nFront (mayor prioridad): " + pq.front());

        //  Ver el último (menor prioridad)
        System.out.println("Back (menor prioridad): " + pq.back());

        //  Eliminar elementos
        System.out.println("\nDequeue: " + pq.dequeue());
        System.out.println("Cola después de dequeue:");
        System.out.println(pq);

        System.out.println("\nDequeue: " + pq.dequeue());
        System.out.println("Cola después de dequeue:");
        System.out.println(pq);
    }
}