package Actividades;

class GestorDeTareas<T extends Comparable<T>> {
    private ListLinked<T> lista = new ListLinked<>();

    public void agregarTarea(T tarea) {
        lista.insertLast(tarea);
    }

    public boolean eliminarTarea(T tarea) {
        return lista.removeNode(tarea);
    }

    public boolean contieneTarea(T tarea) {
        return lista.search(tarea);
    }

    public void imprimirTareas() {
        lista.print();
    }

    public int contarTareas() {
        return lista.length();
    }

    public void invertirTareas() {
        lista.reverse();
    }

    public T obtenerTareaMasPrioritaria() {
        Node<T> temp = lista.getHead();
        if (temp == null) return null;

        T mejor = temp.value;
        while (temp != null) {
            if (temp.value.compareTo(mejor) < 0) {
                mejor = temp.value;
            }
            temp = temp.next;
        }
        return mejor;
    }
}