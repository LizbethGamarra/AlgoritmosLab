package Ejercicio1;

public class GestorTicketsAVL {

    public static void main(String[] args) {

        ArbolAVL arbol =
                new ArbolAVL();

        // ======================================
        // INSERCIONES
        // ======================================

        int insertar[] = {
                30, 10, 20,
                40, 50, 25
        };

        System.out.println(
                "=========== INSERCIONES ==========="
        );

        for (int x : insertar)

            arbol.insertar(x);

        // ======================================
        // BUSQUEDAS
        // ======================================

        System.out.println(
                "\n=========== BUSQUEDAS ==========="
        );

        arbol.buscar(20);

        arbol.buscar(60);

        // ======================================
        // RECORRIDO
        // ======================================

        System.out.println(
                "\n=========== INORDEN ==========="
        );

        arbol.inorden();

        // ======================================
        // ELIMINACIONES
        // ======================================

        int eliminar[] = {
                10, 40, 30
        };

        System.out.println(
                "\n=========== ELIMINACIONES ==========="
        );

        for (int x : eliminar)

            arbol.eliminar(x);

        // ======================================
        // ARBOL FINAL
        // ======================================

        System.out.println(
                "\n=========== ARBOL FINAL ==========="
        );

        arbol.mostrarArbol();
    }
}