package Ejercicios;


public class TestEjercicio5 {

    public static void main(String[] args)
            throws Exception {

        Ejercicio5<Integer> inventario =
                new Ejercicio5<>();

        inventario.insert(15);
        inventario.insert(8);
        inventario.insert(22);
        inventario.insert(5);
        inventario.insert(12);
        inventario.insert(18);
        inventario.insert(30);

        System.out.println("ARBOL:");

        inventario.drawBST();

        System.out.println("\nRANGO 10 - 25:");

        System.out.println(inventario.searchRange(10, 25));

        System.out.println("\nNODOS HOJA:");

        System.out.println(inventario.countLeaves());

        System.out.println("\nDESCENDENTE:");

        inventario.printDescending();
    }
}