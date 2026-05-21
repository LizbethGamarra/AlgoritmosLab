package Ejercicio3;

public class TestEliminarAVL {

    public static void main(String[] args) {

        AVLTree avl =
                new AVLTree();

        // ======================================
        // INSERTAR DATOS
        // ======================================

        int datos[] = {

                30, 20, 40,
                10, 25, 35,
                50, 5, 15,
                45, 60
        };

        System.out.println(
                "=========== INSERTANDO ==========="
        );

        for (int x : datos)

            avl.insertar(x);

        System.out.println(
                "\nÁRBOL AVL INICIAL"
        );

        avl.mostrarArbol();

        // ======================================
        // ELIMINACIONES
        // ======================================

        int eliminar[] = {
                5,   // hoja
                50,  // un hijo
                30   // dos hijos
        };

        System.out.println(
                "\n=========== ELIMINACIONES ==========="
        );

        for (int x : eliminar)

            avl.eliminar(x);
    }
}