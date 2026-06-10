package Actividades;


import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {

    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    private static int nextId = 1;
    protected int idNode;

    public BNode(int n) {
        keys   = new ArrayList<E>(n);
        childs = new ArrayList<BNode<E>>(n + 1);
        count  = 0;
        idNode = nextId++;

        for (int i = 0; i < n; i++)   keys.add(null);
        for (int i = 0; i <= n; i++)  childs.add(null);
    }

    public boolean nodeFull(int maxKeys) { return count == maxKeys; }
    public boolean nodeEmpty()           { return count == 0; }

    public boolean searchNode(E cl, int pos[]) {
        int i = 0;
        while (i < count && cl.compareTo(keys.get(i)) > 0) i++;
        pos[0] = i;
        return (i < count && cl.compareTo(keys.get(i)) == 0);
    }

    @Override
    public String toString() {
        String s = "Nodo " + idNode + ": ";
        for (int i = 0; i < count; i++) s += keys.get(i) + " ";
        return s;
    }

    /** Reset the static id counter (useful when recreating the tree in tests). */
    public static void resetIdCounter() { nextId = 1; }
}
