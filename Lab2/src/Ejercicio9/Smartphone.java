package Ejercicio9;
public class Smartphone implements Cargable {
    private String modelo;
    private double consumoVatios;
    private int bateria;

    public Smartphone(String modelo, double consumoVatios) {
        this.modelo = modelo;
        this.consumoVatios = consumoVatios;
        this.bateria = 50;
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

        Smartphone s = (Smartphone) obj;
        return this.modelo.equals(s.modelo);
    }
}