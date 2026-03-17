package Ejecicio2;

public class Zona {
    String mineral;
    int cantidad;
    double pureza;

    public Zona(String mineral, int cantidad, double pureza) {
        this.mineral = mineral;
        this.cantidad = cantidad;
        this.pureza = pureza;
    }

    public double getValor() {
        return cantidad * pureza;
    }

    public String toString() {
        return mineral + " " + cantidad + " " + pureza;
    }
}