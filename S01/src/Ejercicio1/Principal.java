package Ejercicio1;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ContainerRect cont = new ContainerRect(10);

        System.out.println("Ingrese esquina 1 del Rectangulo A:");
        Coordenada a1 = new Coordenada(sc.nextDouble(), sc.nextDouble());

        System.out.println("Ingrese esquina 2 del Rectangulo A:");
        Coordenada a2 = new Coordenada(sc.nextDouble(), sc.nextDouble());

        Rectangulo A = new Rectangulo(a1, a2);
        cont.addRectangulo(A);

        System.out.println("Ingrese esquina 1 del Rectangulo B:");
        Coordenada b1 = new Coordenada(sc.nextDouble(), sc.nextDouble());

        System.out.println("Ingrese esquina 2 del Rectangulo B:");
        Coordenada b2 = new Coordenada(sc.nextDouble(), sc.nextDouble());

        Rectangulo B = new Rectangulo(b1, b2);
        cont.addRectangulo(B);

        System.out.println("\nCONTENEDOR DE RECTANGULOS:");
        System.out.println(cont);
    }
}