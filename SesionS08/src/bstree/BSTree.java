package bstree;

public class BSTree<E extends Comparable<E>> {

 protected Node<E> root;

 public BSTree() {

     root = null;
 }

 // ==========================================
 // RECORRIDO INORDEN
 // ==========================================

 public void inOrder() {

     inOrder(root);

     System.out.println();
 }

 private void inOrder(Node<E> node) {

     if (node != null) {

         inOrder(node.left);

         System.out.print(node.data + " ");

         inOrder(node.right);
     }
 }

 // ==========================================
 // MOSTRAR ARBOL
 // ==========================================

 public void printTree() {

     printTree(root, "", true);
 }

 private void printTree(Node<E> node,
                        String space,
                        boolean rootNode) {

     if (node != null) {

         System.out.println(
                 space +
                 (rootNode ? "└── " : "├── ")
                 + node.data
         );

         printTree(node.left,
                 space + "    ",
                 false);

         printTree(node.right,
                 space + "    ",
                 false);
     }
 }
}