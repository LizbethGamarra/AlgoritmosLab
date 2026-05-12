package Ejercicios;

import Actividades.LinkedBST;

public class Ejercicio5<E extends Comparable<E>>
        extends LinkedBST<E> {

    // ================= SEARCH RANGE =================

    public String searchRange(E min,
                              E max) {

        return searchRange(root, min, max);
    }

    private String searchRange(
            LinkedBST<E>.Node node,
            E min,
            E max) {

        if (node == null) {
            return "";
        }

        String result = "";

        if (node.data.compareTo(min) > 0) {

            result += searchRange(node.left,
                                  min,
                                  max);
        }

        if (node.data.compareTo(min) >= 0 && node.data.compareTo(max) <= 0) {

            result += node.data + " ";
        }

        if (node.data.compareTo(max) < 0) {

            result += searchRange(node.right,
                                  min,
                                  max);
        }

        return result;
    }

    // ================= COUNT LEAVES =================

    public int countLeaves() {

        return countLeaves(root);
    }

    private int countLeaves(
            LinkedBST<E>.Node node) {

        if (node == null) {
            return 0;
        }

        if (node.left == null &&
            node.right == null) {

            return 1;
        }

        return countLeaves(node.left)
                + countLeaves(node.right);
    }

    // ================= PRINT DESCENDING =================

    public void printDescending() {

        printDescending(root);

        System.out.println();
    }

    private void printDescending(
            LinkedBST<E>.Node node) {

        if (node == null) {
            return;
        }

        printDescending(node.right);

        System.out.print(node.data + " ");

        printDescending(node.left);
    }
}
