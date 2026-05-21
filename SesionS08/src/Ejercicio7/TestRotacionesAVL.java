package Ejercicio7;

public class TestRotacionesAVL {

    public static void main(String[] args) {

        AVLTree avl =
                new AVLTree();

        // ======================================
        // INSERCIONES
        // ======================================

        System.out.println(
                "=========== INSERCIONES ==========="
        );

        int insertar[] = {

                // Caso II
                30, 20, 10,

                // Caso DD
                40, 50,

                // Caso ID
                25,

                // Caso DI
                45
        };

        for (int x : insertar)

            avl.insertar(x);

        // ======================================
        // ELIMINACIONES
        // ======================================

        System.out.println(
                "\n=========== ELIMINACIONES ==========="
        );

        int eliminar[] = {

                50,
                45,
                30
        };

        for (int x : eliminar)

            avl.eliminar(x);

        // ======================================
        // ARBOL FINAL
        // ======================================

        System.out.println(
                "\n=========== ARBOL FINAL ==========="
        );

        avl.mostrarArbol();
    }
}