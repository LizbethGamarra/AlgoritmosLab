package Ejemplo2;

public class AVLTurnos {

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
    // INSERTAR TURNO
    // ==========================================

    NodoAVL insertar(
            NodoAVL nodo,
            int turno) {

        if (nodo == null) {

            System.out.println(
                    "Turno agregado: "
                            + turno
            );

            return new NodoAVL(turno);
        }

        if (turno < nodo.turno)

            nodo.izquierda =
                    insertar(
                            nodo.izquierda,
                            turno);

        else if (turno > nodo.turno)

            nodo.derecha =
                    insertar(
                            nodo.derecha,
                            turno);

        else {

            System.out.println(
                    "El turno ya existe."
            );

            return nodo;
        }

        nodo.altura =
                1 + max(
                        altura(nodo.izquierda),
                        altura(nodo.derecha)
                );

        int fb = balance(nodo);

        // CASO II
        if (fb < -1
                && turno < nodo.izquierda.turno)

            return rotacionDerecha(nodo);

        // CASO DD
        if (fb > 1
                && turno > nodo.derecha.turno)

            return rotacionIzquierda(nodo);

        // CASO ID
        if (fb < -1
                && turno > nodo.izquierda.turno) {

            nodo.izquierda =
                    rotacionIzquierda(
                            nodo.izquierda);

            return rotacionDerecha(nodo);
        }

        // CASO DI
        if (fb > 1
                && turno < nodo.derecha.turno) {

            nodo.derecha =
                    rotacionDerecha(
                            nodo.derecha);

            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void insertar(int turno) {

        raiz = insertar(raiz, turno);
    }

    // ==========================================
    // BUSCAR TURNO
    // ==========================================

    public boolean buscar(int turno) {

        return buscarRecursivo(
                raiz,
                turno
        );
    }

    boolean buscarRecursivo(
            NodoAVL nodo,
            int turno) {

        if (nodo == null)
            return false;

        if (turno == nodo.turno)
            return true;

        if (turno < nodo.turno)

            return buscarRecursivo(
                    nodo.izquierda,
                    turno);

        return buscarRecursivo(
                nodo.derecha,
                turno);
    }

    // ==========================================
    // NODO MINIMO
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
    // ELIMINAR TURNO
    // ==========================================

    NodoAVL eliminar(
            NodoAVL nodo,
            int turno) {

        if (nodo == null)
            return nodo;

        if (turno < nodo.turno)

            nodo.izquierda =
                    eliminar(
                            nodo.izquierda,
                            turno);

        else if (turno > nodo.turno)

            nodo.derecha =
                    eliminar(
                            nodo.derecha,
                            turno);

        else {

            System.out.println(
                    "Turno atendido y eliminado: "
                            + turno
            );

            // NODO HOJA
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

                nodo.turno =
                        sucesor.turno;

                nodo.derecha =
                        eliminar(
                                nodo.derecha,
                                sucesor.turno);
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

        // II
        if (fb < -1
                && balance(
                nodo.izquierda) <= 0)

            return rotacionDerecha(nodo);

        // ID
        if (fb < -1
                && balance(
                nodo.izquierda) > 0) {

            nodo.izquierda =
                    rotacionIzquierda(
                            nodo.izquierda);

            return rotacionDerecha(nodo);
        }

        // DD
        if (fb > 1
                && balance(
                nodo.derecha) >= 0)

            return rotacionIzquierda(nodo);

        // DI
        if (fb > 1
                && balance(
                nodo.derecha) < 0) {

            nodo.derecha =
                    rotacionDerecha(
                            nodo.derecha);

            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void eliminar(int turno) {

        raiz = eliminar(raiz, turno);
    }

    // ==========================================
    // RECORRIDO INORDEN
    // ==========================================

    void inorden(NodoAVL nodo) {

        if (nodo != null) {

            inorden(nodo.izquierda);

            System.out.print(
                    nodo.turno + " "
            );

            inorden(nodo.derecha);
        }
    }

    public void mostrarTurnos() {

        System.out.println(
                "\nTurnos registrados:"
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
                            + nodo.turno
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