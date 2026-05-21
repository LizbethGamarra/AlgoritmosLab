package Ejercicio2;

public class ArbolAVL {

    NodoAVL raiz;

    // ==========================================
    // ALTURA
    // ==========================================

    int altura(NodoAVL n) {

        if (n == null)
            return 0;

        return n.altura;
    }

    // ==========================================
    // MAXIMO
    // ==========================================

    int max(int a, int b) {

        return (a > b) ? a : b;
    }

    // ==========================================
    // FACTOR BALANCE
    // ==========================================

    int balance(NodoAVL n) {

        if (n == null)
            return 0;

        return altura(n.derecha)
                - altura(n.izquierda);
    }

    // ==========================================
    // ROTACION DERECHA
    // ==========================================

    NodoAVL rotacionDerecha(
            NodoAVL y) {

        NodoAVL x = y.izquierda;

        NodoAVL t2 = x.derecha;

        x.derecha = y;

        y.izquierda = t2;

        y.altura =
                max(
                        altura(y.izquierda),
                        altura(y.derecha)
                ) + 1;

        x.altura =
                max(
                        altura(x.izquierda),
                        altura(x.derecha)
                ) + 1;

        return x;
    }

    // ==========================================
    // ROTACION IZQUIERDA
    // ==========================================

    NodoAVL rotacionIzquierda(
            NodoAVL x) {

        NodoAVL y = x.derecha;

        NodoAVL t2 = y.izquierda;

        y.izquierda = x;

        x.derecha = t2;

        x.altura =
                max(
                        altura(x.izquierda),
                        altura(x.derecha)
                ) + 1;

        y.altura =
                max(
                        altura(y.izquierda),
                        altura(y.derecha)
                ) + 1;

        return y;
    }

    // ==========================================
    // INSERTAR
    // ==========================================

    NodoAVL insertar(NodoAVL nodo,
                      int dato) {

        if (nodo == null)
            return new NodoAVL(dato);

        if (dato < nodo.dato)

            nodo.izquierda =
                    insertar(
                            nodo.izquierda,
                            dato);

        else if (dato > nodo.dato)

            nodo.derecha =
                    insertar(
                            nodo.derecha,
                            dato);

        else
            return nodo;

        nodo.altura =
                1 + max(
                        altura(nodo.izquierda),
                        altura(nodo.derecha)
                );

        int fb = balance(nodo);

        // II
        if (fb < -1
                && dato < nodo.izquierda.dato)

            return rotacionDerecha(nodo);

        // DD
        if (fb > 1
                && dato > nodo.derecha.dato)

            return rotacionIzquierda(nodo);

        // ID
        if (fb < -1
                && dato > nodo.izquierda.dato) {

            nodo.izquierda =
                    rotacionIzquierda(
                            nodo.izquierda);

            return rotacionDerecha(nodo);
        }

        // DI
        if (fb > 1
                && dato < nodo.derecha.dato) {

            nodo.derecha =
                    rotacionDerecha(
                            nodo.derecha);

            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void insertar(int dato) {

        raiz = insertar(raiz, dato);
    }

    // ==========================================
    // BUSCAR
    // ==========================================

    boolean buscar(NodoAVL nodo,
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
    // ALTURA
    // ==========================================

    public int altura() {

        return altura(raiz);
    }

    // ==========================================
    // INORDEN
    // ==========================================

    void inorden(NodoAVL nodo) {

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