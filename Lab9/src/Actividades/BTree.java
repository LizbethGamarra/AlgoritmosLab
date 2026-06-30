package Actividades;
 
public class BTree<E extends Comparable<E>> {
 
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;
 
    // Atributo auxiliar para remove(): indica si un hijo quedó deficiente
    // después de una eliminación recursiva.
    private boolean deficiente;
 
    public BTree(int orden) {
        this.orden = orden;
        this.root  = null;
    }
 
    public boolean isEmpty() {
        return root == null;
    }
 
   
    //  INSERT  ()
    //Chilo el experto en arboles B 😲 😮 😯 😳 🤯 😱💪

 
    public void insert(E cl) {
 
        up = false;
 
        E mediana;
        BNode<E> pnew;
 
        mediana = push(root, cl);
 
        if (up) {
 
            pnew = new BNode<E>(orden);
 
            pnew.count = 1;
            pnew.keys.set(0, mediana);
 
            pnew.childs.set(0, root);
            pnew.childs.set(1, nDes);
 
            root = pnew;
        }
    }
 
    private E push(BNode<E> current, E cl) {
 
        int pos[] = new int[1];
        E mediana;
 
        if (current == null) {
 
            up  = true;
            nDes = null;
 
            return cl;
        }
 
        boolean fl = current.searchNode(cl, pos);
 
        if (fl) {
 
            System.out.println("Item duplicado");
            up = false;
 
            return null;
        }
 
        mediana = push(current.childs.get(pos[0]), cl);
 
        if (up) {
 
            if (current.nodeFull(orden - 1)) {
 
                mediana = dividedNode(current, mediana, pos[0]);
 
            } else {
 
                putNode(current, mediana, nDes, pos[0]);
                up = false;
            }
        }
 
        return mediana;
    }
 
    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
 
        int i;
 
        for (i = current.count - 1; i >= k; i--) {
 
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
 
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
 
        current.count++;
    }
 
