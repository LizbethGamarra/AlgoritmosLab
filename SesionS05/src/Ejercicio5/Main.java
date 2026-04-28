package Ejercicio5;

public class Main {
    public static void main(String[] args) {

        ListLinked<Integer> lista1 = new ListLinked<>();
        lista1.insertar(1);
        lista1.insertar(2);
        lista1.insertar(3);

        ListLinked<Integer> lista2 = new ListLinked<>();
        lista2.insertar(1);
        lista2.insertar(2);
        lista2.insertar(3);

        boolean resultado = OperacionesLista.sonIguales(lista1, lista2);

        System.out.println("¿Las listas son iguales? " + resultado);
    }
}