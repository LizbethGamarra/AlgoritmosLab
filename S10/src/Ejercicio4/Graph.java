package Ejercicio4;

import java.util.List;

public interface Graph<E> {
    void insertVertex(E data);
    void insertEdge(E origin, E destination);
    void removeVertex(E data);
    void removeEdge(E origin, E destination);
    boolean searchVertex(E data);
    boolean searchEdge(E origin, E destination);
    List<E> adjacentVertices(E data);
}