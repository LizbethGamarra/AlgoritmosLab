package Ejercicios;

import Actividades.BTree;

import java.io.BufferedReader;
import java.io.FileReader;

public class Biblioteca {
    private BTree<Libro> arbol;

    public Biblioteca(int orden) {
        arbol = new BTree<>(orden);
    }

    public void agregarLibro(Libro libro) {

        System.out.println("Insertando -> " + libro);

        arbol.insert(libro);
    }

    public void eliminarLibro(String isbn) {

        Libro aux = new Libro(isbn, "", "", 0);

        arbol.remove(aux);
    }

    public void buscarLibro(String isbn) {

        Libro aux = new Libro(isbn, "", "", 0);

        arbol.search(aux);
    }

    public void mostrarLibros() {

        System.out.println("\n=== LIBROS ORDENADOS ===");

        arbol.inOrder();
    }

    public void mostrarCantidad() {

        System.out.println("\nCantidad total: "+ arbol.countKeys()
        );
    }

    public void mostrarAltura() {

        System.out.println(
                "Altura del árbol: "
                + arbol.height()
        );
    }

    public void cargarArchivo(String archivo)
            throws Exception {

        BufferedReader br =new BufferedReader(new FileReader(archivo));

        String linea;

        // Leer primera línea (orden)
        linea = br.readLine();

        System.out.println("Orden leído del archivo: "+ linea
        );

        while ((linea = br.readLine()) != null) {

            System.out.println("Leyendo: " + linea);

            String[] datos =
                    linea.split(",");

            Libro libro =
                    new Libro(
                            datos[0],
                            datos[1],
                            datos[2],
                            Integer.parseInt(datos[3])
                    );

            agregarLibro(libro);
        }

        br.close();

        System.out.println("\nCarga finalizada."
        );
    }
}