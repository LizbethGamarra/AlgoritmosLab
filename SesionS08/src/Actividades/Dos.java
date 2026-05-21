package Actividades;

class Nodo {
    int dato;
    int altura;

    Nodo izq;
    Nodo der;

    Nodo(int dato) {
        this.dato = dato;
        altura = 1;
    }
}

class AVL {

    Nodo raiz;

    // Altura
    int altura(Nodo n) {
        if (n == null)
            return 0;

        return n.altura;
    }

    // Máximo
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Balance
    int balance(Nodo n) {

        if (n == null)
            return 0;

        return altura(n.der) - altura(n.izq);
    }

    // Rotación izquierda
    Nodo rotarIzquierda(Nodo x) {

        Nodo y = x.der;
        Nodo temp = y.izq;

        y.izq = x;
        x.der = temp;

        x.altura = max(altura(x.izq), altura(x.der)) + 1;
        y.altura = max(altura(y.izq), altura(y.der)) + 1;

        return y;
    }

    // Rotación derecha
    Nodo rotarDerecha(Nodo y) {

        Nodo x = y.izq;
        Nodo temp = x.der;

        x.der = y;
        y.izq = temp;

        y.altura = max(altura(y.izq), altura(y.der)) + 1;
        x.altura = max(altura(x.izq), altura(x.der)) + 1;

        return x;
    }

    // Insertar
    Nodo insertar(Nodo nodo, int dato) {

        if (nodo == null)
            return new Nodo(dato);

        if (dato < nodo.dato)
            nodo.izq = insertar(nodo.izq, dato);

        else if (dato > nodo.dato)
            nodo.der = insertar(nodo.der, dato);

        else
            return nodo;

        nodo.altura = 1 + max(altura(nodo.izq), altura(nodo.der));

        int bf = balance(nodo);

        // II
        if (bf < -1 && dato < nodo.izq.dato)
            return rotarDerecha(nodo);

        // DD
        if (bf > 1 && dato > nodo.der.dato)
            return rotarIzquierda(nodo);

        // ID
        if (bf < -1 && dato > nodo.izq.dato) {

            nodo.izq = rotarIzquierda(nodo.izq);

            return rotarDerecha(nodo);
        }

        // DI
        if (bf > 1 && dato < nodo.der.dato) {

            nodo.der = rotarDerecha(nodo.der);

            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    void insertar(int dato) {
        raiz = insertar(raiz, dato);
    }

    // Nodo mínimo
    Nodo nodoMinimo(Nodo nodo) {

        Nodo actual = nodo;

        while (actual.izq != null)
            actual = actual.izq;

        return actual;
    }

    // Eliminar
    Nodo eliminar(Nodo nodo, int dato) {

        if (nodo == null)
            return nodo;

        // Buscar nodo
        if (dato < nodo.dato)
            nodo.izq = eliminar(nodo.izq, dato);

        else if (dato > nodo.dato)
            nodo.der = eliminar(nodo.der, dato);

        else {

            System.out.println("\nEliminando: " + dato);

            // Caso 1 o Caso 2
            if ((nodo.izq == null) || (nodo.der == null)) {

                Nodo temp;

                if (nodo.izq != null)
                    temp = nodo.izq;
                else
                    temp = nodo.der;

                // Caso 1
                if (temp == null) {

                    System.out.println("Caso BST: Nodo hoja");

                    temp = nodo;
                    nodo = null;
                }

                // Caso 2
                else {

                    System.out.println("Caso BST: Nodo con un hijo");

                    nodo = temp;
                }
            }

            // Caso 3
            else {

                System.out.println("Caso BST: Nodo con dos hijos");

                Nodo temp = nodoMinimo(nodo.der);

                System.out.println("Sucesor inorden: " + temp.dato);

                nodo.dato = temp.dato;

                nodo.der = eliminar(nodo.der, temp.dato);
            }
        }

        if (nodo == null)
            return nodo;

        // Actualizar altura
        nodo.altura = 1 + max(altura(nodo.izq), altura(nodo.der));

        // Balance
        int bf = balance(nodo);

        // II
        if (bf < -1 && balance(nodo.izq) <= 0) {

            System.out.println("Rotación: Simple Derecha");

            return rotarDerecha(nodo);
        }

        // ID
        if (bf < -1 && balance(nodo.izq) > 0) {

            System.out.println("Rotación: Doble Derecha");

            nodo.izq = rotarIzquierda(nodo.izq);

            return rotarDerecha(nodo);
        }

        // DD
        if (bf > 1 && balance(nodo.der) >= 0) {

            System.out.println("Rotación: Simple Izquierda");

            return rotarIzquierda(nodo);
        }

        // DI
        if (bf > 1 && balance(nodo.der) < 0) {

            System.out.println("Rotación: Doble Izquierda");

            nodo.der = rotarDerecha(nodo.der);

            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    void eliminar(int dato) {
        raiz = eliminar(raiz, dato);
    }

    // Mostrar árbol
    void mostrarArbol(Nodo nodo, String espacio, boolean raiz) {

        if (nodo != null) {

            System.out.println(
                    espacio + (raiz ? "└── " : "├── ") + nodo.dato);

            mostrarArbol(nodo.izq, espacio + "    ", false);

            mostrarArbol(nodo.der, espacio + "    ", false);
        }
    }

    void mostrar() {
        mostrarArbol(raiz, "", true);
    }
}

public class Dos {

    public static void main(String[] args) {

        AVL arbol = new AVL();

        // Insertar datos del árbol de la figura
        int datos[] = {
                33, 20, 45, 12, 26,
                41, 56, 6, 15, 24,
                35, 44, 48, 59, 17,
                38, 46, 53, 65, 50
        };

        for (int x : datos)
            arbol.insertar(x);

        System.out.println("================================");
        System.out.println("ARBOL AVL INICIAL");
        System.out.println("================================");

        arbol.mostrar();

        // Eliminaciones
        int eliminar[] = {12, 33, 46, 59, 45, 56};

        for (int x : eliminar) {

            System.out.println("\n================================");
            System.out.println("ELIMINAR " + x);
            System.out.println("================================");

            arbol.eliminar(x);

            System.out.println("\nÁrbol AVL Resultante:");

            arbol.mostrar();
        }

        System.out.println("\n================================");
        System.out.println("ARBOL AVL FINAL");
        System.out.println("================================");

        arbol.mostrar();
    }
}