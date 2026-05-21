package Ejemplo1;

public class TestProductosAVL {

    public static void main(String[] args) {

        AVLProductos almacen =
                new AVLProductos();

        // ======================================
        // INSERTAR PRODUCTOS
        // ======================================

        System.out.println(
                "========== INSERTAR PRODUCTOS =========="
        );

        int productos[] = {

                50, 30, 70,
                20, 40, 60,
                80, 10, 25
        };

        for (int x : productos)

            almacen.insertar(x);

        almacen.mostrarProductos();

        System.out.println(
                "\nÁrbol AVL:"
        );

        almacen.mostrarArbol();

        // ======================================
        // BUSCAR PRODUCTOS
        // ======================================

        System.out.println(
                "\n========== BUSQUEDAS =========="
        );

        int buscar1 = 40;
        int buscar2 = 90;

        if (almacen.buscar(buscar1))

            System.out.println(
                    "Producto "
                            + buscar1
                            + " encontrado."
            );

        else

            System.out.println(
                    "Producto "
                            + buscar1
                            + " NO encontrado."
            );

        if (almacen.buscar(buscar2))

            System.out.println(
                    "Producto "
                            + buscar2
                            + " encontrado."
            );

        else

            System.out.println(
                    "Producto "
                            + buscar2
                            + " NO encontrado."
            );

        // ======================================
        // ELIMINAR PRODUCTOS
        // ======================================

        System.out.println(
                "\n========== ELIMINAR PRODUCTOS =========="
        );

        almacen.eliminar(20);

        almacen.eliminar(70);

        almacen.mostrarProductos();

        System.out.println(
                "\nÁrbol AVL actualizado:"
        );

        almacen.mostrarArbol();
    }
}