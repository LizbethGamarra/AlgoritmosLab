package Actividad1;


public class TestQueue {

    public static void main(String[] args) {

        // Prueba con Integer
        System.out.println("=== PRUEBA CON INTEGER ===");
        QueueArray<Integer> colaInt = new QueueArray<>(5);

        colaInt.enqueue(10);
        colaInt.enqueue(20);
        colaInt.enqueue(30);

        System.out.println("Cola: " + colaInt);

        try {
            System.out.println("Front: " + colaInt.front());
            System.out.println("Dequeue: " + colaInt.dequeue());
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Cola después de dequeue: " + colaInt);

        // Prueba con String
        System.out.println("\n=== PRUEBA CON STRING ===");
        QueueArray<String> colaStr = new QueueArray<>(5);

        colaStr.enqueue("Hola");
        colaStr.enqueue("Mundo");
        colaStr.enqueue("Java");

        System.out.println("Cola: " + colaStr);

        try {
            System.out.println("Front: " + colaStr.front());
            System.out.println("Dequeue: " + colaStr.dequeue());
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Cola después de dequeue: " + colaStr);
    }
}