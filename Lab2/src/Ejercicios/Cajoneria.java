package Ejercicios;

import java.util.ArrayList;
import java.util.Iterator;

public class Cajoneria<T> implements Iterable<Caja<T>> {

    private ArrayList<Caja<T>> lista = new ArrayList<>();
    private int tope;

    public Cajoneria(int tope) {
        this.tope = tope;
    }

    public void add(Caja<T> caja) {
        if (lista.size() < tope) {
            lista.add(caja);
        } else {
            throw new RuntimeException("No caben más cajas");
        }
    }

    public Iterator<Caja<T>> iterator() {
        return lista.iterator();
    }
    //Ejercicio4
    public String search(T elemento) {
        for (int i = 0; i < lista.size(); i++) {
            Caja<T> caja = lista.get(i);

            if (caja.getContenido().equals(elemento)) {
                return "Posición: " + i + ", Color: " + caja.getColor();
            }
        }
        return "Elemento no encontrado";
    }
    //Ejercicio4
    public T delete(T elemento) {
        for (int i = 0; i < lista.size(); i++) {
            Caja<T> caja = lista.get(i);

            if (caja.getContenido().equals(elemento)) {
                T eliminado = caja.getContenido();
                lista.remove(i);
                return eliminado;
            }
        }
        return null;
    }
    //Ejercicio4
    @Override
    public String toString() {
        String resultado = "";

        for (int i = 0; i < lista.size(); i++) {
            Caja<T> caja = lista.get(i);

            resultado += "Posicion: " + i +
                         " | Color de caja: " + caja.getColor() +
                         " | Objeto: " + caja.getContenido() + "\n";
        }

        return resultado;
    }
    //Ejercicio4
    //Ejercicio6
    public int contar(T elemento) {
        int contador = 0;

        for (Caja<T> caja : lista) {
            if (caja.getContenido().equals(elemento)) {
                contador++;
            }
        }

        return contador;
    }
    //Ejercicio6
}