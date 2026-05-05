package Ejercicio2;


public class TestCola {
    public static void main(String[] args) {

        ColaArreglo cola = new ColaArreglo(5);

        // Encolar 101–105
        cola.encolar(101);
        cola.encolar(102);
        cola.encolar(103);
        cola.encolar(104);
        cola.encolar(105);

        // Intentar encolar 106
        cola.encolar(106);

        // Desencolar 2
        System.out.println("Atendiendo cliente: " + cola.desencolar());
        System.out.println("Atendiendo cliente: " + cola.desencolar());

        // Mostrar frente
        System.out.println("Cliente en frente: " + cola.frente());

        // Encolar 106 y 107 (circular)
        cola.encolar(106);
        cola.encolar(107);

        // Desencolar todo
        while (!cola.isEmpty()) {
            System.out.println("Atendiendo cliente: " + cola.desencolar());
        }

        // Intentar desencolar otra vez
        cola.desencolar();
    }
}