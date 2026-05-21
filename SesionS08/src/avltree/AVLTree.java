package avltree;

import bstree.BSTree;
import bstree.Node;
import exceptions.ItemDuplicated;

public class AVLTree<E extends Comparable<E>>
        extends BSTree<E> {

    // =================================================
    // CLASE INTERNA NodeAVL
    // =================================================

    class NodeAVL extends Node<E> {

        protected int bf;

        public NodeAVL(E data) {

            super(data);

            bf = 0;
        }

        @Override
        public String toString() {

            return data + "(bf=" + bf + ")";
        }
    }

    // indicador de cambio de altura
    private boolean height;

    // =================================================
    // CONSTRUCTOR
    // =================================================

    public AVLTree() {

        super();

        height = false;
    }

    // =================================================
    // INSERT
    // =================================================

    public void insert(E x)
            throws ItemDuplicated {

        this.height = false;

        this.root =
                insert(x, (NodeAVL) this.root);
    }

    // =================================================
    // INSERT RECURSIVO
    // =================================================

    protected Node<E> insert(E x,
                             NodeAVL node)
            throws ItemDuplicated {

        NodeAVL fat = node;

        // =============================================
        // NUEVO NODO
        // =============================================

        if (node == null) {

            this.height = true;

            fat = new NodeAVL(x);
        }

        else {

            int resC =
                    node.data.compareTo(x);

            // =========================================
            // DUPLICADO
            // =========================================

            if (resC == 0)

                throw new ItemDuplicated(
                        x + " ya existe en el arbol..."
                );

            // =========================================
            // INSERTAR DERECHA
            // =========================================

            if (resC < 0) {

                fat.right =
                        insert(x,
                                (NodeAVL) node.right);

                if (this.height)

                    switch (fat.bf) {

                        case -1:

                            fat.bf = 0;

                            this.height = false;
                            break;

                        case 0:

                            fat.bf = 1;

                            this.height = true;
                            break;

                        case 1:

                            // bf = 2
                            fat = balanceToLeft(fat);

                            this.height = false;
                            break;
                    }
            }

            // =========================================
            // INSERTAR IZQUIERDA
            // =========================================

            else {

                fat.left =
                        insert(x,
                                (NodeAVL) node.left);

                if (this.height)

                    switch (fat.bf) {

                        case 1:

                            fat.bf = 0;

                            this.height = false;
                            break;

                        case 0:

                            fat.bf = -1;

                            this.height = true;
                            break;

                        case -1:

                            // bf = -2
                            fat = balanceToRight(fat);

                            this.height = false;
                            break;
                    }
            }
        }

        return fat;
    }

    // =================================================
    // BALANCE TO LEFT
    // bf = 2
    // =================================================

    private NodeAVL balanceToLeft(NodeAVL node) {

        NodeAVL hijo =
                (NodeAVL) node.right;

        // =============================================
        // CASO DD
        // =============================================

        if (hijo.bf == 1) {

            node.bf = 0;
            hijo.bf = 0;

            node = rotateSL(node);
        }

        // =============================================
        // CASO DI
        // =============================================

        else if (hijo.bf == -1) {

            NodeAVL nieto =
                    (NodeAVL) hijo.left;

            switch (nieto.bf) {

                case -1:

                    node.bf = 0;
                    hijo.bf = 1;
                    break;

                case 0:

                    node.bf = 0;
                    hijo.bf = 0;
                    break;

                case 1:

                    node.bf = -1;
                    hijo.bf = 0;
                    break;
            }

            nieto.bf = 0;

            // rotacion derecha
            node.right =
                    rotateSR(hijo);

            // rotacion izquierda
            node =
                    rotateSL(node);
        }

        // =============================================
        // CASO ELIMINACION
        // =============================================

        else if (hijo.bf == 0) {

            node.bf = 1;
            hijo.bf = -1;

            node =
                    rotateSL(node);
        }

        return node;
    }

    // =================================================
    // BALANCE TO RIGHT
    // bf = -2
    // =================================================

    private NodeAVL balanceToRight(NodeAVL node) {

        NodeAVL hijo =
                (NodeAVL) node.left;

        // =============================================
        // CASO II
        // =============================================

        if (hijo.bf == -1) {

            node.bf = 0;
            hijo.bf = 0;

            node =
                    rotateSR(node);
        }

        // =============================================
        // CASO ID
        // =============================================

        else if (hijo.bf == 1) {

            NodeAVL nieto =
                    (NodeAVL) hijo.right;

            switch (nieto.bf) {

                case -1:

                    node.bf = 1;
                    hijo.bf = 0;
                    break;

                case 0:

                    node.bf = 0;
                    hijo.bf = 0;
                    break;

                case 1:

                    node.bf = 0;
                    hijo.bf = -1;
                    break;
            }

            nieto.bf = 0;

            // rotacion izquierda
            node.left =
                    rotateSL(hijo);

            // rotacion derecha
            node =
                    rotateSR(node);
        }

        // =============================================
        // CASO ELIMINACION
        // =============================================

        else if (hijo.bf == 0) {

            node.bf = -1;
            hijo.bf = 1;

            node =
                    rotateSR(node);
        }

        return node;
    }

    // =================================================
    // ROTACION SIMPLE IZQUIERDA
    // RSL
    // =================================================

    private NodeAVL rotateSL(NodeAVL node) {

        NodeAVL p =
                (NodeAVL) node.right;

        node.right = p.left;

        p.left = node;

        return p;
    }

    // =================================================
    // ROTACION SIMPLE DERECHA
    // RSR
    // =================================================

    private NodeAVL rotateSR(NodeAVL node) {

        NodeAVL p =
                (NodeAVL) node.left;

        node.left = p.right;

        p.right = node;

        return p;
    }

    // =================================================
    // RECORRIDO INORDEN
    // =================================================

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

    // =================================================
    // MOSTRAR ARBOL
    // =================================================

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
                            + node
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