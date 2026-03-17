package PC1_Actividades;

import java.util.*;
public class Coordenada {
    private double x;
    private double y;
    // Constructor por defecto
    public Coordenada() {
        this(0, 0);
    }
    // Constructor con parámetros
    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // Constructor copia
    public Coordenada(Coordenada c) {
        this(c.x, c.y);
    }
    // Setters
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    // Getters
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }

    // Distancia (instancia)
    public double distancia(Coordenada c) {
        double dx = c.x - this.x;
        double dy = c.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Distancia (estática)
    public static double distancia(Coordenada c1, Coordenada c2) {
        double dx = c2.x - c1.x;
        double dy = c2.y - c1.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // toString correcto
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}