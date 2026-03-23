package Ejercicios;

public class TestGen {
	static <T> boolean exist(T[] arreglo, T elemento) {
	    for (int i = 0; i < arreglo.length; i++) {
	        if (arreglo[i].equals(elemento)) {
	            return true;
	        }
	    }
	    return false;
	}
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
        // ...
        String[] v = {"Perez", "Sanchez", "Rodriguez"};
        Integer[] w = {12, 34, 56};

        System.out.println(exist(v, "Sanchez")); // true
        System.out.println(exist(w, 34));    // true
        // System.out.println(exist(w, "Salas")); // Error intencional (tipos incompatibles)
        Golosina[] golosinas = {
        	    new Golosina("Gomitas", 0.5),
        	    new Golosina("Caramelos", 0.2)
        	};


 	      // Resultado: A D C B
    }
}