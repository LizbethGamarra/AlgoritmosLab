package PC1_Actividades;

public class Verificador {

    public static boolean esSobrePos(Rectangulo r1, Rectangulo r2) {

        double r1x1 = Math.min(r1.getEsquina1().getX(), r1.getEsquina2().getX());
        double r1x2 = Math.max(r1.getEsquina1().getX(), r1.getEsquina2().getX());
        double r1y1 = Math.min(r1.getEsquina1().getY(), r1.getEsquina2().getY());
        double r1y2 = Math.max(r1.getEsquina1().getY(), r1.getEsquina2().getY());

        double r2x1 = Math.min(r2.getEsquina1().getX(), r2.getEsquina2().getX());
        double r2x2 = Math.max(r2.getEsquina1().getX(), r2.getEsquina2().getX());
        double r2y1 = Math.min(r2.getEsquina1().getY(), r2.getEsquina2().getY());
        double r2y2 = Math.max(r2.getEsquina1().getY(), r2.getEsquina2().getY());

        return !(r1x2 <= r2x1 || r1x1 >= r2x2 || r1y2 <= r2y1 || r1y1 >= r2y2);
    }

    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {

        if (esSobrePos(r1, r2)) return false;

        double r1x1 = Math.min(r1.getEsquina1().getX(), r1.getEsquina2().getX());
        double r1x2 = Math.max(r1.getEsquina1().getX(), r1.getEsquina2().getX());
        double r1y1 = Math.min(r1.getEsquina1().getY(), r1.getEsquina2().getY());
        double r1y2 = Math.max(r1.getEsquina1().getY(), r1.getEsquina2().getY());

        double r2x1 = Math.min(r2.getEsquina1().getX(), r2.getEsquina2().getX());
        double r2x2 = Math.max(r2.getEsquina1().getX(), r2.getEsquina2().getX());
        double r2y1 = Math.min(r2.getEsquina1().getY(), r2.getEsquina2().getY());
        double r2y2 = Math.max(r2.getEsquina1().getY(), r2.getEsquina2().getY());

        boolean juntoX = (r1x2 == r2x1 || r1x1 == r2x2) && !(r1y2 <= r2y1 || r1y1 >= r2y2);
        boolean juntoY = (r1y2 == r2y1 || r1y1 == r2y2) && !(r1x2 <= r2x1 || r1x1 >= r2x2);

        return juntoX || juntoY;
    }

    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
        return !esSobrePos(r1, r2) && !esJunto(r1, r2);
    }
}