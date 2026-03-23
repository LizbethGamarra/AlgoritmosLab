package Ejercicios;

public class Golosina {
	private String nombre;
	private double peso;

	public Golosina(String nombre, double peso) {
		this.nombre = nombre;
		this.peso = peso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPeso() {
		return this.peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	//Parte del Ejercicio 1 
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;

	    Golosina g = (Golosina) obj;

	    return this.nombre.equals(g.nombre) && this.peso == g.peso;
	}
	//Parte del Ejercicio 1 
	@Override
	public String toString() {
	    return nombre + " - " + peso;
	}
}