package Ejercicios;


public class Caja<T> {
    private T contenido;
    private String color;

    public Caja(String color, T contenido) {
        this.color = color;
        this.contenido = contenido;
    }

    public T getContenido() {
        return contenido;
    }

    public void setContenido(T contenido) {
        this.contenido = contenido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Caja color: " + color + " | Contenido: " + contenido;
    }
}