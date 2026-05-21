package Actividades;



public class Uno {

    public static void main(String[] args) {

        AVL arbol = new AVL();

        int datos[] = {
                30, 15, 20, 50, 40,
                60, 70, 10, 25, 45,
                55, 65, 75
        };

        for (int x : datos) {

            System.out.println("\n================================");
            System.out.println("Insertando: " + x);
            System.out.println("================================");

            arbol.insertar(x);

            System.out.println("\nÁrbol AVL:");
            arbol.mostrar();
        }

        System.out.println("\n================================");
        System.out.println("ÁRBOL AVL FINAL");
        System.out.println("================================");

        arbol.mostrar();
    }
}
