package Ejercicios;

import java.util.*;

public class Principal {
	//Ejercicio2
	static <T> void mostrarBolsa(Bolsa<T> bolsa) {
	    for (T elemento : bolsa) {
	        System.out.println(elemento);
	    }
	}
	//Ejercicio2
	public static void main(String[] args) {
		Bolsa<Chocolatina> bolsaCho = new Bolsa<Chocolatina>(0);
		Chocolatina c = new Chocolatina("milka");
		Chocolatina c1 = new Chocolatina("milka");
		Chocolatina c2 = new Chocolatina("ferrero");
		bolsaCho.add(c);
		bolsaCho.add(c1);
		bolsaCho.add(c2);
		for (Chocolatina chocolatina : bolsaCho) {
			System.out.println(chocolatina.getMarca());
		}
		
		Bolsa<Golosina> bolsaGol = new Bolsa<>(0);
        bolsaGol.add(new Golosina("Gomitas", 0.5));
        bolsaGol.add(new Golosina("Caramelos", 0.2));
        bolsaGol.add(new Golosina("Chupetines", 0.3));

        System.out.println("\nGolosinas:");
        mostrarBolsa(bolsaGol);
        
        Bolsa<Chocolatina> bolsaChoo = new Bolsa<>(3);
        bolsaCho.add(new Chocolatina("Milka"));
        bolsaCho.add(new Chocolatina("Ferrero"));

        mostrarBolsa(bolsaChoo);
        //Ejercicio 5//
        Cajoneria<Golosina> cajon = new Cajoneria<>(10);
        cajon.add(new Caja<>("Rojo", new Golosina("Gomitas", 0.5)));
        cajon.add(new Caja<>("Azul", new Golosina("Caramelos", 0.2)));
        cajon.add(new Caja<>("Verde", new Golosina("Chupetin", 0.3)));
        cajon.add(new Caja<>("Amarillo", new Golosina("Galleta", 0.4)));
        cajon.add(new Caja<>("Negro", new Golosina("Chocolate", 0.6)));
        System.out.println(cajon.search(new Golosina("Gomitas", 0.5)));
        System.out.println(cajon.search(new Golosina("Caramelos", 0.2)));
        System.out.println("Eliminado: " + cajon.delete(new Golosina("Galleta", 0.4)));
        System.out.println("Después de eliminar:");
        System.out.println(cajon);
        //Ejercicio 5//   
        System.out.println("Cantidad de Gomitas: " + 
        	    cajon.contar(new Golosina("Gomitas", 0.5)));
        
        Cajoneria<Chocolatina> cajonChoco = new Cajoneria<>(5);
        //Ejercicio7
        cajonChoco.add(new Caja<>("Rojo", new Chocolatina("Milka")));
        cajonChoco.add(new Caja<>("Azul", new Chocolatina("Ferrero")));
        cajonChoco.add(new Caja<>("Verde", new Chocolatina("Milka")));
        System.out.println(cajonChoco.search(new Chocolatina("Milka")));

        System.out.println("Cantidad Milka: " + 
            cajonChoco.contar(new Chocolatina("Milka")));

        System.out.println("Eliminado: " + 
            cajonChoco.delete(new Chocolatina("Ferrero")));

        System.out.println(cajonChoco);
        //Ejercicio7
        }
	}
