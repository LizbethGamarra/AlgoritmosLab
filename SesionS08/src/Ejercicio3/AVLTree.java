package Ejercicio3;

public class AVLTree {

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

        System.out.println(
                ">> Rotación Simple Derecha"
        );

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

        System.out.println(
                ">> Rotación Simple Izquierda"
        );

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

    public void insertar(int dato) {

        raiz = insertar(raiz, dato);
    }

    // ==========================================
    // NODO MINIMO (Sucesor)
    // ==========================================

    NodoAVL nodoMinimo(
            NodoAVL nodo) {

        NodoAVL actual = nodo;

        while (actual.izquierda != null)

            actual =
                    actual.izquierda;

        return actual;
    }

    // ==========================================
    // ELIMINAR AVL
    // ==========================================

    NodoAVL eliminar(
            NodoAVL nodo,
            int dato) {

        if (nodo == null)
            return nodo;

        // ======================================
        // BUSCAR NODO
        // ======================================

        if (dato < nodo.dato)

            nodo.izquierda =
                    eliminar(
                            nodo.izquierda,
                            dato);

        else if (dato > nodo.dato)

            nodo.derecha =
                    eliminar(
                            nodo.derecha,
                            dato);

        // ======================================
        // NODO ENCONTRADO
        // ======================================

        else {

            System.out.println(
                    "\nEliminando: " + dato
            );

            // ==================================
            // CASO 1:
            // NODO HOJA
            // ==================================

            if (nodo.izquierda == null
                    && nodo.derecha == null) {

                System.out.println(
                        "Caso BST: Nodo hoja"
                );

                nodo = null;
            }

            // ==================================
            // CASO 2:
            // NODO CON UN HIJO
            // ==================================

            else if (nodo.izquierda == null
                    || nodo.derecha == null) {

                System.out.println(
                        "Caso BST: Nodo con un hijo"
                );

                NodoAVL temp;

                if (nodo.izquierda != null)

                    temp = nodo.izquierda;

                else

                    temp = nodo.derecha;

                nodo = temp;
            }

            // ==================================
            // CASO 3:
            // NODO CON DOS HIJOS
            // ==================================

            else {

                System.out.println(
                        "Caso BST: Nodo con dos hijos"
                );

                NodoAVL sucesor =
                        nodoMinimo(
                                nodo.derecha);

                System.out.println(
                        "Sucesor Inorden: "
                                + sucesor.dato
                );

                nodo.dato =
                        sucesor.dato;

                nodo.derecha =
                        eliminar(
                                nodo.derecha,
                                sucesor.dato);
            }
        }

        // ======================================
        // SI EL ARBOL QUEDA VACIO
        // ======================================

        if (nodo == null)
            return nodo;

        // ======================================
        // ACTUALIZAR ALTURA
        // ======================================

        nodo.altura =
                1 + max(
                        altura(nodo.izquierda),
                        altura(nodo.derecha)
                );

        // ======================================
        // FACTOR BALANCE
        // ======================================

        int fb = balance(nodo);

        System.out.println(
                "Nodo: "
                        + nodo.dato
                        + " | FB = "
                        + fb
        );

        // ======================================
        // CASO II
        // ======================================

        if (fb < -1
                && balance(
                nodo.izquierda) <= 0) {

            System.out.println(
                    "Desbalance: II"
            );

            return rotacionDerecha(nodo);
        }

        // ======================================
        // CASO ID
        // ======================================

        if (fb < -1
                && balance(
                nodo.izquierda) > 0) {

            System.out.println(
                    "Desbalance: ID"
            );

            nodo.izquierda =
                    rotacionIzquierda(
                            nodo.izquierda);

            return rotacionDerecha(nodo);
        }

        // ======================================
        // CASO DD
        // ======================================

        if (fb > 1
                && balance(
                nodo.derecha) >= 0) {

            System.out.println(
                    "Desbalance: DD"
            );

            return rotacionIzquierda(nodo);
        }

        // ======================================
        // CASO DI
        // ======================================

        if (fb > 1
                && balance(
                nodo.derecha) < 0) {

            System.out.println(
                    "Desbalance: DI"
            );

            nodo.derecha =
                    rotacionDerecha(
                            nodo.derecha);

            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    // ==========================================
    // ELIMINAR PUBLICO
    // ==========================================

    public void eliminar(int dato) {

        raiz = eliminar(raiz, dato);

        System.out.println(
                "\nÁrbol después de eliminar:"
        );

        mostrarArbol();

        System.out.println(
                "================================"
        );
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