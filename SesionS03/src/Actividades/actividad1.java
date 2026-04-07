package Actividades;

public class actividad1 {
	//a
	public static int max(int x, int y) {
		int result; // O(1)

		if (x == y) { // O(1)
			result = x; // O(1)
		} else {
			if (x > y) { // O(1)
				result = x; // O(1)
			} else {
				result = y; // O(1)
			}
		}

		return result; // O(1)
	}
	
	//b
	public static int suma(int[] v, int tamaño) {
	    int result = 0;                 // O(1)

	    if (tamaño <= 0) {              // O(1)
	        return 0;                   // O(1)
	    }

	    for (int i = 0; i < tamaño; i++) {   // O(n)
	        if (v[i] >= 0) {                // O(1)
	            result = result + v[i];     // O(1)
	        } else {
	            result = result + v[i];     // O(1)
	        }
	    }

	    return result;                 // O(1)
	}
	
	//c
	public static void ordenar(int[] v, int tamaño) {

	    if (tamaño <= 1) {                 // O(1)
	        return;                        // O(1)
	    }

	    for (int i = 0; i < tamaño - 1; i++) {          // O(n)
	        for (int j = 0; j < tamaño - 1 - i; j++) {  // O(n)
	            if (v[j] > v[j + 1]) {                 // O(1)
	                int aux = v[j];                    // O(1)
	                v[j] = v[j + 1];                  // O(1)
	                v[j + 1] = aux;                   // O(1)
	            }
	        }
	    }
	}	
}