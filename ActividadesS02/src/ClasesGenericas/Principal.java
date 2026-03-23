package ClasesGenericas;
import java.util.*;

public class Principal {
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
        for (Golosina g : bolsaGol) {
            System.out.println(g.getNombre() + " - " + g.getPeso() + "kg");
        }
	 }
}