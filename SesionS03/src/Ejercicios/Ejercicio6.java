package Ejercicios;

public class Ejercicio6 {
    // Función que simula la recurrencia T(n) = T(n/2) + 3 con caso base T(1) = 7
    public static int funcionRecursiva(int n) {
        // Caso base: si n == 1, retorna 7
        if (n == 1) {
            return 7; // O(1)
        }
        // Caso recursivo: llamada con n/2 + constante 3
        return funcionRecursiva(n / 2) + 3; // T(n) = T(n/2) + O(1)
    }

    public static void main(String[] args) {
        int n = 16; 
        int resultado = funcionRecursiva(n);
        System.out.println("Resultado para n=" + n + " es: " + resultado);
    }
    //Complejidad: O(log n)
}