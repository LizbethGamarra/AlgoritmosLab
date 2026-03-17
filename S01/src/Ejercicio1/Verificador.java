package Ejercicio1;

public class Verificador {

    public static int verificar(Rectangulo A, Rectangulo B) {

        double ax1 = Math.min(A.getEsquina1().getX(), A.getEsquina2().getX());
        double ax2 = Math.max(A.getEsquina1().getX(), A.getEsquina2().getX());
        double ay1 = Math.min(A.getEsquina1().getY(), A.getEsquina2().getY());
        double ay2 = Math.max(A.getEsquina1().getY(), A.getEsquina2().getY());

        double bx1 = Math.min(B.getEsquina1().getX(), B.getEsquina2().getX());
        double bx2 = Math.max(B.getEsquina1().getX(), B.getEsquina2().getX());
        double by1 = Math.min(B.getEsquina1().getY(), B.getEsquina2().getY());
        double by2 = Math.max(B.getEsquina1().getY(), B.getEsquina2().getY());

        if (ax2 < bx1 || bx2 < ax1 || ay2 < by1 || by2 < ay1) {
            return 3;
        }

        if (ax2 == bx1 || bx2 == ax1 || ay2 == by1 || by2 == ay1) {
            return 2;
        }

        return 1;
    }
}