package Actividades;

import javax.swing.*;
import java.awt.*;

public class Actividad3 extends JPanel {
    private int nivel;
    // Constructor para definir nivel
    public Actividad3(int nivel) {
        this.nivel = nivel;
    }
    // Método recursivo
    public void drawTriangle(Graphics g,
                             int x1, int y1,
                             int x2, int y2,
                             int x3, int y3,
                             int nivel) {
        // Caso base
        if (nivel == 0) {
            int[] xPoints = {x1, x2, x3};
            int[] yPoints = {y1, y2, y3};
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            // Calcular puntos medios
            int mx12 = (x1 + x2) / 2;
            int my12 = (y1 + y2) / 2;

            int mx23 = (x2 + x3) / 2;
            int my23 = (y2 + y3) / 2;

            int mx31 = (x3 + x1) / 2;
            int my31 = (y3 + y1) / 2;

            // Llamadas recursivas (3 triángulos)
            drawTriangle(g, x1, y1, mx12, my12, mx31, my31, nivel - 1);
            drawTriangle(g, mx12, my12, x2, y2, mx23, my23, nivel - 1);
            drawTriangle(g, mx31, my31, mx23, my23, x3, y3, nivel - 1);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar triángulo principal
        drawTriangle(g, 100, 500, 500, 500, 300, 100, nivel);
    }
    public static void main(String[] args) {
        int nivel = 4; // prueba con 4, 6, 8
        JFrame frame = new JFrame("Triángulo de Sierpinski - Nivel " + nivel);
        Actividad3 panel = new Actividad3(nivel);
        frame.add(panel);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}