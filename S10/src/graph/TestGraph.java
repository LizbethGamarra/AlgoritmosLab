package graph;
import graph.GraphLink;
import java.util.ArrayList;
import java.util.Stack;

public class TestGraph {
    public static void main(String[] args) {

        GraphLink<String> g = new GraphLink<>();
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");

        g.insertEdgeWeight("A", "B", 4);
        g.insertEdgeWeight("A", "C", 2);
        g.insertEdgeWeight("C", "B", 1);
        g.insertEdgeWeight("B", "D", 5);
        g.insertEdgeWeight("C", "D", 8);
        g.insertEdgeWeight("D", "E", 2);

        System.out.println(g);

        // isConexo
        System.out.println("Es conexo: " + g.isConexo());

        // shortPath -> ArrayList
        ArrayList<String> ruta = g.shortPath("A", "E");
        System.out.println("shortPath A->E: " + ruta);

        // Dijkstra -> Stack
        Stack<String> stack = g.Dijkstra("A", "E");
        System.out.print("Dijkstra A->E (stack): ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}