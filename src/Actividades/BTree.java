package Actividades;

public class BTree<E extends Comparable<E>> {

    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    private boolean deficiente;

    public BTree(int orden) {
        this.orden = orden;
        this.root  = null;
    }

    public boolean isEmpty() { return root == null; }

    
    /** Returns the root node so the GUI can traverse the tree for painting. */
    public BNode<E> getRoot() { return root; }

    /** Returns the order of this B-Tree (needed by GUI to know max keys). */
    public int getOrden() { return orden; }


    // INSERT 

    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(root, cl);
        if (up) {
            pnew = new BNode<E>(orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, root);
            pnew.childs.set(1, nDes);
            root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int pos[] = new int[1];
        E mediana;
        if (current == null) { up = true; nDes = null; return cl; }
        boolean fl = current.searchNode(cl, pos);
        if (fl) { System.out.println("Item duplicado"); up = false; return null; }
        mediana = push(current.childs.get(pos[0]), cl);
        if (up) {
            if (current.nodeFull(orden - 1))
                mediana = dividedNode(current, mediana, pos[0]);
            else { putNode(current, mediana, nDes, pos[0]); up = false; }
        }
        return mediana;
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int posMdna = (k <= orden / 2) ? orden / 2 : orden / 2 + 1;
        nDes = new BNode<E>(orden);
        for (int i = posMdna; i < orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count    = (orden - 1) - posMdna;
        current.count = posMdna;
        if (k <= orden / 2) putNode(current, cl, rd, k);
        else                 putNode(nDes, cl, rd, k - posMdna);
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        up = true;
        return median;
    }

    //  SEARCH  

    public boolean search(E cl) { return search(root, cl); }

    private boolean search(BNode<E> current, E cl) {
        if (current == null) return false;
        int pos[] = new int[1];
        boolean found = current.searchNode(cl, pos);
        if (found) {
            System.out.println(cl + " se encuentra en el nodo "
                    + current.idNode + " en la posicion " + pos[0]);
            return true;
        }
        return search(current.childs.get(pos[0]), cl);
    }

    /**
     * Read-only search that returns the node where the key was found,
     * or null if not present.  Used exclusively by the GUI for highlighting.
     */
    public BNode<E> searchNode(E cl) { return searchNode(root, cl); }

    private BNode<E> searchNode(BNode<E> current, E cl) {
        if (current == null) return null;
        int pos[] = new int[1];
        if (current.searchNode(cl, pos)) return current;
        return searchNode(current.childs.get(pos[0]), cl);
    }

    //  SEARCH RANGE  

    public void searchRange(E min, E max) {
        if (min.compareTo(max) > 0) { System.out.println("Rango invalido"); return; }
        searchRange(root, min, max);
        System.out.println();
    }

    private void searchRange(BNode<E> current, E min, E max) {
        if (current == null) return;
        int i;
        for (i = 0; i < current.count; i++) {
            if (current.keys.get(i).compareTo(min) > 0)
                searchRange(current.childs.get(i), min, max);
            if (current.keys.get(i).compareTo(min) >= 0 &&
                current.keys.get(i).compareTo(max) <= 0)
                System.out.print(current.keys.get(i) + " ");
            if (current.keys.get(i).compareTo(max) > 0) return;
        }
        searchRange(current.childs.get(i), min, max);
    }

    //  REMOVE 

    public void remove(E cl) {
        if (isEmpty()) { System.out.println("El árbol está vacío."); return; }
        deficiente = false;
        eliminar(root, cl);
        if (root.count == 0) root = root.childs.get(0);
    }

    private int minimoClaves() { return (int) Math.ceil(orden / 2.0) - 1; }

    private boolean esHoja(BNode<E> nodo) { return nodo.childs.get(0) == null; }

    private void eliminar(BNode<E> current, E cl) {
        if (current == null) {
            System.out.println("Clave " + cl + " no encontrada.");
            deficiente = false;
            return;
        }
        int pos[] = new int[1];
        boolean encontrado = current.searchNode(cl, pos);
        if (encontrado) {
            if (esHoja(current)) {
                eliminarDePosicion(current, pos[0]);
                deficiente = current.count < minimoClaves();
            } else {
                E sucesor = obtenerSucesor(current, pos[0]);
                current.keys.set(pos[0], sucesor);
                eliminar(current.childs.get(pos[0] + 1), sucesor);
                if (deficiente) verificarMinimo(current, pos[0] + 1);
            }
        } else {
            int hijoIdx = pos[0];
            eliminar(current.childs.get(hijoIdx), cl);
            if (deficiente) verificarMinimo(current, hijoIdx);
        }
    }

    private void eliminarDePosicion(BNode<E> nodo, int pos) {
        for (int i = pos; i < nodo.count - 1; i++) {
            nodo.keys.set(i, nodo.keys.get(i + 1));
            nodo.childs.set(i + 1, nodo.childs.get(i + 2));
        }
        nodo.keys.set(nodo.count - 1, null);
        nodo.childs.set(nodo.count, null);
        nodo.count--;
    }

    private E obtenerSucesor(BNode<E> nodo, int posicion) {
        BNode<E> actual = nodo.childs.get(posicion + 1);
        while (!esHoja(actual)) actual = actual.childs.get(0);
        return actual.keys.get(0);
    }

    private E obtenerAntecesor(BNode<E> nodo, int posicion) {
        BNode<E> actual = nodo.childs.get(posicion);
        while (!esHoja(actual)) actual = actual.childs.get(actual.count);
        return actual.keys.get(actual.count - 1);
    }

    private void verificarMinimo(BNode<E> padre, int hijoIdx) {
        BNode<E> hijoDeficiente = padre.childs.get(hijoIdx);
        if (hijoIdx > 0) {
            BNode<E> hermanoIzq = padre.childs.get(hijoIdx - 1);
            if (hermanoIzq.count > minimoClaves()) {
                redistribuirIzquierda(padre, hijoIdx);
                deficiente = false;
                return;
            }
        }
        if (hijoIdx < padre.count) {
            BNode<E> hermanoDer = padre.childs.get(hijoIdx + 1);
            if (hermanoDer.count > minimoClaves()) {
                redistribuirDerecha(padre, hijoIdx);
                deficiente = false;
                return;
            }
        }
        if (hijoIdx > 0) fusionar(padre, hijoIdx - 1);
        else             fusionar(padre, hijoIdx);
        deficiente = padre.count < minimoClaves();
    }

    private void redistribuirIzquierda(BNode<E> padre, int hijoIdx) {
        BNode<E> hijo       = padre.childs.get(hijoIdx);
        BNode<E> hermanoIzq = padre.childs.get(hijoIdx - 1);
        hijo.childs.set(hijo.count + 1, hijo.childs.get(hijo.count));
        for (int i = hijo.count - 1; i >= 0; i--) {
            hijo.keys.set(i + 1, hijo.keys.get(i));
            hijo.childs.set(i + 1, hijo.childs.get(i));
        }
        hijo.childs.set(0, null);
        hijo.keys.set(0, padre.keys.get(hijoIdx - 1));
        hijo.childs.set(0, hermanoIzq.childs.get(hermanoIzq.count));
        hijo.count++;
        padre.keys.set(hijoIdx - 1, hermanoIzq.keys.get(hermanoIzq.count - 1));
        hermanoIzq.keys.set(hermanoIzq.count - 1, null);
        hermanoIzq.childs.set(hermanoIzq.count, null);
        hermanoIzq.count--;
    }

    private void redistribuirDerecha(BNode<E> padre, int hijoIdx) {
        BNode<E> hijo      = padre.childs.get(hijoIdx);
        BNode<E> hermanoDer = padre.childs.get(hijoIdx + 1);
        hijo.keys.set(hijo.count, padre.keys.get(hijoIdx));
        hijo.childs.set(hijo.count + 1, hermanoDer.childs.get(0));
        hijo.count++;
        padre.keys.set(hijoIdx, hermanoDer.keys.get(0));
        hermanoDer.childs.set(0, hermanoDer.childs.get(1));
        for (int i = 0; i < hermanoDer.count - 1; i++) {
            hermanoDer.keys.set(i, hermanoDer.keys.get(i + 1));
            hermanoDer.childs.set(i + 1, hermanoDer.childs.get(i + 2));
        }
        hermanoDer.keys.set(hermanoDer.count - 1, null);
        hermanoDer.childs.set(hermanoDer.count, null);
        hermanoDer.count--;
    }

    private void fusionar(BNode<E> padre, int sepIdx) {
        BNode<E> hijoIzq = padre.childs.get(sepIdx);
        BNode<E> hijoDer  = padre.childs.get(sepIdx + 1);
        hijoIzq.keys.set(hijoIzq.count, padre.keys.get(sepIdx));
        hijoIzq.childs.set(hijoIzq.count + 1, hijoDer.childs.get(0));
        hijoIzq.count++;
        for (int i = 0; i < hijoDer.count; i++) {
            hijoIzq.keys.set(hijoIzq.count, hijoDer.keys.get(i));
            hijoIzq.childs.set(hijoIzq.count + 1, hijoDer.childs.get(i + 1));
            hijoIzq.count++;
        }
        for (int i = sepIdx; i < padre.count - 1; i++) {
            padre.keys.set(i, padre.keys.get(i + 1));
            padre.childs.set(i + 1, padre.childs.get(i + 2));
        }
        padre.keys.set(padre.count - 1, null);
        padre.childs.set(padre.count, null);
        padre.count--;
    }

    //  TO STRING  

    @Override
    public String toString() {
        return isEmpty() ? "BTree is empty..." : writeTree(root);
    }

    private String writeTree(BNode<E> current) {
        if (current == null) return "";
        String s = current.toString() + "\n";
        for (int i = 0; i <= current.count; i++) s += writeTree(current.childs.get(i));
        return s;
    }

    //   EXTRA METHODS  

    public void inOrder() { inOrder(root); }
    private void inOrder(BNode<E> current) {
        if (current == null) return;
        int i;
        for (i = 0; i < current.count; i++) { inOrder(current.childs.get(i)); System.out.println(current.keys.get(i)); }
        inOrder(current.childs.get(i));
    }

    public int height()  { return height(root); }
    private int height(BNode<E> current) {
        if (current == null) return 0;
        if (current.childs.get(0) == null) return 1;
        return 1 + height(current.childs.get(0));
    }

    public int countKeys() { return countKeys(root); }
    private int countKeys(BNode<E> current) {
        if (current == null) return 0;
        int total = current.count;
        for (int i = 0; i <= current.count; i++) total += countKeys(current.childs.get(i));
        return total;
    }
}
