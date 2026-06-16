package Ejercicio2y3;

import listlinked.ListLinked;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import graph.*;
/**
 * GraphLink<E>: implementacion del TAD Graph usando listas de adyacencia.
 * Grafo no dirigido ponderado.
 */
public class GraphLink<E> implements Graph<E> {

    private ListLinked<AdjList<E>> graph;

    public GraphLink() {
        graph = new ListLinked<>();
    }

    // ------------------------------------------------------------------ //
    //  Metodo privado: busca la AdjList de un dato
    // ------------------------------------------------------------------ //
    private AdjList<E> findAdjList(E data) {
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            if (adj.getVertex().getData().equals(data))
                return adj;
        }
        return null;
    }

    // Obtiene el indice de un vertice en la lista
    private int getIndex(E data) {
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).getVertex().getData().equals(data))
                return i;
        }
        return -1;
    }

    // ------------------------------------------------------------------ //
    //  insertVertex: agrega un vertice si no existe
    // ------------------------------------------------------------------ //
    @Override
    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            graph.addLast(new AdjList<>(new Vertex<>(data)));
        }
    }

    // ------------------------------------------------------------------ //
    //  insertEdge: arista no dirigida sin peso (peso = 1)
    // ------------------------------------------------------------------ //
    @Override
    public void insertEdge(E origin, E destination, int weight) {
        AdjList<E> v1 = findAdjList(origin);
        AdjList<E> v2 = findAdjList(destination);
        if (v1 == null || v2 == null) return;

        // Evitar duplicados
        if (!searchEdge(origin, destination)) {
            v1.getEdges().addLast(new Edge<>(v2.getVertex(), weight));
            v2.getEdges().addLast(new Edge<>(v1.getVertex(), weight));
        }
    }

    // insertEdgeWeight: alias con nombre explicito del ejercicio 1
    public void insertEdgeWeight(E origin, E destination, int weight) {
        insertEdge(origin, destination, weight);
    }

    // ------------------------------------------------------------------ //
    //  removeVertex: elimina un vertice y todas sus aristas
    // ------------------------------------------------------------------ //
    @Override
    public void removeVertex(E data) {
        // Primero eliminar todas las aristas que apuntan a este vertice
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            if (!adj.getVertex().getData().equals(data)) {
                // Buscar y eliminar la arista hacia 'data'
                for (int j = 0; j < adj.getEdges().size(); j++) {
                    if (adj.getEdges().get(j).getDestination().getData().equals(data)) {
                        adj.getEdges().remove(adj.getEdges().get(j));
                        break;
                    }
                }
            }
        }
        // Luego eliminar el vertice de la lista principal
        AdjList<E> toRemove = findAdjList(data);
        if (toRemove != null) {
            graph.remove(toRemove);
        }
    }

    // ------------------------------------------------------------------ //
    //  removeEdge: elimina la arista entre dos vertices
    // ------------------------------------------------------------------ //
    @Override
    public void removeEdge(E origin, E destination) {
        AdjList<E> v1 = findAdjList(origin);
        AdjList<E> v2 = findAdjList(destination);
        if (v1 == null || v2 == null) return;

        // Eliminar de v1 la arista hacia destination
        for (int i = 0; i < v1.getEdges().size(); i++) {
            if (v1.getEdges().get(i).getDestination().getData().equals(destination)) {
                v1.getEdges().remove(v1.getEdges().get(i));
                break;
            }
        }
        // Eliminar de v2 la arista hacia origin (no dirigido)
        for (int i = 0; i < v2.getEdges().size(); i++) {
            if (v2.getEdges().get(i).getDestination().getData().equals(origin)) {
                v2.getEdges().remove(v2.getEdges().get(i));
                break;
            }
        }
    }

    // ------------------------------------------------------------------ //
    //  searchVertex: true si existe un vertice con ese dato
    // ------------------------------------------------------------------ //
    @Override
    public boolean searchVertex(E data) {
        return findAdjList(data) != null;
    }

    // ------------------------------------------------------------------ //
    //  searchEdge: true si existe arista entre origin y destination
    // ------------------------------------------------------------------ //
    @Override
    public boolean searchEdge(E origin, E destination) {
        AdjList<E> v1 = findAdjList(origin);
        if (v1 == null) return false;
        for (int i = 0; i < v1.getEdges().size(); i++) {
            if (v1.getEdges().get(i).getDestination().getData().equals(destination))
                return true;
        }
        return false;
    }

    // ------------------------------------------------------------------ //
    //  adjacentVertices: lista de vecinos de un vertice
    // ------------------------------------------------------------------ //
    @Override
    public List<E> adjacentVertices(E data) {
        List<E> neighbors = new ArrayList<>();
        AdjList<E> adj = findAdjList(data);
        if (adj == null) return neighbors;
        for (int i = 0; i < adj.getEdges().size(); i++) {
            neighbors.add(adj.getEdges().get(i).getDestination().getData());
        }
        return neighbors;
    }

    // ------------------------------------------------------------------ //
    //  isConexo: true si el grafo es conexo
    // ------------------------------------------------------------------ //
    public boolean isConexo() {
        if (graph.size() == 0) return true;
        ArrayList<E> visited = new ArrayList<>();
        dfsHelper(graph.get(0).getVertex().getData(), visited);
        return visited.size() == graph.size();
    }

    // ------------------------------------------------------------------ //
    //  shortPath: ruta mas corta con Dijkstra, retorna ArrayList
    // ------------------------------------------------------------------ //
    public ArrayList<E> shortPath(E origin, E destination) {
        int n = graph.size();
        int[] dist    = new int[n];
        int[] prev    = new int[n];
        boolean[] vis = new boolean[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        int startIdx = getIndex(origin);
        if (startIdx == -1) return new ArrayList<>();
        dist[startIdx] = 0;

        for (int count = 0; count < n; count++) {
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!vis[i] && (u == -1 || dist[i] < dist[u])) u = i;
            }
            if (u == -1 || dist[u] == Integer.MAX_VALUE) break;
            vis[u] = true;

            AdjList<E> adj = graph.get(u);
            for (int j = 0; j < adj.getEdges().size(); j++) {
                Edge<E> edge = adj.getEdges().get(j);
                int v = getIndex(edge.getDestination().getData());
                if (v != -1 && !vis[v]) {
                    int newDist = dist[u] + edge.getWeight();
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        prev[v] = u;
                    }
                }
            }
        }

        ArrayList<E> path = new ArrayList<>();
        int destIdx = getIndex(destination);
        if (destIdx == -1 || dist[destIdx] == Integer.MAX_VALUE) return path;

        for (int at = destIdx; at != -1; at = prev[at]) {
            path.add(0, graph.get(at).getVertex().getData());
        }
        System.out.println("shortPath " + origin + " -> " + destination +
                           ": " + path + " | Costo: " + dist[destIdx]);
        return path;
    }

    // ------------------------------------------------------------------ //
    //  Dijkstra: ruta mas corta, retorna Stack
    // ------------------------------------------------------------------ //
    public Stack<E> Dijkstra(E origin, E destination) {
        ArrayList<E> path = shortPath(origin, destination);
        Stack<E> stack = new Stack<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            stack.push(path.get(i));
        }
        return stack;
    }

    // ------------------------------------------------------------------ //
    //  DFS auxiliar (usado por isConexo)
    // ------------------------------------------------------------------ //
    private void dfsHelper(E current, ArrayList<E> visited) {
        visited.add(current);
        AdjList<E> adj = findAdjList(current);
        if (adj == null) return;
        for (int i = 0; i < adj.getEdges().size(); i++) {
            E neighbor = adj.getEdges().get(i).getDestination().getData();
            if (!visited.contains(neighbor)) dfsHelper(neighbor, visited);
        }
    }

    // ------------------------------------------------------------------ //
    //  toString: muestra lista de adyacencia
    // ------------------------------------------------------------------ //
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            sb.append(adj.getVertex()).append(" -> ");
            for (int j = 0; j < adj.getEdges().size(); j++) {
                sb.append(adj.getEdges().get(j)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}