package Ejercicio5;

public class AVLTree {

    NodoAVL raiz;

    // ==========================================
    // ALTURA
    // ==========================================

    int altura(NodoAVL nodo) {

        if (nodo == null)
            return 0;

        return nodo.altura;
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

    int balance(NodoAVL nodo) {

        if (nodo == null)
            return 0;

        return altura(nodo.derecha)
                - altura(nodo.izquierda);
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
    // INSERTAR AVL
    // ==========================================

    NodoAVL insertar(
            NodoAVL nodo,
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

    // ==========================================
    // INSERT PUBLICO
    // ==========================================

    public void insertar(int dato) {

        raiz = insertar(raiz, dato);
    }

    // ==========================================
    // ALTURA TOTAL DEL ARBOL
    // ==========================================

    public int alturaArbol() {

        return altura(raiz);
    }

    // ==========================================
    // BFS RECURSIVO
    // ==========================================

    public void recorridoAmplitud() {

        int h = alturaArbol();

        System.out.println(
                "\nRecorrido por amplitud:"
        );

        for (int i = 1; i <= h; i++) {

            imprimirNivel(
                    raiz,
                    i
            );
        }

        System.out.println();
    }

    // ==========================================
    // IMPRIMIR NIVEL
    // ==========================================

    void imprimirNivel(
            NodoAVL nodo,
            int nivel) {

        if (nodo == null)
            return;

        // NIVEL ACTUAL
        if (nivel == 1)

            System.out.print(
                    nodo.dato + ", "
            );

        // BAJAR NIVELES
        else if (nivel > 1) {

            imprimirNivel(
                    nodo.izquierda,
                    nivel - 1
            );

            imprimirNivel(
                    nodo.derecha,
                    nivel - 1
            );
        }
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

        System.out.println(
                "\nRecorrido Inorden:"
        );

        inorden(raiz);

        System.out.println();
    }

    // ==========================================
    // MOSTRAR ARBOL
    // ==========================================

    void mostrar(
            NodoAVL nodo,
            String espacio,
            boolean raiz) {

        if (nodo != null) {

            System.out.println(
                    espacio
                            + (raiz ? "└── " : "├── ")
                            + nodo.dato
            );

            mostrar(
                    nodo.izquierda,
                    espacio + "    ",
                    false
            );

            mostrar(
                    nodo.derecha,
                    espacio + "    ",
                    false
            );
        }
    }

    public void mostrarArbol() {

        mostrar(
                raiz,
                "",
                true
        );
    }
}