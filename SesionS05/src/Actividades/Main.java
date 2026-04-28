package Actividades;

public class Main {
    public static void main(String[] args) {
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();

        gestor.agregarTarea(new Tarea("Diseñar BD", 2, "pendiente"));
        gestor.agregarTarea(new Tarea("Deploy produccion", 1, "pendiente"));
        gestor.agregarTarea(new Tarea("Documentar API", 3, "completada"));
        gestor.agregarTarea(new Tarea("Code review", 2, "pendiente"));
        gestor.agregarTarea(new Tarea("Corregir bug #42", 1, "completada"));

        gestor.imprimirTareas();

        System.out.println("Mas prioritaria: " + gestor.obtenerTareaMasPrioritaria());

        gestor.invertirTareas();
        System.out.println("Lista invertida:");
        gestor.imprimirTareas();
    }
}
