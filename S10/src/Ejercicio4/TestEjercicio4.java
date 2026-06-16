package Ejercicio4;

public class TestEjercicio4 {

    public static void main(String[] args) {

        GraphAnalyzer<String> analyzer = new GraphAnalyzer<>();

        // ---- G1: ciclo dirigido A->B->C->D->A ----
        GraphListEdge<String> g1 = new GraphListEdge<>();
        g1.insertVertex("A"); g1.insertVertex("B");
        g1.insertVertex("C"); g1.insertVertex("D");
        g1.insertEdge("A", "B");
        g1.insertEdge("B", "C");
        g1.insertEdge("C", "D");
        g1.insertEdge("D", "A");

        // ---- G2: mismo ciclo con otros nombres (isomorfo a G1) ----
        GraphListEdge<String> g2 = new GraphListEdge<>();
        g2.insertVertex("W"); g2.insertVertex("X");
        g2.insertVertex("Y"); g2.insertVertex("Z");
        g2.insertEdge("W", "X");
        g2.insertEdge("X", "Y");
        g2.insertEdge("Y", "Z");
        g2.insertEdge("Z", "W");

        // ---- G3: grafo NO isomorfo a G1 ----
        GraphListEdge<String> g3 = new GraphListEdge<>();
        g3.insertVertex("A"); g3.insertVertex("B");
        g3.insertVertex("C"); g3.insertVertex("D");
        g3.insertEdge("A", "B");
        g3.insertEdge("A", "C");
        g3.insertEdge("A", "D"); // todos salen de A

        System.out.println("========================================");
        System.out.println("GRAFO G1: " + g1);
        System.out.println("========================================");
        analyzer.isConexo(g1);
        analyzer.isPlanar(g1);

        System.out.println("\n========================================");
        System.out.println("ISOMORFISMO G1 vs G2 (deben ser iguales)");
        System.out.println("========================================");
        analyzer.isIsomorphic(g1, g2);

        System.out.println("\n========================================");
        System.out.println("ISOMORFISMO G1 vs G3 (deben ser distintos)");
        System.out.println("========================================");
        analyzer.isIsomorphic(g1, g3);

        System.out.println("\n========================================");
        System.out.println("AUTO-COMPLEMENTARIO G1");
        System.out.println("========================================");
        analyzer.isSelfComplementary(g1);
    }
}