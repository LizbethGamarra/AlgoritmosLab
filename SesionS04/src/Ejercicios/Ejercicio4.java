package Ejercicios;

public class Ejercicio4 {
    static int N, M;
    public static boolean resolver(int[][] laberinto) {
        N = laberinto.length;
        M = laberinto[0].length;

        int[][] solucion = new int[N][M];

        if (buscar(laberinto, 0, 0, solucion)) {
            imprimir(solucion);
            return true;
        } else {
            System.out.println("No hay camino");
            return false;
        }
    }
    public static boolean buscar(int[][] lab, int x, int y, int[][] sol) {
        if (x == N - 1 && y == M - 1 && lab[x][y] == 0) {
            sol[x][y] = 1;
            return true;
        }
        if (esValido(lab, x, y)) {
            sol[x][y] = 1; 
            if (buscar(lab, x, y + 1, sol)) return true;
            if (buscar(lab, x + 1, y, sol)) return true;
            if (buscar(lab, x, y - 1, sol)) return true;
            if (buscar(lab, x - 1, y, sol)) return true;
            sol[x][y] = 0;
            return false;
        }
        return false;
    }
    public static boolean esValido(int[][] lab, int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < M && lab[x][y] == 0);
    }
    public static void imprimir(int[][] sol) {
        System.out.println("Camino:");
        for (int i = 0; i < sol.length; i++) {
            for (int j = 0; j < sol[0].length; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[][] laberinto = {
            {0, 0, 1},
            {1, 0, 1},
            {1, 0, 0}
        };
        System.out.println(resolver(laberinto));
    }
}