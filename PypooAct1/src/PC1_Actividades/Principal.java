package PC1_Actividades;
import java.util.Scanner;
public class Principal {
    public static Rectangulo rectanguloSobre(Rectangulo r1, Rectangulo r2) {
        double x1 = Math.max(
            Math.min(r1.getEsquina1().getX(), r1.getEsquina2().getX()),
            Math.min(r2.getEsquina1().getX(), r2.getEsquina2().getX())
        );
        double y1 = Math.max(
            Math.min(r1.getEsquina1().getY(), r1.getEsquina2().getY()),
            Math.min(r2.getEsquina1().getY(), r2.getEsquina2().getY())
        );
        double x2 = Math.min(
            Math.max(r1.getEsquina1().getX(), r1.getEsquina2().getX()),
            Math.max(r2.getEsquina1().getX(), r2.getEsquina2().getX())
        );
        double y2 = Math.min(
            Math.max(r1.getEsquina1().getY(), r1.getEsquina2().getY()),
            Math.max(r2.getEsquina1().getY(), r2.getEsquina2().getY())
        );
        return new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Rectángulo A
        System.out.println("Ingrese una esquina del 1er rectángulo:");
        double x1 = sc.nextDouble();
        double y1 = sc.nextDouble();
        System.out.println("Ingrese la esquina opuesta del 1er rectángulo:");
        double x2 = sc.nextDouble();
        double y2 = sc.nextDouble();
        // Rectángulo B
        System.out.println("Ingrese una esquina del 2do rectángulo:");
        double x3 = sc.nextDouble();
        double y3 = sc.nextDouble();

        System.out.println("Ingrese la esquina opuesta del 2do rectángulo:");
        double x4 = sc.nextDouble();
        double y4 = sc.nextDouble();

        Rectangulo r1 = new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));
        Rectangulo r2 = new Rectangulo(new Coordenada(x3, y3), new Coordenada(x4, y4));

        System.out.println();
        System.out.println("Rectangulo A = " + r1);
        System.out.println("Rectangulo B = " + r2);

        if (Verificador.esSobrePos(r1, r2)) {
            System.out.println("Rectangulos A y B se sobreponen");
            Rectangulo sobre = rectanguloSobre(r1, r2);
            System.out.println("Area de sobreposicion = " + sobre.calculoArea());

        } else if (Verificador.esJunto(r1, r2)) {
            System.out.println("Rectangulos A y B se juntan");
        } else {
            System.out.println("Rectangulos A y B son disjuntos");
        }
        sc.close();
    }
}