    private E dividedNode(BNode<E> current, E cl, int k) {
 
        BNode<E> rd = nDes;
 
        int i;
        int posMdna;
 
        if (k <= orden / 2)
            posMdna = orden / 2;
        else
            posMdna = orden / 2 + 1;
 
        nDes = new BNode<E>(orden);
 
        for (i = posMdna; i < orden - 1; i++) {
 
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
 
        nDes.count = (orden - 1) - posMdna;
 
        current.count = posMdna;
 
        if (k <= orden / 2)
            putNode(current, cl, rd, k);
        else
            putNode(nDes, cl, rd, k - posMdna);
 
        E median = current.keys.get(current.count - 1);
 
        nDes.childs.set(0, current.childs.get(current.count));
 
        current.count--;
 
        up = true;
 
        return median;
    }
 
   
    //  SEARCH  ()
    
 
    public boolean search(E cl) {
        return search(root, cl);
    }
 
    private boolean search(BNode<E> current, E cl) {
 
        if (current == null) return false;
 
        int pos[] = new int[1];
        boolean found = current.searchNode(cl, pos);
 
        if (found) {
 
            System.out.println(cl + " se encuentra en el nodo "
                    + current.idNode + " en la posicion " + pos[0]);
            return true;
        }
 
        return search(current.childs.get(pos[0]), cl);
    }
 
    
    //  SEARCH RANGE  ()
    
 
    public void searchRange(E min, E max) {
 
        if (min.compareTo(max) > 0) {
            System.out.println("Rango invalido");
            return;
        }
 
        searchRange(root, min, max);
        System.out.println();
    }
 
    private void searchRange(BNode<E> current, E min, E max) {
 
        if (current == null) return;
 
        int i;
 
        for (i = 0; i < current.count; i++) {
 
            if (current.keys.get(i).compareTo(min) > 0)
                searchRange(current.childs.get(i), min, max);
 
            if (current.keys.get(i).compareTo(min) >= 0 &&
                current.keys.get(i).compareTo(max) <= 0)
                System.out.print(current.keys.get(i) + " ");
 
            if (current.keys.get(i).compareTo(max) > 0)
                return;
        }
 
        searchRange(current.childs.get(i), min, max);
    }
 
    
    //  REMOVE  – punto de entrada público
   
 
    //
    // Elimina la clave  cl} del árbol B.
    // Sigue las reglas clásicas:
    //   1. Si la clave está en un nodo interno la reemplaza por su sucesor
    //      en orden y continúa la eliminación en la hoja del sucesor.
    //   2. Si la clave está en una hoja la borra directamente.
    //   3. Tras borrar, si el nodo queda con menos del mínimo de claves
    //     intenta redistribución; si no es posible, realiza una fusión.
    //   4. La fusión puede propagarse hacia la raíz; si la raíz queda vacía
    //     se sustituye por su único hijo (el árbol decrece en altura).
    public void remove(E cl) {
 
        if (isEmpty()) {
            System.out.println("El árbol está vacío.");
            return;
        }
 
        deficiente = false;
        eliminar(root, cl);
 
        // Si la raíz quedó vacía tras una fusión, bajar un nivel
        if (root.count == 0) {
            root = root.childs.get(0);
        }
    }
 
    //  MÉTODOS AUXILIARES PRIVADOS
 

     //Mínimo de claves permitido en cualquier nodo que no sea la raíz.
     // Para un árbol B de orden m:  min = ceil(m/2) - 1
     
    private int minimoClaves() {
        return (int) Math.ceil(orden / 2.0) - 1;
    }
 
    
    // Indica si un nodo es hoja (todos sus hijos son null).
     
    private boolean esHoja(BNode<E> nodo) {
        return nodo.childs.get(0) == null;
    }
 
  
    // Núcleo recursivo de la eliminación.
    //  current nodo que se está visitando
    //  clave a eliminar

    private void eliminar(BNode<E> current, E cl) {
 
        if (current == null) {
            System.out.println("Clave " + cl + " no encontrada.");
            deficiente = false;
            return;
        }
 
        int pos[] = new int[1];
        boolean encontrado = current.searchNode(cl, pos);
 
        if (encontrado) {
            // La clave está en este nodo 
 
            if (esHoja(current)) {
                // Caso hoja: eliminar directamente
                eliminarDePosicion(current, pos[0]);
                deficiente = current.count < minimoClaves();
 
            } else {
                // Caso nodo interno: reemplazar por el sucesor en orden
                E sucesor = obtenerSucesor(current, pos[0]);
                current.keys.set(pos[0], sucesor);
 
                // Eliminar el sucesor del subárbol derecho
                eliminar(current.childs.get(pos[0] + 1), sucesor);
 
                // Si el hijo derecho quedó deficiente, corregir
                if (deficiente) {
                    verificarMinimo(current, pos[0] + 1);
                }
            }
 
        } else {
            // ── La clave NO está en este nodo: bajar por el hijo correcto ───
 
            int hijoIdx = pos[0];           // índice del hijo por donde descender
            eliminar(current.childs.get(hijoIdx), cl);
 
            if (deficiente) {
                verificarMinimo(current, hijoIdx);
            }
        }
    }
 
   
    // Elimina la clave en la posición {@code pos} de {@code nodo}
    // desplazando el resto hacia la izquierda.
   
    private void eliminarDePosicion(BNode<E> nodo, int pos) {
 
        for (int i = pos; i < nodo.count - 1; i++) {
            nodo.keys.set(i, nodo.keys.get(i + 1));
            nodo.childs.set(i + 1, nodo.childs.get(i + 2));
        }
 
        // Limpiar la última posición
        nodo.keys.set(nodo.count - 1, null);
        nodo.childs.set(nodo.count, null);
 
        nodo.count--;
    }
 
     // Devuelve el sucesor en orden de la clave en {@code posicion} dentro
     // de {@code nodo}: es la clave más pequeña del subárbol derecho de esa clave.
     
    private E obtenerSucesor(BNode<E> nodo, int posicion) {
 
        BNode<E> actual = nodo.childs.get(posicion + 1);
 
        while (!esHoja(actual)) {
            actual = actual.childs.get(0);
        }
 
        return actual.keys.get(0);
    }
 
    //Devuelve el antecesor en orden de la clave en {@code posicion} dentro
    //de {@code nodo}: es la clave más grande del subárbol izquierdo de esa clave.
    //(No se usa en el flujo principal pero se incluye según los requisitos.)
     
    private E obtenerAntecesor(BNode<E> nodo, int posicion) {
 
        BNode<E> actual = nodo.childs.get(posicion);
 
        while (!esHoja(actual)) {
            actual = actual.childs.get(actual.count);
        }
 
        return actual.keys.get(actual.count - 1);
    }
 
    
    //Corrige la deficiencia del hijo {@code hijoIdx} de {@code padre}.
     //Intenta primero redistribución con el hermano izquierdo,
     //luego con el hermano derecho; si ninguno puede prestar, fusiona.
     
    private void verificarMinimo(BNode<E> padre, int hijoIdx) {
 
        BNode<E> hijoDeficiente = padre.childs.get(hijoIdx);
 
        //  Intentar redistribución con el hermano IZQUIERDO 
        if (hijoIdx > 0) {
 
            BNode<E> hermanoIzq = padre.childs.get(hijoIdx - 1);
 
            if (hermanoIzq.count > minimoClaves()) {
                redistribuirIzquierda(padre, hijoIdx);
                deficiente = false;
                return;
            }
        }
 
        //  Intentar redistribución con el hermano DERECHO 
        if (hijoIdx < padre.count) {
 
            BNode<E> hermanoDer = padre.childs.get(hijoIdx + 1);
 
            if (hermanoDer.count > minimoClaves()) {
                redistribuirDerecha(padre, hijoIdx);
                deficiente = false;
                return;
            }
        }
 
        //  Ninguno puede prestar → FUSIONAR
        if (hijoIdx > 0) {
            // Fusionar hijo deficiente con su hermano izquierdo
            fusionar(padre, hijoIdx - 1);   // el separador es padre.keys[hijoIdx-1]
        } else {
            // Fusionar hijo deficiente con su hermano derecho
            fusionar(padre, hijoIdx);       // el separador es padre.keys[hijoIdx]
        }
 
        deficiente = padre.count < minimoClaves();
    }
 
    
    //Redistribución: el hermano IZQUIERDO presta su clave mayor al padre,
   // y la clave separadora del padre baja al hijo deficiente (por la izquierda).
    
    //  padre    nodo padre
    // hijoIdx  índice del hijo deficiente (≥ 1)
    private void redistribuirIzquierda(BNode<E> padre, int hijoIdx) {
 
        BNode<E> hijo      = padre.childs.get(hijoIdx);
        BNode<E> hermanoIzq = padre.childs.get(hijoIdx - 1);
 
        // Hacer espacio en hijo: desplazar todo una posición a la derecha
        hijo.childs.set(hijo.count + 1, hijo.childs.get(hijo.count));
        for (int i = hijo.count - 1; i >= 0; i--) {
            hijo.keys.set(i + 1, hijo.keys.get(i));
            hijo.childs.set(i + 1, hijo.childs.get(i));
        }
        hijo.childs.set(0, null);   // se llenará con el hijo más derecho del hermano
 
        // Bajar la clave separadora del padre al inicio del hijo
        hijo.keys.set(0, padre.keys.get(hijoIdx - 1));
        // El primer hijo del deficiente pasa a ser el último hijo del hermano izq
        hijo.childs.set(0, hermanoIzq.childs.get(hermanoIzq.count));
 
        hijo.count++;
 
        // Subir la clave mayor del hermano izquierdo al padre
        padre.keys.set(hijoIdx - 1, hermanoIzq.keys.get(hermanoIzq.count - 1));
 
        // Limpiar la clave y el último hijo del hermano izquierdo
        hermanoIzq.keys.set(hermanoIzq.count - 1, null);
        hermanoIzq.childs.set(hermanoIzq.count, null);
        hermanoIzq.count--;
    }
 
    
    //Redistribución: el hermano DERECHO presta su clave menor al padre,
    //y la clave separadora del padre baja al hijo deficiente (por la derecha).
    // padre    nodo padre
    // hijoIdx  índice del hijo deficiente
     
    private void redistribuirDerecha(BNode<E> padre, int hijoIdx) {
 
        BNode<E> hijo      = padre.childs.get(hijoIdx);
        BNode<E> hermanoDer = padre.childs.get(hijoIdx + 1);
 
        // Añadir la clave separadora del padre al final del hijo deficiente
        hijo.keys.set(hijo.count, padre.keys.get(hijoIdx));
        // El primer hijo del hermano derecho pasa a ser el último hijo del deficiente
        hijo.childs.set(hijo.count + 1, hermanoDer.childs.get(0));
        hijo.count++;
 
        // Subir la clave menor del hermano derecho al padre
        padre.keys.set(hijoIdx, hermanoDer.keys.get(0));
 
        // Desplazar el hermano derecho una posición a la izquierda
        hermanoDer.childs.set(0, hermanoDer.childs.get(1));
        for (int i = 0; i < hermanoDer.count - 1; i++) {
            hermanoDer.keys.set(i, hermanoDer.keys.get(i + 1));
            hermanoDer.childs.set(i + 1, hermanoDer.childs.get(i + 2));
        }
        // Limpiar la última posición del hermano derecho
        hermanoDer.keys.set(hermanoDer.count - 1, null);
        hermanoDer.childs.set(hermanoDer.count, null);
        hermanoDer.count--;
    }
    //  Fusiona {@code padre.childs[sepIdx]} con {@code padre.childs[sepIdx+1]}.
    // La clave separadora {@code padre.keys[sepIdx]} baja al nodo fusionado.
    // El nodo derecho se descarta.
    
    //   padre   nodo padre
    //   sepIdx  índice de la clave separadora en el padre
    private void fusionar(BNode<E> padre, int sepIdx) {
 
        BNode<E> hijoIzq = padre.childs.get(sepIdx);
        BNode<E> hijoDer  = padre.childs.get(sepIdx + 1);
 
        //  Bajar la clave separadora del padre al nodo izquierdo 
        hijoIzq.keys.set(hijoIzq.count, padre.keys.get(sepIdx));
        hijoIzq.childs.set(hijoIzq.count + 1, hijoDer.childs.get(0));
        hijoIzq.count++;
 
        //  Copiar todas las claves e hijos del nodo derecho al izquierdo
        for (int i = 0; i < hijoDer.count; i++) {
            hijoIzq.keys.set(hijoIzq.count, hijoDer.keys.get(i));
            hijoIzq.childs.set(hijoIzq.count + 1, hijoDer.childs.get(i + 1));
            hijoIzq.count++;
        }
 
        //  Eliminar la clave separadora y el puntero al hijo derecho del padre
        for (int i = sepIdx; i < padre.count - 1; i++) {
            padre.keys.set(i, padre.keys.get(i + 1));
            padre.childs.set(i + 1, padre.childs.get(i + 2));
        }
 
        padre.keys.set(padre.count - 1, null);
        padre.childs.set(padre.count, null);
        padre.count--;
    }
 
    //  TO STRING  ()
  
 
    @Override
    public String toString() {
 
        String s = "";
 
        if (isEmpty())
            s = "BTree is empty...";
        else
            s = writeTree(root);
 
        return s;
    }
 
    private String writeTree(BNode<E> current) {
 
        if (current == null) return "";
 
        String s = current.toString() + "\n";
 
        for (int i = 0; i <= current.count; i++) {
            s += writeTree(current.childs.get(i));
        }
 
        return s;
    }

 //Ejercicio4
 // RECORRIDO INORDEN

 public void inOrder() {
     inOrder(root);
 }

 private void inOrder(BNode<E> current) {

     if (current == null)
         return;

     int i;

     for (i = 0; i < current.count; i++) {

         inOrder(current.childs.get(i));

         System.out.println(current.keys.get(i));
     }

     inOrder(current.childs.get(i));
 }

//Ejercicio4
 // ALTURA DEL ARBOL

 public int height() {
     return height(root);
 }

 private int height(BNode<E> current) {

     if (current == null)
         return 0;

     if (current.childs.get(0) == null)
         return 1;

     return 1 + height(current.childs.get(0));
 }

//Ejercicio4
 // CANTIDAD TOTAL DE CLAVES

 public int countKeys() {
     return countKeys(root);
 }

 private int countKeys(BNode<E> current) {

     if (current == null)
         return 0;

     int total = current.count;

     for (int i = 0; i <= current.count; i++) {

         total += countKeys(current.childs.get(i));
     }

     return total;
 	}
 }
