package Ejemplo2;

public class TestTurnosAVL {

    public static void main(String[] args) {

        AVLTurnos clinica =
                new AVLTurnos();

        // ======================================
        // INSERTAR TURNOS
        // ======================================

        System.out.println(
                "========== REGISTRO DE TURNOS =========="
        );

        int turnos[] = {

                50, 30, 70,
                20, 40, 60,
                80, 10, 25
        };

        for (int x : turnos)

            clinica.insertar(x);

        clinica.mostrarTurnos();

        System.out.println(
                "\nÁrbol AVL:"
        );

        clinica.mostrarArbol();

        // ======================================
        // BUSCAR TURNOS
        // ======================================

        System.out.println(
                "\n========== BUSQUEDA DE TURNOS =========="
        );

        int buscar1 = 40;
        int buscar2 = 90;

        if (clinica.buscar(buscar1))

            System.out.println(
                    "Turno "
                            + buscar1
                            + " encontrado."
            );

        else

            System.out.println(
                    "Turno "
                            + buscar1
                            + " NO encontrado."
            );

        if (clinica.buscar(buscar2))

            System.out.println(
                    "Turno "
                            + buscar2
                            + " encontrado."
            );

        else

            System.out.println(
                    "Turno "
                            + buscar2
                            + " NO encontrado."
            );

        // ======================================
        // ELIMINAR TURNOS
        // ======================================

        System.out.println(
                "\n========== ELIMINAR TURNOS =========="
        );

        clinica.eliminar(20);

        clinica.eliminar(70);

        clinica.mostrarTurnos();

        System.out.println(
                "\nÁrbol AVL actualizado:"
        );

        clinica.mostrarArbol();
    }
}