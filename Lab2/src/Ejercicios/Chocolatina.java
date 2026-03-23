package Ejercicios;

public class Chocolatina {
	private String marca;
	
	public Chocolatina(String marca) {
		this.marca = marca;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	//Parte del Ejercicio 1 
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;

	    Chocolatina c = (Chocolatina) obj;

	    return this.marca.equals(c.marca);
	}
	//Parte del Ejercicio 1 
	@Override
	public String toString() {
	    return marca;
	}
}