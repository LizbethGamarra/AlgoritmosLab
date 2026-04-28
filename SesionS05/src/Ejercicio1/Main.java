package Ejercicio1;

public class Main {
    public static void main(String[] args) {

        ListLinked<Integer> lista = new ListLinked<>();

        lista.add(10);
        lista.add(20);
        lista.add(30);

        System.out.println(Utilidades.buscarElemento(lista, 20)); // true
        System.out.println(Utilidades.buscarElemento(lista, 50)); // false
    }
}