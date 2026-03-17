package Ejercicio1;

public class ContainerRect {

    private Rectangulo[] rectangulos;
    private double[] distancias;
    private double[] areas;
    private int n;

    private int numRec = 0; 
    public ContainerRect(int n) {
        this.n = n;
        rectangulos = new Rectangulo[n];
        distancias = new double[n];
        areas = new double[n];
    }

    public void addRectangulo(Rectangulo r) {

        if (numRec < n) {

            rectangulos[numRec] = r;

            distancias[numRec] =
                r.getEsquina1().distancia(r.getEsquina2());

            areas[numRec] = r.calculoArea();

            numRec++;

        } else {
            System.out.println("Ya no entra más.");
        }
    }

    public String toString() {

        String res = "Rectangulo   Coordenadas   Distancia   Area\n";

        for (int i = 0; i < numRec; i++) {

            res = res + (i + 1) + "   ";
            res = res + rectangulos[i].toString() + "   ";
            res = res + distancias[i] + "   ";
            res = res + areas[i] + "\n";
        }
        return res;
    }
}