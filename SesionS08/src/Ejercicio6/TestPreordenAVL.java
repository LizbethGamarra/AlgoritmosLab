package Ejercicio6;

public class TestPreordenAVL {

    public static void main(String[] args) {

        // ======================================
        // ARBOL AVL 1
        // ======================================

        AVLTree arbol1 =
                new AVLTree();

        int datos1[] = {

                30, 20, 40,
                10, 25, 35,
                50
        };

        for (int x : datos1)

            arbol1.insertar(x);

        System.out.println(
                "=================================="
        );

        System.out.println(
                "ARBOL AVL 1"
        );

        System.out.println(
                "=================================="
        );

        arbol1.mostrarArbol();

        arbol1.recorridoPreorden();

        arbol1.recorridoInorden();

        // ======================================
        // ARBOL AVL 2
        // ======================================

        AVLTree arbol2 =
                new AVLTree();

        int datos2[] = {

                50, 30, 70,
                20, 40, 60,
                80, 10, 25
        };

        for (int x : datos2)

            arbol2.insertar(x);

        System.out.println(
                "\n=================================="
        );

        System.out.println(
                "ARBOL AVL 2"
        );

        System.out.println(
                "=================================="
        );

        arbol2.mostrarArbol();

        arbol2.recorridoPreorden();

        arbol2.recorridoInorden();

        // ======================================
        // ARBOL AVL 3
        // ======================================

        AVLTree arbol3 =
                new AVLTree();

        int datos3[] = {

                10, 20, 30,
                40, 50, 60
        };

        for (int x : datos3)

            arbol3.insertar(x);

        System.out.println(
                "\n=================================="
        );

        System.out.println(
                "ARBOL AVL 3"
        );

        System.out.println(
                "=================================="
        );

        arbol3.mostrarArbol();

        arbol3.recorridoPreorden();

        arbol3.recorridoInorden();

        // ======================================
        // CONCLUSION
        // ======================================

        System.out.println(
                "\n=================================="
        );

        System.out.println(
                "CONCLUSION"
        );

        System.out.println(
                "=================================="
        );

        System.out.println(
                "El recorrido preorden visita "
                        + "primero la raíz, luego "
                        + "el subárbol izquierdo "
                        + "y finalmente el derecho."
        );

        System.out.println(
                "El AVL mantiene el árbol "
                        + "balanceado después de "
                        + "cada inserción."
        );
    }
}