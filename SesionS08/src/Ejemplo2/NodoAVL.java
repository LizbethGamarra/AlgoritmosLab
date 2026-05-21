package Ejemplo2;

public class NodoAVL {

    int turno;
    int altura;

    NodoAVL izquierda;
    NodoAVL derecha;

    public NodoAVL(int turno) {

        this.turno = turno;
        this.altura = 1;
    }
}