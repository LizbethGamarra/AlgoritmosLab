package Ejercicio4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * GraphAnalyzer: analiza propiedades de un grafo dirigido.
 * - isConexo
 * - isPlanar
 * - isIsomorphic
 * - isSelfComplementary
 */
public class GraphAnalyzer<E> {

    // ------------------------------------------------------------------ //
    // isConexo: desde cualquier vertice se puede llegar a todos los demas
    // ------------------------------------------------------------------ //
    public boolean isConexo(GraphListEdge<E> g) {
        if (g.vertexCount() == 0) return true;

        ArrayList<E> visited = new ArrayList<>();
        E start = g.getVertices().get(0).getData();
        dfs(g, start, visited);

        boolean result = visited.size() == g.vertexCount();
        System.out.println("isConexo: " + result
            + " (visitados " + visited.size()
            + "/" + g.vertexCount() + " vertices)");
        return result;
    }

    private void dfs(GraphListEdge<E> g, E current, ArrayList<E> visited) {
        visited.add(current);
        List<E> neighbors = g.adjacentVertices(current);
        for (E neighbor : neighbors) {
            if (!visited.contains(neighbor))
                dfs(g, neighbor, visited);
        }
    }

    // ------------------------------------------------------------------ //
    // isPlanar: condicion de Euler E <= 3V - 6 (necesaria, no suficiente)
    // ------------------------------------------------------------------ //
    public boolean isPlanar(GraphListEdge<E> g) {
        int V = g.vertexCount();
        int E = g.edgeCount();

        if (V < 3) {
            System.out.println("isPlanar: true (menos de 3 vertices)");
            return true;
        }

        boolean result = E <= (3 * V - 6);
        System.out.println("isPlanar: " + result
            + " | Aristas=" + E
            + ", 3V-6=" + (3 * V - 6));
        return result;
    }

    // ------------------------------------------------------------------ //
    // isIsomorphic: misma cantidad de vertices, aristas y secuencia de grados
    // ------------------------------------------------------------------ //
    public boolean isIsomorphic(GraphListEdge<E> g1, GraphListEdge<E> g2) {
        // Condicion 1: mismo numero de vertices y aristas
        if (g1.vertexCount() != g2.vertexCount() ||
            g1.edgeCount()   != g2.edgeCount()) {
            System.out.println("isIsomorphic: false"
                + " (distinto numero de vertices o aristas)");
            return false;
        }

        // Condicion 2: misma secuencia de grados ordenada
        List<Integer> deg1 = getDegreeSequence(g1);
        List<Integer> deg2 = getDegreeSequence(g2);
        Collections.sort(deg1);
        Collections.sort(deg2);

        boolean result = deg1.equals(deg2);
        System.out.println("isIsomorphic: " + result
            + " | Grados G1=" + deg1 + " G2=" + deg2);
        return result;
    }

    // Calcula grado saliente de cada vertice
    private List<Integer> getDegreeSequence(GraphListEdge<E> g) {
        List<Integer> degrees = new ArrayList<>();
        for (int i = 0; i < g.vertexCount(); i++) {
            E data = g.getVertices().get(i).getData();
            degrees.add(g.adjacentVertices(data).size());
        }
        return degrees;
    }

    // ------------------------------------------------------------------ //
    // isSelfComplementary: el complemento de G es isomorfo a G
    // ------------------------------------------------------------------ //
    public boolean isSelfComplementary(GraphListEdge<E> g) {
        GraphListEdge<E> complement = buildComplement(g);

        System.out.println("--- Auto-complementario ---");
        System.out.println("Original:   " + g.edgeCount() + " aristas");
        System.out.println("Complemento:" + complement.edgeCount() + " aristas");

        boolean result = isIsomorphic(g, complement);
        System.out.println("isSelfComplementary: " + result);
        return result;
    }

    // Construye el complemento: mismos vertices, aristas invertidas
    private GraphListEdge<E> buildComplement(GraphListEdge<E> g) {
        GraphListEdge<E> comp = new GraphListEdge<>();

        // Copiar vertices
        for (int i = 0; i < g.vertexCount(); i++)
            comp.insertVertex(g.getVertices().get(i).getData());

        // Agregar aristas que NO existen en g (excepto lazos i==j)
        for (int i = 0; i < g.vertexCount(); i++) {
            for (int j = 0; j < g.vertexCount(); j++) {
                if (i == j) continue;
                E src = g.getVertices().get(i).getData();
                E dst = g.getVertices().get(j).getData();
                if (!g.searchEdge(src, dst))
                    comp.insertEdge(src, dst);
            }
        }
        return comp;
    }
}