package ejercicios;

/**
 * Ejercicio 2: Comparación de métodos de sondeo en hash cerrado.
 * Tabla de tamaño 7, h(x) = x % 7.
 * Inserta 10, 17, 24, 31, 4 con sondeo lineal y luego con sondeo cuadrático f(i) = i^2.
 */
public class Ejercicio2 {

    static final int M = 7;

    static int hash(int x) {
        return x % M;
    }

    public static void main(String[] args) {
        int[] valores = {10, 17, 24, 31, 4};

        System.out.println("########## SONDEO LINEAL ##########");
        sondeoLineal(valores);

        System.out.println("\n########## SONDEO CUADRATICO ##########");
        sondeoCuadratico(valores);

        /*
         * Pregunta 2: ¿En cuál método se producen menos saltos ante colisiones consecutivas?
         *
         * Con estos valores: 10%7=3, 17%7=3, 24%7=3, 31%7=3, 4%7=4
         * Los primeros 4 valores (10,17,24,31) colisionan TODOS en el índice 3.
         *
         * - Sondeo lineal: cada nueva inserción avanza 1 posición a la vez desde el
         *   índice base hasta encontrar un hueco, por lo que el número de saltos crece
         *   linealmente (0,1,2,3 saltos respectivamente) y tiende a generar
         *   "agrupamiento primario": los elementos quedan contiguos y bloquean cada
         *   vez más posiciones consecutivas, aumentando el costo de futuras inserciones.
         *
         * - Sondeo cuadrático: las posiciones probadas son base+0, base+1, base+4,
         *   base+9, ... por lo que los elementos se dispersan más rápido por la tabla
         *   en lugar de quedar pegados; en la práctica, para este pequeño conjunto,
         *   cuadrático necesita igual o menos intentos "efectivos" para hallar un
         *   hueco libre porque no se concentra en un solo bloque contiguo, reduciendo
         *   el agrupamiento primario (aunque puede generar agrupamiento secundario).
         *
         * En general, el sondeo cuadrático produce menos saltos "perdidos" en cadenas
         * largas de colisiones consecutivas que el sondeo lineal, porque evita que se
         * formen bloques largos de celdas ocupadas consecutivas.
         */
    }

    static void sondeoLineal(int[] valores) {
        int[] tabla = new int[M];
        boolean[] ocupado = new boolean[M];
        for (int i = 0; i < M; i++) tabla[i] = -1;

        for (int v : valores) {
            int base = hash(v);
            int idx = base;
            int saltos = 0;
            while (ocupado[idx]) {
                saltos++;
                idx = (base + saltos) % M;
            }
            tabla[idx] = v;
            ocupado[idx] = true;
            System.out.println("Insertar " + v + " -> h=" + base + ", posiciones exploradas: "
                    + (saltos + 1) + " -> insertado en " + idx);
            imprimirTabla(tabla);
        }
    }

    static void sondeoCuadratico(int[] valores) {
        int[] tabla = new int[M];
        boolean[] ocupado = new boolean[M];
        for (int i = 0; i < M; i++) tabla[i] = -1;

        for (int v : valores) {
            int base = hash(v);
            int i = 0;
            int idx = base;
            int explorados = 0;
            while (ocupado[idx]) {
                i++;
                explorados++;
                idx = (base + i * i) % M;
                if (explorados > M) { // evita ciclo infinito si no hay hueco
                    System.out.println("No se encontró espacio para " + v);
                    idx = -1;
                    break;
                }
            }
            if (idx != -1) {
                tabla[idx] = v;
                ocupado[idx] = true;
                System.out.println("Insertar " + v + " -> h=" + base + ", posiciones exploradas: "
                        + (explorados + 1) + " -> insertado en " + idx);
                imprimirTabla(tabla);
            }
        }
    }

    static void imprimirTabla(int[] tabla) {
        StringBuilder sb = new StringBuilder("  Tabla: [");
        for (int i = 0; i < tabla.length; i++) {
            sb.append(tabla[i] == -1 ? "_" : tabla[i]);
            if (i != tabla.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb);
    }
}
