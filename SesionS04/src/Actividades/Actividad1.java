package Actividades;

public class Actividad1 {

	    public static void main(String[] args) {
	        torresHanoi(3, 1, 2, 3);
	    }

	    public static void torresHanoi(int discos, int origen, int auxiliar, int destino) {

	        // Caso base
	        if (discos == 1) {
	            System.out.println("Mover disco de torre " + origen + " a torre " + destino);
	        } else {
	            // Paso recursivo
	            torresHanoi(discos - 1, origen, destino, auxiliar);
	            System.out.println("Mover disco de torre " + origen + " a torre " + destino);
	            torresHanoi(discos - 1, auxiliar, origen, destino);
	        }
	    }
	}

