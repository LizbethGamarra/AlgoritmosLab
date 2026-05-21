package Ejercicio4;

public class TestRecorridoAmplitud {

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

        arbol1.inorden();

        arbol1.preorden();

        arbol1.recorridoAmplitud();

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

        arbol2.inorden();

        arbol2.preorden();

        arbol2.recorridoAmplitud();

        // ======================================
        // COMPARACION
        // ======================================

        System.out.println(
                "\n=================================="
        );

        System.out.println(
                "COMPARACION DE RECORRIDOS"
        );

        System.out.println(
                "=================================="
        );

        System.out.println(
                "Inorden: visita izquierda, raíz y derecha."
        );

        System.out.println(
                "Preorden: visita raíz, izquierda y derecha."
        );

        System.out.println(
                "Amplitud: recorre el árbol nivel por nivel."
        );
    }
}