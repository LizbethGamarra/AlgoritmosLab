package Ejercicio4;
import listlinked.ListLinked;
import java.util.ArrayList;
import java.util.List;
import graph.*;
/**
 * GraphListEdge: grafo DIRIGIDO representado con lista de aristas.
 * Implementa el TAD Graph.
 */
public class GraphListEdge<E> implements Graph<E> {

    private ListLinked<Vertex<E>> vertices;
    private ListLinked<Edge<E>>   edges;

    public GraphListEdge() {
        vertices = new ListLinked<>();
        edges    = new ListLinked<>();
    }

    // Busca un vertice por dato
    private Vertex<E> findVertex(E data) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getData().equals(data))
                return vertices.get(i);
        }
        return null;
    }

    @Override
    public void insertVertex(E data) {
        if (!searchVertex(data))
            vertices.addLast(new Vertex<>(data));
    }

    @Override
    public void insertEdge(E origin, E destination) {
        Vertex<E> vo = findVertex(origin);
        Vertex<E> vd = findVertex(destination);
        if (vo == null || vd == null) return;
        if (!searchEdge(origin, destination))
            edges.addLast(new Edge<>(vo, vd));
    }

    @Override
    public void removeVertex(E data) {
        Vertex<E> v = findVertex(data);
        if (v == null) return;
        // Eliminar todas las aristas que involucran este vertice
        for (int i = 0; i < edges.size(); i++) {
            Edge<E> e = edges.get(i);
            if (e.getOrigin().getData().equals(data) ||
                e.getDestination().getData().equals(data)) {
                edges.remove(e);
                i--; // ajustar indice tras eliminar
            }
        }
        vertices.remove(v);
    }

    @Override
    public void removeEdge(E origin, E destination) {
        for (int i = 0; i < edges.size(); i++) {
            Edge<E> e = edges.get(i);
            if (e.getOrigin().getData().equals(origin) &&
                e.getDestination().getData().equals(destination)) {
                edges.remove(e);
                return;
            }
        }
    }

    @Override
    public boolean searchVertex(E data) {
        return findVertex(data) != null;
    }

    @Override
    public boolean searchEdge(E origin, E destination) {
        for (int i = 0; i < edges.size(); i++) {
            Edge<E> e = edges.get(i);
            if (e.getOrigin().getData().equals(origin) &&
                e.getDestination().getData().equals(destination))
                return true;
        }
        return false;
    }

    @Override
    public List<E> adjacentVertices(E data) {
        List<E> neighbors = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            Edge<E> e = edges.get(i);
            if (e.getOrigin().getData().equals(data))
                neighbors.add(e.getDestination().getData());
        }
        return neighbors;
    }

    public int vertexCount() { return vertices.size(); }
    public int edgeCount()   { return edges.size(); }

    public ListLinked<Vertex<E>> getVertices() { return vertices; }
    public ListLinked<Edge<E>>   getEdges()    { return edges; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: [");
        for (int i = 0; i < vertices.size(); i++) {
            sb.append(vertices.get(i));
            if (i < vertices.size() - 1) sb.append(", ");
        }
        sb.append("]\nAristas:  [");
        for (int i = 0; i < edges.size(); i++) {
            sb.append(edges.get(i));
            if (i < edges.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}