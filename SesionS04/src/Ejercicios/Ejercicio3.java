package Ejercicios;

public class Ejercicio3 {
    public static int[][] calcularCostos(int[][] T, int n) {
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            C[i][i] = 0;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                C[i][j] = T[i][j];
                for (int k = i + 1; k < j; k++) {
                    int costo = T[i][k] + C[k][j];
                    if (costo < C[i][j]) {
                        C[i][j] = costo;
                    }
                }
            }
        }
        return C;
    }
    public static void main(String[] args) {
        int[][] T = {
            {0, 5, 9, 10},
            {0, 0, 3, 7},
            {0, 0, 0, 1},
            {0, 0, 0, 0}
        };
        int n = T.length;
        int[][] C = calcularCostos(T, n);
        System.out.println("Costo mínimo de 0 a 3: " + C[0][3]);
    }
}