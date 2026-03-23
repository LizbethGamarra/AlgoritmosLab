package Ejercicio8;


public class Test {

    public static <T> void swap(T[] arreglo, int i, int j) {
        if (i < 0 || j < 0 || i >= arreglo.length || j >= arreglo.length) {
            System.out.println("Índices fuera de rango");
            return;
        }

        T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }

    public static void main(String[] args) {

        String[] letras = {"A", "B", "C", "D"};

        // Antes
        for (String s : letras) {
            System.out.print(s + " ");
        }

        System.out.println();

        // Intercambio
        swap(letras, 1, 3);

        // Después
        for (String s : letras) {
            System.out.print(s + " ");
        }
    }
}