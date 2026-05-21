package Ejercicio5;

public class TestBFSRecursivo {

    public static void main(String[] args) {

        AVLTree arbol =
                new AVLTree();

        // ======================================
        // DATOS DEL EJERCICIO
        // ======================================

        int datos[] = {

                50, 30, 70,
                20, 40, 60,
                80, 10, 25,
                65
        };

        // ======================================
        // INSERTAR DATOS
        // ======================================

        for (int x : datos)

            arbol.insertar(x);

        // ======================================
        // MOSTRAR ARBOL
        // ======================================

        System.out.println(
                "=================================="
        );

        System.out.println(
                "ARBOL AVL"
        );

        System.out.println(
                "=================================="
        );

        arbol.mostrarArbol();

        // ======================================
        // RECORRIDO INORDEN
        // ======================================

        arbol.inorden();

        // ======================================
        // BFS RECURSIVO
        // ======================================

        System.out.println(
                "\nRecorrido esperado:"
        );

        System.out.println(
                "50, 30, 70, 20, 40, 60, 80, 10, 25, 65"
        );

        arbol.recorridoAmplitud();
    }
}