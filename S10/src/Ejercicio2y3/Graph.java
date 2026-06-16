package Ejercicio2y3;
import java.util.List;

/**
 * TAD Graph: interfaz que define las operaciones basicas de un grafo.
 */
public interface Graph<E> {

    // Inserta un vertice con el dato dado
    void insertVertex(E data);

    // Inserta una arista entre origin y destination (con peso)
    void insertEdge(E origin, E destination, int weight);

    // Elimina el vertice con el dato dado y todas sus aristas
    void removeVertex(E data);

    // Elimina la arista entre origin y destination
    void removeEdge(E origin, E destination);

    // Retorna true si existe un vertice con ese dato
    boolean searchVertex(E data);

    // Retorna true si existe una arista entre origin y destination
    boolean searchEdge(E origin, E destination);

    // Retorna la lista de vertices adyacentes a un vertice dado
    List<E> adjacentVertices(E data);
}