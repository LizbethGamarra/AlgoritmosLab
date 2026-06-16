package graph;
import java.util.ArrayList;
import java.util.Stack;
import listlinked.ListLinked;

public class GraphLink<E> {

    private ListLinked<AdjList<E>> graph;

    public GraphLink() {
        graph = new ListLinked<>();
    }

    public void insertVertex(E data) {
        Vertex<E> vertex = new Vertex<>(data);
        graph.addLast(new AdjList<>(vertex));
    }

    private AdjList<E> findVertex(E data) {

        for (int i = 0; i < graph.size(); i++) {

            AdjList<E> adj = graph.get(i);

            if (adj.getVertex().getData().equals(data)) {
                return adj;
            }
        }

        return null;
    }

    public void insertEdge(E origin, E destination) {

        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if (v1 == null || v2 == null) {
            return;
        }

        v1.getEdges().addLast(
                new Edge<>(v2.getVertex())
        );

        v2.getEdges().addLast(
                new Edge<>(v1.getVertex())
        );
    }
    public void removeVertex(E data) {
        // Primero eliminar todas las aristas que apuntan a este vertice
        for (int i = 0; i < graph.size(); i++) {
            AdjList<E> adj = graph.get(i);
            if (!adj.getVertex().getData().equals(data)) {
                // Buscar la arista hacia 'data' y eliminarla
                for (int j = 0; j < adj.getEdges().size(); j++) {
                    Edge<E> edge = adj.getEdges().get(j);
                    if (edge.getDestination().getData().equals(data)) {
                        adj.getEdges().remove(edge);
                        break;
                    }
                }
            }
        }
        // Luego eliminar el vertice de la lista principal
        AdjList<E> toRemove = findVertex(data);
        if (toRemove != null) {
            graph.remove(toRemove);
        }
    }

    public void removeEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);
        if (v1 == null || v2 == null) return;

        // Eliminar de v1 la arista hacia destination
        for (int j = 0; j < v1.getEdges().size(); j++) {
            Edge<E> edge = v1.getEdges().get(j);
            if (edge.getDestination().getData().equals(destination)) {
                v1.getEdges().remove(edge);
                break;
            }
        }

        // Eliminar de v2 la arista hacia origin (grafo no dirigido)
        for (int j = 0; j < v2.getEdges().size(); j++) {
            Edge<E> edge = v2.getEdges().get(j);
            if (edge.getDestination().getData().equals(origin)) {
                v2.getEdges().remove(edge);
                break;
            }
        }
    }
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < graph.size(); i++) {

            AdjList<E> adj = graph.get(i);

            sb.append(adj.getVertex())
              .append(" -> ");

            for (int j = 0; j < adj.getEdges().size(); j++) {
                sb.append(adj.getEdges().get(j))
                  .append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
    
    
    //Ejercicio1:
    
    public void insertEdgeWeight(E origin, E destination, int weight) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if (v1 == null || v2 == null) {
            return;
        }

        v1.getEdges().addLast(new Edge<>(v2.getVertex(), weight));
        v2.getEdges().addLast(new Edge<>(v1.getVertex(), weight));
    }
    
    public boolean isConexo() {
        if (graph.size() == 0) return true;

        // Hacemos DFS desde el primer vertice
        ArrayList<E> visited = new ArrayList<>();
        E start = graph.get(0).getVertex().getData();
        dfsHelper(start, visited);

        // Si visito todos los vertices, es conexo
        return visited.size() == graph.size();
    }

    // Metodo auxiliar DFS (privado)
    private void dfsHelper(E current, ArrayList<E> visited) {
        visited.add(current);
        AdjList<E> adj = findVertex(current);
        if (adj == null) return;

        for (int i = 0; i < adj.getEdges().size(); i++) {
            E neighbor = adj.getEdges().get(i).getDestination().getData();
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }
    
    public ArrayList<E> shortPath(E origin, E destination) {
        // Mapa de distancias y predecesores usando indices
        int n = graph.size();
        int[] dist = new int[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];

        // Inicializar distancias en infinito
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        // Encontrar indice del origen
        int startIdx = getIndex(origin);
        if (startIdx == -1) return new ArrayList<>();
        dist[startIdx] = 0;

        for (int count = 0; count < n; count++) {
            // Elegir el vertice no visitado con menor distancia
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && (u == -1 || dist[i] < dist[u])) {
                    u = i;
                }
            }

            if (u == -1 || dist[u] == Integer.MAX_VALUE) break;
            visited[u] = true;

            // Relajar vecinos
            AdjList<E> adj = graph.get(u);
            for (int j = 0; j < adj.getEdges().size(); j++) {
                Edge<E> edge = adj.getEdges().get(j);
                int v = getIndex(edge.getDestination().getData());
                if (v != -1 && !visited[v]) {
                    int newDist = dist[u] + edge.getWeight();
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        prev[v] = u;
                    }
                }
            }
        }

        // Reconstruir camino
        ArrayList<E> path = new ArrayList<>();
        int destIdx = getIndex(destination);
        if (destIdx == -1 || dist[destIdx] == Integer.MAX_VALUE) {
            System.out.println("No hay camino entre " + origin + " y " + destination);
            return path;
        }

        for (int at = destIdx; at != -1; at = prev[at]) {
            path.add(0, graph.get(at).getVertex().getData());
        }

        System.out.println("Ruta mas corta: " + path + " | Costo: " + dist[destIdx]);
        return path;
    }

    // Metodo auxiliar: obtiene el indice de un vertice en graph
    private int getIndex(E data) {
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).getVertex().getData().equals(data)) {
                return i;
            }
        }
        return -1;
    }
    
    public Stack<E> Dijkstra(E origin, E destination) {
        // Reutilizamos shortPath y convertimos a Stack
        ArrayList<E> path = shortPath(origin, destination);

        Stack<E> stack = new Stack<>();
        // Apilamos en orden inverso para que al desapilar salga origen primero
        for (int i = path.size() - 1; i >= 0; i--) {
            stack.push(path.get(i));
        }

        return stack;
    }
}