package Ejercicio3;

public class TestPrioridad {
    public static void main(String[] args) {

        PriorityQueueMulti<String> cola = new PriorityQueueMulti<>(3);

        cola.enqueue("A", 0);
        cola.enqueue("B", 2);
        cola.enqueue("C", 1);
        cola.enqueue("D", 2);

        String dato;
        while ((dato = cola.dequeue()) != null) {
            System.out.println(dato);
        }
    }
}