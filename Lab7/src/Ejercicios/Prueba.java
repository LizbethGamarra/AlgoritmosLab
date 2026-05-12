package Ejercicios;

import Actividades.LinkedBST;

public class Prueba {

    public static void main(String[] args)
            throws Exception {

        LinkedBST<Integer> bst =
                new LinkedBST<>();

        bst.insert(15);
        bst.insert(8);
        bst.insert(22);
        bst.insert(5);
        bst.insert(12);
        bst.insert(18);
        bst.insert(30);

        System.out.println("INORDER:");
        System.out.println(bst);

        System.out.println("\nTOTAL NODOS:");
        System.out.println(
                bst.countAllNodes());

        System.out.println("\nNODOS NO HOJA:");
        System.out.println(
                bst.countNodes());

        System.out.println("\nHEIGHT 22:");
        System.out.println(
                bst.height(22));

        System.out.println("\nAMPLITUDE NIVEL 2:");
        System.out.println(
                bst.amplitude(2));

        System.out.println("\nMIN:");
        System.out.println(
                bst.findMin());

        System.out.println("\nMAX:");
        System.out.println(
                bst.findMax());
    }
}