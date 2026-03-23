package Ejercicio9;

public class TestEstacion {
    public static void main(String[] args) {

        PowerStation<Smartphone> zonaCelulares = new PowerStation<>();

        Smartphone s1 = new Smartphone("iPhone 15", 20.5);
        Smartphone s2 = new Smartphone("Galaxy S24", 25.0);

        zonaCelulares.conectar(s1);
        zonaCelulares.conectar(s2);

        System.out.println("Consumo Total: " +
            zonaCelulares.calcularConsumoTotal() + "W");

        zonaCelulares.mostrarReporte();
    }
}