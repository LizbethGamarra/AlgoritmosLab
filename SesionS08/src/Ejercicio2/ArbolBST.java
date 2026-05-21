package Ejercicio2;

public class ArbolBST {

    NodoBST raiz;

    // ==========================================
    // INSERTAR
    // ==========================================

    NodoBST insertar(NodoBST nodo, int dato) {

        if (nodo == null)
            return new NodoBST(dato);

        if (dato < nodo.dato)

            nodo.izquierda =
                    insertar(nodo.izquierda,
                            dato);

        else if (dato > nodo.dato)

            nodo.derecha =
                    insertar(nodo.derecha,
                            dato);

        return nodo;
    }

    public void insertar(int dato) {

        raiz = insertar(raiz, dato);
    }

    // ==========================================
    // ALTURA
    // ==========================================

    int altura(NodoBST nodo) {

        if (nodo == null)
            return 0;

        int izq =
                altura(nodo.izquierda);

        int der =
                altura(nodo.derecha);

        return Math.max(izq, der) + 1;
    }

    public int altura() {

        return altura(raiz);
    }

    // ==========================================
    // BUSCAR
    // ==========================================

    boolean buscar(NodoBST nodo,
                   int dato) {

        if (nodo == null)
            return false;

        if (dato == nodo.dato)
            return true;

        if (dato < nodo.dato)

            return buscar(
                    nodo.izquierda,
                    dato);

        return buscar(
                nodo.derecha,
                dato);
    }

    public boolean buscar(int dato) {

        return buscar(raiz, dato);
    }

    // ==========================================
    // INORDEN
    // ==========================================

    void inorden(NodoBST nodo) {

        if (nodo != null) {

            inorden(nodo.izquierda);

            System.out.print(
                    nodo.dato + " "
            );

            inorden(nodo.derecha);
        }
    }

    public void inorden() {

        inorden(raiz);

        System.out.println();
    }
}