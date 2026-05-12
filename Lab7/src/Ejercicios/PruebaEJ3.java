package Ejercicios;

import Actividades.LinkedBST;

public class PruebaEJ3 {

    public static boolean sameArea(
            LinkedBST<Integer> bst1,
            LinkedBST<Integer> bst2) {

        return bst1.areaBST()
                == bst2.areaBST();
    }

    public static void main(String[] args)
            throws Exception {

        LinkedBST<Integer> bst1 =
                new LinkedBST<>();

        LinkedBST<Integer> bst2 =
                new LinkedBST<>();

        // ARBOL 1
        bst1.insert(15);
        bst1.insert(8);
        bst1.insert(22);
        bst1.insert(5);
        bst1.insert(12);
        bst1.insert(18);
        bst1.insert(30);

        // ARBOL 2
        bst2.insert(50);
        bst2.insert(30);
        bst2.insert(70);
        bst2.insert(20);
        bst2.insert(40);
        bst2.insert(60);
        bst2.insert(80);

        System.out.println("ARBOL 1:");
        bst1.drawBST();

        System.out.println("\nARBOL 2:");
        bst2.drawBST();

        System.out.println("\nAREA BST1:");
        System.out.println(
                bst1.areaBST());

        System.out.println("\nAREA BST2:");
        System.out.println(
                bst2.areaBST());

        System.out.println("\nMISMA AREA:");
        System.out.println(
                sameArea(bst1, bst2));
    }
}