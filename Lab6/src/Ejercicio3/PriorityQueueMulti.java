package Ejercicio3;


public class PriorityQueueMulti<E> {

    private QueueLink[] queues;
    private int levels;

    public PriorityQueueMulti(int levels) {
        this.levels = levels;
        queues = new QueueLink[levels];

        for (int i = 0; i < levels; i++) {
            queues[i] = new QueueLink();
        }
    }

    public void enqueue(E x, int priority) {
        if (priority < 0 || priority >= levels) {
            System.out.println("Prioridad inválida");
            return;
        }
        queues[priority].enqueue(x);
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