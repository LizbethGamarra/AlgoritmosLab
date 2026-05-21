package Ejercicio3;

public class NodoAVL {

    int dato;
    int altura;

    NodoAVL izquierda;
    NodoAVL derecha;

    public NodoAVL(int dato) {

        this.dato = dato;
        this.altura = 1;
    }
}