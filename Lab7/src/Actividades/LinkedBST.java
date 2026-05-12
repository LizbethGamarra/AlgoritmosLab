package Actividades;

public class LinkedBST<E extends Comparable<E>>
        implements BinarySearchTree<E> {

    //  NODO 

	public class Node {

	    public E data;
	    public Node left;
	    public Node right;

	    public Node(E data) {
	        this.data = data;
	        this.left = null;
	        this.right = null;
	    }
	}
	
    //ATRIBUTO ROOT 

    protected Node root;

    //  CONSTRUCTOR 

    public LinkedBST() {
        root = null;
    }
    
    protected Node getRoot() {
        return root;
    }

    //  IS EMPTY 

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    //  INSERT 

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private Node insertRec(Node node, E data)
            throws ItemDuplicated {

        if (node == null) {
            return new Node(data);
        }

        int cmp = data.compareTo(node.data);

        if (cmp < 0) {

            node.left = insertRec(node.left, data);

        } else if (cmp > 0) {

            node.right = insertRec(node.right, data);

        } else {

            throw new ItemDuplicated(
                    "Elemento duplicado");
        }

        return node;
    }

    //  SEARCH 

    @Override
    public E search(E data) throws ItemNoFound {

        Node result = searchRec(root, data);

        if (result == null) {
            throw new ItemNoFound(
                    "Elemento no encontrado");
        }

        return result.data;
    }

    private Node searchRec(Node node, E data) {

        if (node == null) {
            return null;
        }

        int cmp = data.compareTo(node.data);

        if (cmp == 0) {
            return node;
        }

        if (cmp < 0) {
            return searchRec(node.left, data);
        }

        return searchRec(node.right, data);
    }

    // DELETE 

    @Override
    public void delete(E data)
            throws ExceptionIsEmpty {

        if (isEmpty()) {

            throw new ExceptionIsEmpty(
                    "El árbol está vacío");
        }

        root = deleteRec(root, data);
    }

    private Node deleteRec(Node node, E data) {

        if (node == null) {
            return null;
        }

        int cmp = data.compareTo(node.data);

        if (cmp < 0) {

            node.left = deleteRec(node.left, data);

        } else if (cmp > 0) {

            node.right = deleteRec(node.right, data);

        } else {

            // CASO 1: SIN HIJOS
            if (node.left == null &&
                node.right == null) {

                return null;
            }

            // CASO 2: UN HIJO
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            // CASO 3: DOS HIJOS
            Node min = findMin(node.right);

            node.data = min.data;

            node.right =
                    deleteRec(node.right, min.data);
        }

        return node;
    }

    // FIND MIN PARA DELETE 

    private Node findMin(Node node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    // FIND MIN NODE 

    private E findMinNode(Node node)
            throws ItemNoFound {

        if (node == null) {

            throw new ItemNoFound(
                    "Árbol vacío");
        }

        while (node.left != null) {
            node = node.left;
        }

        return search(node.data);
    }

    //  FIND MAX NODE 

    private E findMaxNode(Node node)
            throws ItemNoFound {

        if (node == null) {

            throw new ItemNoFound(
                    "Árbol vacío");
        }

        while (node.right != null) {
            node = node.right;
        }

        return search(node.data);
    }

    // METODOS PUBLICOS MIN Y MAX 

    public E findMin() throws ItemNoFound {
        return findMinNode(root);
    }

    public E findMax() throws ItemNoFound {
        return findMaxNode(root);
    }

    //  INORDER 

    public String inOrder() {
        return inOrder(root);
    }

    private String inOrder(Node node) {

        if (node == null) {
            return "";
        }

        return inOrder(node.left)
                + node.data + " "
                + inOrder(node.right);
    }

    //  PREORDER 

    public String preOrder() {
        return preOrder(root);
    }

    private String preOrder(Node node) {

        if (node == null) {
            return "";
        }

        return node.data + " "
                + preOrder(node.left)
                + preOrder(node.right);
    }

    //  POSTORDER 

    public String postOrder() {
        return postOrder(root);
    }

    private String postOrder(Node node) {

        if (node == null) {
            return "";
        }

        return postOrder(node.left)
                + postOrder(node.right)
                + node.data + " ";
    }

    //  TOSTRING 

    @Override
    public String toString() {
        return inOrder();
    }
    // EJERCICIO 2 
    public void destroyNodes() throws ExceptionIsEmpty {

        if (isEmpty()) {

            throw new ExceptionIsEmpty(
                    "El árbol está vacío");
        }

        root = null;
    }
    public int countAllNodes() {
        return countAllNodes(root);
    }

    private int countAllNodes(Node node) {

        if (node == null) {
            return 0;
        }

        return 1
                + countAllNodes(node.left)
                + countAllNodes(node.right);
    }
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node node) {

        if (node == null) {
            return 0;
        }

        // si es hoja
        if (node.left == null &&
            node.right == null) {

            return 0;
        }

        return 1
                + countNodes(node.left)
                + countNodes(node.right);
    }
    public int height(E x) {

        Node current = root;

        // BUSQUEDA ITERATIVA
        while (current != null) {

            int cmp = x.compareTo(current.data);

            if (cmp == 0) {

                return calculateHeight(current);
            }

            if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return -1;
    }
    private int calculateHeight(Node node) {

        if (node == null) {
            return -1;
        }

        int leftHeight =
                calculateHeight(node.left);

        int rightHeight =
                calculateHeight(node.right);

        return 1 + Math.max(leftHeight,
                            rightHeight);
    }
    public int amplitude(int level) {

        return amplitude(root, level);
    }
    private int amplitude(Node node,int level) {

	if (node == null) {
	return 0;
	}
	
	if (level == 0) {
	return 1;
	}
	
	return amplitude(node.left,
	           level - 1)
	
	+ amplitude(node.right,
	           level - 1);
    }
    //EJERCICIO 2
    
    //EJERCICIO 3
    public int areaBST() {

        if (root == null) {
            return 0;
        }

        int hojas = countLeafNodesIterative();

        int altura = calculateHeight(root);

        return hojas * altura;
    }
    private int countLeafNodesIterative() {

        if (root == null) {
            return 0;
        }

        java.util.Stack<Node> stack =
                new java.util.Stack<>();

        stack.push(root);

        int count = 0;

        while (!stack.isEmpty()) {

            Node current = stack.pop();

            // es hoja
            if (current.left == null &&
                current.right == null) {

                count++;
            }

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return count;
    }
    public void drawBST() {
        drawBST(root, 0);
    }
	    private void drawBST(Node node,int level) {
	
		if (node == null) {
		return;
		}
		
		drawBST(node.right, level + 1);
		
		for (int i = 0; i < level; i++) {
		System.out.print("    ");
		}
		
		System.out.println(node.data);
		
		drawBST(node.left, level + 1);
		}
    //EJERCICIO3
	    
	//EJERCICIO4
	    public void parenthesize() {
	        parenthesize(root, 0);
	    }
	    private void parenthesize(Node node,
                int level) {

		if (node == null) {
		return;
		}
		
		// sangría
		for (int i = 0; i < level; i++) {
		System.out.print("    ");
		}
		
		System.out.print(node.data);
		
		// si tiene hijos
		if (node.left != null ||
		node.right != null) {
		
		System.out.println(" (");
		
		parenthesize(node.left,
		           level + 1);
		
		parenthesize(node.right,
		           level + 1);
		
		// cerrar paréntesis
		for (int i = 0; i < level; i++) {
		  System.out.print("    ");
		}
		
		System.out.println(")");
		} else {
		
		System.out.println();
		}
	}
	 //EJERCICIO4
		    
}