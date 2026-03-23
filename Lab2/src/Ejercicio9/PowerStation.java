package Ejercicio9;

import java.util.ArrayList;

public class PowerStation<T extends Cargable> {

    private ArrayList<T> lista = new ArrayList<>();

    public void conectar(T dispositivo) {
        lista.add(dispositivo);
    }

    public double calcularConsumoTotal() {
        double total = 0;
        for (T d : lista) {
            total += d.getConsumoVatios();
        }
        return total;
    }

    public int buscarDispositivo(T prototipo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(prototipo)) {
                return i;
            }
        }
        return -1;
    }

    public void mostrarReporte() {
        for (int i = 0; i < lista.size(); i++) {
            T d = lista.get(i);
            System.out.println("Posición: " + i +
                               " | Consumo: " + d.getConsumoVatios() + "W");
        }
    }
}