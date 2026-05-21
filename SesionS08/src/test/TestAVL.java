package test;

import avltree.AVLTree;
import exceptions.ItemDuplicated;

public class TestAVL {

    public static void main(String[] args) {

        AVLTree<Integer> avl =
                new AVLTree<>();

        // =============================================
        // 8 CASOS DE PRUEBA
        // =============================================

        int datos[] = {

                // SIN ROTACION
                30,

                // RSR (II)
                20,
                10,

                // RSL (DD)
                40,
                50,

                // RDR (ID)
                25,

                // RDL (DI)
                45,

                // MAS PRUEBAS
                60,
                55,
                70,
                65
        };

        try {

            for (int x : datos) {

                System.out.println(
                        "\n================================"
                );

                System.out.println(
                        "INSERTANDO: " + x
                );

                System.out.println(
                        "================================"
                );

                avl.insert(x);

                avl.printTree();
            }

            // =========================================
            // RECORRIDO INORDEN
            // =========================================

            System.out.println(
                    "\n================================"
            );

            System.out.println(
                    "RECORRIDO INORDEN"
            );

            System.out.println(
                    "================================"
            );

            avl.inOrder();
        }

        catch (ItemDuplicated e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }
}