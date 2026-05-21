package Ejercicio1;

public class ArbolAVL {

    Nodo raiz;

    // ==========================================
    // ALTURA
    // ==========================================

    int altura(Nodo n) {

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

    int factorBalance(Nodo n) {

        if (n == null)
            return 0;

        return altura(n.derecha)
                - altura(n.izquierda);
    }

    // ==========================================
    // ROTACION SIMPLE DERECHA
    // ==========================================

    Nodo rotacionDerecha(Nodo y) {

        System.out.println(
                ">> Rotación Simple Derecha"
        );

        Nodo x = y.izquierda;
        Nodo t2 = x.derecha;

        x.derecha = y;
        y.izquierda = t2;

        y.altura =
                max(altura(y.izquierda),
                        altura(y.derecha)) + 1;

        x.altura =
                max(altura(x.izquierda),
                        altura(x.derecha)) + 1;

        return x;
    }

    // ==========================================
    // ROTACION SIMPLE IZQUIERDA
    // ==========================================

    Nodo rotacionIzquierda(Nodo x) {

        System.out.println(
                ">> Rotación Simple Izquierda"
        );

        Nodo y = x.derecha;
        Nodo t2 = y.izquierda;

        y.izquierda = x;
        x.derecha = t2;

        x.altura =
                max(altura(x.izquierda),
                        altura(x.derecha)) + 1;

        y.altura =
                max(altura(y.izquierda),
                        altura(y.derecha)) + 1;

        return y;
    }

    // ==========================================
    // INSERTAR
    // ==========================================

    Nodo insertar(Nodo nodo, int ticket) {

        if (nodo == null) {

            System.out.println(
                    "Insertando ticket: " + ticket
            );

            return new Nodo(ticket);
        }

        if (ticket < nodo.ticket)

            nodo.izquierda =
                    insertar(nodo.izquierda,
                            ticket);

        else if (ticket > nodo.ticket)

            nodo.derecha =
                    insertar(nodo.derecha,
                            ticket);

        else
            return nodo;

        nodo.altura =
                1 + max(altura(nodo.izquierda),
                altura(nodo.derecha));

        int fb = factorBalance(nodo);

        System.out.println(
                "Nodo: " + nodo.ticket +
                        " | FB = " + fb
        );

        // II
        if (fb < -1
                && ticket < nodo.izquierda.ticket)

            return rotacionDerecha(nodo);

        // DD
        if (fb > 1
                && ticket > nodo.derecha.ticket)

            return rotacionIzquierda(nodo);

        // ID
        if (fb < -1
                && ticket > nodo.izquierda.ticket) {

            System.out.println(
                    ">> Rotación Doble Derecha"
            );

            nodo.izquierda =
                    rotacionIzquierda(
                            nodo.izquierda);

            return rotacionDerecha(nodo);
        }

        // DI
        if (fb > 1
                && ticket < nodo.derecha.ticket) {

            System.out.println(
                    ">> Rotación Doble Izquierda"
            );

            nodo.derecha =
                    rotacionDerecha(
                            nodo.derecha);

            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void insertar(int ticket) {

        raiz = insertar(raiz, ticket);

        System.out.println(
                "\nÁrbol después de insertar:"
        );

        mostrarArbol();

        System.out.println(
                "================================="
        );
    }

    // ==========================================
    // BUSCAR
    // ==========================================

    boolean buscar(Nodo nodo, int ticket) {

        if (nodo == null)
            return false;

        if (ticket == nodo.ticket)
            return true;

        if (ticket < nodo.ticket)

            return buscar(
                    nodo.izquierda,
                    ticket
            );

        return buscar(
                nodo.derecha,
                ticket
        );
    }

    public void buscar(int ticket) {

        System.out.println(
                "\nBuscando ticket: " + ticket
        );

        if (buscar(raiz, ticket))

            System.out.println(
                    "Ticket encontrado"
            );

        else

            System.out.println(
                    "Ticket NO encontrado"
            );
    }

    // ==========================================
    // NODO MINIMO
    // ==========================================

    Nodo nodoMinimo(Nodo nodo) {

        Nodo actual = nodo;

        while (actual.izquierda != null)

            actual = actual.izquierda;

        return actual;
    }

    // ==========================================
    // ELIMINAR
    // ==========================================

    Nodo eliminar(Nodo nodo, int ticket) {

        if (nodo == null)
            return nodo;

        if (ticket < nodo.ticket)

            nodo.izquierda =
                    eliminar(
                            nodo.izquierda,
                            ticket);

        else if (ticket > nodo.ticket)

            nodo.derecha =
                    eliminar(
                            nodo.derecha,
                            ticket);

        else {

            System.out.println(
                    "Eliminando ticket: "
                            + ticket
            );

            if ((nodo.izquierda == null)
                    || (nodo.derecha == null)) {

                Nodo temp;

                if (nodo.izquierda != null)

                    temp = nodo.izquierda;

                else

                    temp = nodo.derecha;

                if (temp == null) {

                    temp = nodo;
                    nodo = null;
                }

                else

                    nodo = temp;
            }

            else {

                Nodo temp =
                        nodoMinimo(
                                nodo.derecha);

                nodo.ticket =
                        temp.ticket;

                nodo.derecha =
                        eliminar(
                                nodo.derecha,
                                temp.ticket);
            }
        }

        if (nodo == null)
            return nodo;

        nodo.altura =
                max(altura(nodo.izquierda),
                        altura(nodo.derecha)) + 1;

        int fb = factorBalance(nodo);

        // II
        if (fb < -1
                && factorBalance(
                nodo.izquierda) <= 0)

            return rotacionDerecha(nodo);

        // ID
        if (fb < -1
                && factorBalance(
                nodo.izquierda) > 0) {

            nodo.izquierda =
                    rotacionIzquierda(
                            nodo.izquierda);

            return rotacionDerecha(nodo);
        }

        // DD
        if (fb > 1
                && factorBalance(
                nodo.derecha) >= 0)

            return rotacionIzquierda(nodo);

        // DI
        if (fb > 1
                && factorBalance(
                nodo.derecha) < 0) {

            nodo.derecha =
                    rotacionDerecha(
                            nodo.derecha);

            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void eliminar(int ticket) {

        raiz = eliminar(raiz, ticket);

        System.out.println(
                "\nÁrbol después de eliminar:"
        );

        mostrarArbol();

        System.out.println(
                "================================="
        );
    }

    // ==========================================
    // INORDEN
    // ==========================================

    void inorden(Nodo nodo) {

        if (nodo != null) {

            inorden(nodo.izquierda);

            System.out.print(
                    nodo.ticket + " "
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

    void mostrar(Nodo nodo,
                 String espacio,
                 boolean raiz) {

        if (nodo != null) {

            System.out.println(
                    espacio +
                            (raiz ? "└── " : "├── ")
                            + nodo.ticket
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

        mostrar(raiz,
                "",
                true);
    }
}