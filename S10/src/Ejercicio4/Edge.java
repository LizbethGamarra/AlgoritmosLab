package Ejercicio4;
import graph.*;
public class Edge<E> {
    private Vertex<E> origin;      // solo usado en GraphListEdge
    private Vertex<E> destination;
    private int weight;

    // Constructor para GraphLink (sin origen explícito)
    public Edge(Vertex<E> destination) {
        this(destination, 1);
    }

    // Constructor para GraphLink ponderado
    public Edge(Vertex<E> destination, int weight) {
        this.origin      = null;
        this.destination = destination;
        this.weight      = weight;
    }

    // Constructor para GraphListEdge (origen + destino)
    public Edge(Vertex<E> origin, Vertex<E> destination) {
        this.origin      = origin;
        this.destination = destination;
        this.weight      = 1;
    }

    public Vertex<E> getOrigin()      { return origin; }
    public Vertex<E> getDestination() { return destination; }
    public int       getWeight()      { return weight; }

    public void setDestination(Vertex<E> destination) { this.destination = destination; }
    public void setWeight(int weight)                 { this.weight = weight; }

    @Override
    public String toString() {
        if (origin != null)
            return "(" + origin + "->" + destination + ")";
        return destination + "(" + weight + ")";
    }
}