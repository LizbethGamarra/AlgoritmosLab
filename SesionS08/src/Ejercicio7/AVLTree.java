package Ejercicio7;

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
    // ROTACION SIMPLE DERECHA
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
    // ROTACION SIMPLE IZQUIERDA
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
    // INSERTAR AVL
    // ==========================================

    NodoAVL insertar(
            NodoAVL nodo,
            int dato) {

        if (nodo == null) {

            System.out.println(
                    "\nInsertando: " + dato
            );

            return new NodoAVL(dato);
        }

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
                && dato < nodo.izquierda.dato) {

            System.out.println(
                    "Caso II"
            );

            return rotacionDerecha(nodo);
        }

        // ======================================
        // CASO DD
        // ======================================

        if (fb > 1
                && dato > nodo.derecha.dato) {

            System.out.println(
                    "Caso DD"
            );

            return rotacionIzquierda(nodo);
        }

        // ======================================
        // CASO ID
        // ======================================

        if (fb < -1
                && dato > nodo.izquierda.dato) {

            System.out.println(
                    "Caso ID"
            );

            nodo.izquierda =
                    rotacionIzquierda(
                            nodo.izquierda);

            return rotacionDerecha(nodo);
        }

        // ======================================
        // CASO DI
        // ======================================

        if (fb > 1
                && dato < nodo.derecha.dato) {

            System.out.println(
                    "Caso DI"
            );

            nodo.derecha =
                    rotacionDerecha(
                            nodo.derecha);

            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void insertar(int dato) {

        raiz = insertar(raiz, dato);

        System.out.println(
                "\nÁrbol después de insertar:"
        );

        mostrarArbol();

        System.out.println(
                "================================="
        );
    }

    // ==========================================
    // NODO MINIMO
    // ==========================================

    NodoAVL nodoMinimo(
            NodoAVL nodo) {

        NodoAVL actual = nodo;

        while (actual.izquierda != null)

            actual = actual.izquierda;

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

        else {

            System.out.println(
                    "\nEliminando: " + dato
            );

            // HOJA
            if (nodo.izquierda == null
                    && nodo.derecha == null) {

                nodo = null;
            }

            // UN HIJO
            else if (nodo.izquierda == null
                    || nodo.derecha == null) {

                NodoAVL temp;

                if (nodo.izquierda != null)

                    temp = nodo.izquierda;

                else

                    temp = nodo.derecha;

                nodo = temp;
            }

            // DOS HIJOS
            else {

                NodoAVL sucesor =
                        nodoMinimo(
                                nodo.derecha);

                nodo.dato =
                        sucesor.dato;

                nodo.derecha =
                        eliminar(
                                nodo.derecha,
                                sucesor.dato);
            }
        }

        if (nodo == null)
            return nodo;

        nodo.altura =
                1 + max(
                        altura(nodo.izquierda),
                        altura(nodo.derecha)
                );

        int fb = balance(nodo);

        System.out.println(
                "Nodo: "
                        + nodo.dato
                        + " | FB = "
                        + fb
        );

        // II
        if (fb < -1
                && balance(
                nodo.izquierda) <= 0) {

            System.out.println(
                    "Rotación II"
            );

            return rotacionDerecha(nodo);
        }

        // ID
        if (fb < -1
                && balance(
                nodo.izquierda) > 0) {

            System.out.println(
                    "Rotación ID"
            );

            nodo.izquierda =
                    rotacionIzquierda(
                            nodo.izquierda);

            return rotacionDerecha(nodo);
        }

        // DD
        if (fb > 1
                && balance(
                nodo.derecha) >= 0) {

            System.out.println(
                    "Rotación DD"
            );

            return rotacionIzquierda(nodo);
        }

        // DI
        if (fb > 1
                && balance(
                nodo.derecha) < 0) {

            System.out.println(
                    "Rotación DI"
            );

            nodo.derecha =
                    rotacionDerecha(
                            nodo.derecha);

            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void eliminar(int dato) {

        raiz = eliminar(raiz, dato);

        System.out.println(
                "\nÁrbol después de eliminar:"
        );

        mostrarArbol();

        System.out.println(
                "================================="
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