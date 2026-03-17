package Ejercicio1;

public class Rectangulo {

    private Coordenada esquina1;
    private Coordenada esquina2;

    public Rectangulo(Coordenada c1, Coordenada c2) {
        setEsquina1(c1);
        setEsquina2(c2);
    }

    public void setEsquina1(Coordenada c) {
        this.esquina1 = new Coordenada(c);
    }

    public void setEsquina2(Coordenada c) {
        this.esquina2 = new Coordenada(c);
    }

    public Coordenada getEsquina1() {
        return esquina1;
    }

    public Coordenada getEsquina2() {
        return esquina2;
    }

    public double calculoArea() {
        double base = Math.abs(esquina1.getX() - esquina2.getX());
        double altura = Math.abs(esquina1.getY() - esquina2.getY());
        return base * altura;
    }

    public String toString() {
        return "(" + esquina1.toString() + ", " + esquina2.toString() + ")";
    }
}