package Ejemplo1;

public class AVLProductos {

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
    // FACTOR DE BALANCE
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
    // INSERTAR PRODUCTO
    // ==========================================

    NodoAVL insertar(
            NodoAVL nodo,
            int codigo) {

        if (nodo == null) {

            System.out.println(
                    "Producto insertado: "
                            + codigo
            );

            return new NodoAVL(codigo);
        }

        if (codigo < nodo.codigo)

            nodo.izquierda =
                    insertar(
                            nodo.izquierda,
                            codigo);

        else if (codigo > nodo.codigo)

            nodo.derecha =
                    insertar(
                            nodo.derecha,
                            codigo);

        else {

            System.out.println(
                    "El producto ya existe."
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
                && codigo < nodo.izquierda.codigo)

            return rotacionDerecha(nodo);

        // CASO DD
        if (fb > 1
                && codigo > nodo.derecha.codigo)

            return rotacionIzquierda(nodo);

        // CASO ID
        if (fb < -1
                && codigo > nodo.izquierda.codigo) {

            nodo.izquierda =
                    rotacionIzquierda(
                            nodo.izquierda);

            return rotacionDerecha(nodo);
        }

        // CASO DI
        if (fb > 1
                && codigo < nodo.derecha.codigo) {

            nodo.derecha =
                    rotacionDerecha(
                            nodo.derecha);

            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void insertar(int codigo) {

        raiz = insertar(raiz, codigo);
    }

    // ==========================================
    // BUSCAR PRODUCTO
    // ==========================================

    public boolean buscar(int codigo) {

        return buscarRecursivo(
                raiz,
                codigo
        );
    }

    boolean buscarRecursivo(
            NodoAVL nodo,
            int codigo) {

        if (nodo == null)
            return false;

        if (codigo == nodo.codigo)
            return true;

        if (codigo < nodo.codigo)

            return buscarRecursivo(
                    nodo.izquierda,
                    codigo);

        return buscarRecursivo(
                nodo.derecha,
                codigo);
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
    // ELIMINAR PRODUCTO
    // ==========================================

    NodoAVL eliminar(
            NodoAVL nodo,
            int codigo) {

        if (nodo == null)
            return nodo;

        if (codigo < nodo.codigo)

            nodo.izquierda =
                    eliminar(
                            nodo.izquierda,
                            codigo);

        else if (codigo > nodo.codigo)

            nodo.derecha =
                    eliminar(
                            nodo.derecha,
                            codigo);

        else {

            System.out.println(
                    "Producto eliminado: "
                            + codigo
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

                nodo.codigo =
                        sucesor.codigo;

                nodo.derecha =
                        eliminar(
                                nodo.derecha,
                                sucesor.codigo);
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

    public void eliminar(int codigo) {

        raiz = eliminar(raiz, codigo);
    }

    // ==========================================
    // RECORRIDO INORDEN
    // ==========================================

    void inorden(NodoAVL nodo) {

        if (nodo != null) {

            inorden(nodo.izquierda);

            System.out.print(
                    nodo.codigo + " "
            );

            inorden(nodo.derecha);
        }
    }

    public void mostrarProductos() {

        System.out.println(
                "\nProductos en almacén:"
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
                            + nodo.codigo
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