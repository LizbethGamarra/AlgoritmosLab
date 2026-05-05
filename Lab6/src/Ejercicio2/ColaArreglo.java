package Ejercicio2;


public class ColaArreglo {

    private int[] cola;
    private int frente;
    private int fin;
    private int tamaño;
    private int capacidad;

    public ColaArreglo(int capacidad) {
        this.capacidad = capacidad;
        cola = new int[capacidad];
        frente = 0;
        fin = -1;
        tamaño = 0;
    }

    public boolean isEmpty() {
        return tamaño == 0;
    }

    public boolean isFull() {
        return tamaño == capacidad;
    }

    public void encolar(int x) {
        if (isFull()) {
            System.out.println("Cola llena");
            return;
        }
        fin = (fin + 1) % capacidad;
        cola[fin] = x;
        tamaño++;
    }

    public int desencolar() {
        if (isEmpty()) {
            System.out.println("Cola vacía");
            return -1;
        }
        int dato = cola[frente];
        frente = (frente + 1) % capacidad;
        tamaño--;
        return dato;
    }

    public int frente() {
        if (isEmpty()) {
            System.out.println("Cola vacía");
            return -1;
        }
        return cola[frente];
    }
}