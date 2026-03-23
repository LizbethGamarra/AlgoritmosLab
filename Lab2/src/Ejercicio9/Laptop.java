package Ejercicio9;

public class Laptop implements Cargable {
    private String marca;
    private double consumoVatios;
    private int bateria;

    public Laptop(String marca, double consumoVatios) {
        this.marca = marca;
        this.consumoVatios = consumoVatios;
        this.bateria = 60;
    }

    public double getConsumoVatios() {
        return consumoVatios;
    }

    public int getNivelBateria() {
        return bateria;
    }

    public void cargar(int cantidad) {
        bateria += cantidad;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Laptop l = (Laptop) obj;
        return this.marca.equals(l.marca);
    }
}