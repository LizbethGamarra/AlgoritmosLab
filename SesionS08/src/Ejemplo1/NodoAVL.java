package Ejemplo1;

public class NodoAVL {

    int codigo;
    int altura;

    NodoAVL izquierda;
    NodoAVL derecha;

    public NodoAVL(int codigo) {

        this.codigo = codigo;
        this.altura = 1;
    }
}