package Actividades;

public class Prueba {

    public static void main(String[] args) {

        LinkedBST<Integer> bst =
                new LinkedBST<>();

        try {

            bst.insert(50);
            bst.insert(30);
            bst.insert(70);
            bst.insert(20);
            bst.insert(40);
            bst.insert(60);
            bst.insert(80);

            System.out.println("INORDER:");
            System.out.println(bst.inOrder());

            System.out.println("\nPREORDER:");
            System.out.println(bst.preOrder());

            System.out.println("\nPOSTORDER:");
            System.out.println(bst.postOrder());

            System.out.println("\nMINIMO:");
            System.out.println(bst.findMin());

            System.out.println("\nMAXIMO:");
            System.out.println(bst.findMax());

            System.out.println("\nTOSTRING:");
            System.out.println(bst);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}