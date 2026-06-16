package Ejercicio2y3;
import graph.GraphLink;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Ejercicio 2: Red de ciudades usando GraphLink propio.
 * Grafo ponderado no dirigido donde vertices=ciudades, aristas=carreteras.
 */
public class CityNetwork {

    private GraphLink<String> graph;

    public CityNetwork() {
        graph = new GraphLink<>();
    }

    // Agrega una ciudad al grafo
    public void addCity(String city) {
        graph.insertVertex(city);
    }

    // Agrega una carretera con distancia en km
    public void addRoad(String origin, String destination, int km) {
        graph.insertEdgeWeight(origin, destination, km);
    }

    // Muestra todas las ciudades y carreteras
    public void showNetwork() {
        System.out.println("=== RED DE CIUDADES ===");
        System.out.println(graph);
    }

    // Camino mas corto entre dos ciudades (retorna ArrayList)
    public ArrayList<String> shortestPath(String origin, String destination) {
        System.out.println("=== CAMINO MAS CORTO: " + origin + " -> " + destination + " ===");
        return graph.shortPath(origin, destination);
    }

    // Camino mas corto usando Dijkstra (retorna Stack)
    public Stack<String> dijkstra(String origin, String destination) {
        System.out.println("=== DIJKSTRA: " + origin + " -> " + destination + " ===");
        return graph.Dijkstra(origin, destination);
    }

    // Verifica si la red es conexa
    public boolean isConnected() {
        return graph.isConexo();
    }

    public static void main(String[] args) {

        CityNetwork network = new CityNetwork();

        // Agregar ciudades
        System.out.println("--- Agregando ciudades ---");
        network.addCity("Arequipa");
        network.addCity("Cusco");
        network.addCity("Puno");
        network.addCity("Tacna");
        network.addCity("Moquegua");

        // Agregar carreteras
        System.out.println("\n--- Agregando carreteras ---");
        network.addRoad("Arequipa",  "Cusco",    510);
        network.addRoad("Arequipa",  "Moquegua", 230);
        network.addRoad("Moquegua",  "Tacna",    160);
        network.addRoad("Cusco",     "Puno",     390);
        network.addRoad("Puno",      "Tacna",    420);

        // Mostrar red
        System.out.println();
        network.showNetwork();

        // Verificar conectividad
        System.out.println("La red es conexa: " + network.isConnected());

        // Caminos mas cortos
        System.out.println();
        ArrayList<String> ruta1 = network.shortestPath("Arequipa", "Tacna");
        System.out.println("Ruta: " + ruta1);

        System.out.println();
        ArrayList<String> ruta2 = network.shortestPath("Arequipa", "Puno");
        System.out.println("Ruta: " + ruta2);

        System.out.println();
        Stack<String> ruta3 = network.dijkstra("Cusco", "Tacna");
        System.out.print("Ruta (stack): ");
        while (!ruta3.isEmpty()) System.out.print(ruta3.pop() + " ");
        System.out.println();
    }
}