package Ejercicio7;

public class Tarea implements Comparable<Tarea> {
    String nombre;
    int prioridad;

    public Tarea(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    @Override
    public String toString() {
        return nombre + "(Prioridad: " + prioridad + ")";
    }
}