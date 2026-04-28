package Ejercicio8;

public class Main {
    public static void main(String[] args) {

        ColaReproduccion<Cancion> cola = new ColaReproduccion<>();

        cola.agregarCancion(new Cancion("Bohemian Rhapsody", "Queen", 354));
        cola.agregarCancion(new Cancion("Blinding Lights", "The Weeknd", 200));
        cola.agregarCancion(new Cancion("Shape of You", "Ed Sheeran", 234));
        cola.agregarCancion(new Cancion("Levitating", "Dua Lipa", 203));
        cola.agregarCancion(new Cancion("Bad Guy", "Billie Eilish", 194));
        cola.agregarCancion(new Cancion("Someone Like You", "Adele", 285));

        // Mostrar inicial
        System.out.println("=== Cola de Reproducción Inicial ===");
        cola.mostrarCola();

        // avanzar
        cola.reproducirSiguiente();
        System.out.println("\n► Reproduciendo ahora: " + cola.getActual());

        cola.reproducirSiguiente();
        System.out.println("► Reproduciendo ahora: " + cola.getActual());

        cola.reproducirSiguiente();
        System.out.println("► Reproduciendo ahora: " + cola.getActual());

        // retroceder
        cola.reproducirAnterior();
        System.out.println("\n◄ Anterior: " + cola.getActual());

        // mezclar
        System.out.println("\n=== Mezclando... ===");
        cola.mezclar();
        cola.mostrarCola();

        // duración total
        int totalSeg = cola.duracionTotal();
        int min = totalSeg / 60;
        int seg = totalSeg % 60;

        System.out.println("\nDuración total: " + min + ":" + (seg < 10 ? "0" + seg : seg));
    }
}