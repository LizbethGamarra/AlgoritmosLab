package Ejecicio2;
import java.io.*;
import java.util.*;
public class Principal {

    public static void main(String[] args) throws Exception {

        Scanner file = new Scanner(new File("datos.txt"));

        int filas = file.nextInt();
        int columnas = file.nextInt();

        Zona[][] matriz = new Zona[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String mineral = file.next();
                int cantidad = file.nextInt();
                double pureza = file.nextDouble();
                matriz[i][j] = new Zona(mineral, cantidad, pureza);
            }
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese tamaño k: ");
        int k = sc.nextInt();

        double maxValor = -1;
        int bestI = 0, bestJ = 0;

        for (int i = 0; i <= filas - k; i++) {
            for (int j = 0; j <= columnas - k; j++) {

                double suma = 0;

                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        suma += matriz[x][y].getValor();
                    }
                }

                if (suma > maxValor) {
                    maxValor = suma;
                    bestI = i;
                    bestJ = j;
                }
            }
        }

        System.out.println("\nRegión más valiosa encontrada:");
        System.out.println("Posición inicial: (" + bestI + "," + bestJ + ")");
        System.out.println("Tamaño de la región: " + k + " x " + k);

        System.out.println("\nZonas analizadas:");

        Map<String, Integer> conteo = new HashMap<>();

        for (int i = bestI; i < bestI + k; i++) {
            for (int j = bestJ; j < bestJ + k; j++) {
                Zona z = matriz[i][j];
                System.out.println("[ " + z.mineral + ", cantidad: " + z.cantidad + ", pureza: " + z.pureza + " ]");

                conteo.put(z.mineral, conteo.getOrDefault(z.mineral, 0) + 1);
            }
        }

        System.out.println("\nValor total estimado: " + maxValor);

        String maxMineral = "";
        int maxCount = 0;

        for (String m : conteo.keySet()) {
            if (conteo.get(m) > maxCount) {
                maxCount = conteo.get(m);
                maxMineral = m;
            }
        }

        System.out.println("\nMineral predominante en la región: " + maxMineral);
    }
}