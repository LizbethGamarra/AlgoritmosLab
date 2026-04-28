package Ejercicio8;
import java.util.ArrayList;
import java.util.Collections;

public class ColaReproduccion<T extends Cancion> {

    private NodeDoble<T> head;
    private NodeDoble<T> tail;
    private NodeDoble<T> actual;

    // 1. Agregar canción
    public void agregarCancion(T cancion) {
        NodeDoble<T> nuevo = new NodeDoble<>(cancion);

        if (head == null) {
            head = tail = actual = nuevo;
        } else {
            tail.next = nuevo;
            nuevo.prev = tail;
            tail = nuevo;
        }
    }

    // 2. Siguiente
    public T reproducirSiguiente() {
        if (actual != null && actual.next != null) {
            actual = actual.next;
        }
        return actual.data;
    }

    // 3. Anterior
    public T reproducirAnterior() {
        if (actual != null && actual.prev != null) {
            actual = actual.prev;
        }
        return actual.data;
    }

    // 4. Mostrar cola
    public void mostrarCola() {
        NodeDoble<T> temp = head;
        int i = 1;

        while (temp != null) {
            System.out.println(i + ". " + temp.data);
            temp = temp.next;
            i++;
        }
    }

    // 5. Duración total
    public int duracionTotal() {
        int total = 0;
        NodeDoble<T> temp = head;

        while (temp != null) {
            total += temp.data.getDuracionSeg();
            temp = temp.next;
        }

        return total;
    }

    // 6. Mezclar (Fisher-Yates usando ArrayList)
    public void mezclar() {
        ArrayList<T> lista = new ArrayList<>();
        NodeDoble<T> temp = head;

        // pasar a lista
        while (temp != null) {
            lista.add(temp.data);
            temp = temp.next;
        }

        // mezclar
        Collections.shuffle(lista);

        // reconstruir lista enlazada
        head = tail = actual = null;

        for (T cancion : lista) {
            agregarCancion(cancion);
        }
    }
    
    // 7. actual
    public T getActual() {
        return actual != null ? actual.data : null;
    }
}