package Actividades;
import java.util.*;

public class Actividad2 {
    public static void permutar(int[] arr, List<Integer> actual, boolean[] usado) {

        if (actual.size() == arr.length) {
            System.out.println(actual);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!usado[i]) {
                usado[i] = true;              // marcar como usado
                actual.add(arr[i]);           // agregar elemento
                permutar(arr, actual, usado);
                // backtracking
                actual.remove(actual.size() - 1);
                usado[i] = false;             // desmarcar
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        boolean[] usado = new boolean[arr.length];
        permutar(arr, new ArrayList<>(), usado);
    }
}
