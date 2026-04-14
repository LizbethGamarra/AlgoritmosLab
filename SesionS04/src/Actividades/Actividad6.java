package Actividades;

public class Actividad6 {

    static int getValue(int[] values, int n) {

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            int max = -1;

            for (int j = 0; j < i; j++) {
                max = Math.max(max, values[j] + dp[i - j - 1]);
            }

            dp[i] = max;
        }

        return dp[n];
    }

    public static void main(String[] args) {

        int[] values = {3, 7, 1, 3, 9};
        int n = values.length;

        System.out.println("Valor máximo: " + getValue(values, n));
    }
}