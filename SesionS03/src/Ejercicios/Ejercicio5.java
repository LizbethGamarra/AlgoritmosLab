package Ejercicios;

class Ejercicio5 {
    // Función Fibonacci recursiva
    static int fibonacci(int n) {
        // Caso base: n = 0 o n = 1
        // Complejidad: O(1)
        if (n <= 1) return n; // O(1)
        // Caso recursivo:
        // Llama a fibonacci(n-1) y fibonacci(n-2)
        // Recurrencia: T(n) = T(n-1) + T(n-2) + O(1)
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    public static void main(String[] args) {
        int n = 6; // O(1)
        System.out.println("Fibonacci de " + n + ": " + fibonacci(n)); // O(F(n))
    }
    //complejidad de enfoque recursivo simple:O(2^n)
    //complejidad de enfoque iterativo:O(n) 
}