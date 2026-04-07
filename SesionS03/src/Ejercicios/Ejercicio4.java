package Ejercicios;

public class Ejercicio4{

    public static int potenciaRapida(int x, int y) {
        // Si y == 0, retorna 1
        // Complejidad: O(1)
        if (y == 0)
            return 1; // O(1)
        // Si y es par:
        // Llama recursivamente con y/2
        // Luego multiplica el resultado por sí mismo
        // Recurrencia: T(y) = T(y/2) + O(1)
        if (y % 2 == 0) {
            int mitad = potenciaRapida(x, y / 2); // T(y/2) + O(1)
            return mitad * mitad;                 // O(1)
        }
        // Si y es impar:
        // Llama recursivamente con y-1
        // Luego multiplica el resultado por x
        // Recurrencia: T(y) = T(y-1) + O(1)
        else {
            return x * potenciaRapida(x, y - 1); // T(y-1) + O(1)
        }
    }

    public static void main(String[] args) {
        int resultado = potenciaRapida(2, 10); // O(log y) promedio
        System.out.println("2^10 = " + resultado);
    }
    // Complejidad final (peor caso): O(y)
    // Complejidad tipica: O(log y)
}