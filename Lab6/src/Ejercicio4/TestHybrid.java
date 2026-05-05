package Ejercicio4;


public class TestHybrid {
    public static void main(String[] args) {

        PriorityQueueHybrid<String> cola = new PriorityQueueHybrid<>(3);

        cola.enqueue("A", 2, 5);
        cola.enqueue("B", 2, 1);
        cola.enqueue("C", 1, 3);
        cola.enqueue("D", 2, 3);

        String dato;
        while ((dato = cola.dequeue()) != null) {
            System.out.println(dato);
        }
    }
}