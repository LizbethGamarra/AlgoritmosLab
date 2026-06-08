package Ejercicios;

public class Main {

    public static void main(String[] args) throws Exception {

        Biblioteca b = new Biblioteca(4);

        b.cargarArchivo("C:\\Users\\Diago\\eclipse-workspace\\Lab9\\src\\biblioteca.txt");

        b.mostrarLibros();

        b.mostrarCantidad();

        b.mostrarAltura();

        b.buscarLibro("9780132350884");
    }
}