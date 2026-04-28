package Ejercicio7;

public class Main {
    public static void main(String[] args) {
        SortedListLinked<Tarea> lista = new SortedListLinked<>();

        lista.insertOrden(new Tarea("Estudiar", 3));
        lista.insertOrden(new Tarea("Dormir", 1));
        lista.insertOrden(new Tarea("Hacer tarea", 2));
        lista.insertOrden(new Tarea("Jugar", 5));

        lista.mostrarLista();
    }
}