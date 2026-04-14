package Actividades;
import java.util.Arrays;

public class Actividad5 {
	public static int moda2(int[] array) {

        Arrays.sort(array); 

        int moda = array[0];
        int maxfrec = 1;
        int frec = 1;

        for (int i = 1; i < array.length; i++) {

            if (array[i] == array[i - 1]) {
                frec++;
            } else {
                if (frec > maxfrec) {
                    maxfrec = frec;
                    moda = array[i - 1];
                }
                frec = 1;
            }
        }

        if (frec > maxfrec) {
            moda = array[array.length - 1];
        }
        return moda;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3};

        System.out.println("Moda2: " + moda2(arr));
    }
}
