package Ejercicios;

import Actividades.LinkedBST;

public class PruebaEJ4 {

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

        System.out.println("PARENTHESIZE:");

        bst.parenthesize();
    }
}