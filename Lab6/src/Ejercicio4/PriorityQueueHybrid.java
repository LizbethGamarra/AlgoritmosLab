package Ejercicio4;

public class PriorityQueueHybrid<E> {

    private OrderedQueue[] queues;
    private int levels;

    public PriorityQueueHybrid(int levels) {
        this.levels = levels;
        queues = new OrderedQueue[levels];

        for (int i = 0; i < levels; i++) {
            queues[i] = new OrderedQueue();
        }
    }

    public void enqueue(E x, int priority, int value) {
        if (priority < 0 || priority >= levels) {
            System.out.println("Prioridad inválida");
            return;
        }

        queues[priority].enqueue(x, value);
    }

    public E dequeue() {
        for (int i = levels - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return (E) queues[i].dequeue();
            }
        }
        return null;
    }
}