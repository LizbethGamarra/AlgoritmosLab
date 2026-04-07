package Actividades;

public class actividad3 {

    public static double potencia(double x, int y) {

        // La función potencia(x, y) calcula x^y de manera recursiva.

        // Caso 1: Si y = 0, retorna 1 → caso base
        // Complejidad: O(1)
        if (y == 0) {
            return 1.0; // O(1)
        }

        // Caso 2: Si y es impar
        // Se llama recursivamente con (y - 1)
        // Luego se multiplica por x
        // Recurrencia: T(y) = T(y - 1) + O(1)
        if (y % 2 == 1) {
            return x * potencia(x, y - 1); // T(y-1) + O(1)
        }

        // Caso 3: Si y es par
        // Se llama recursivamente con (y / 2)
        // Luego se multiplica por sí mismo
        // Recurrencia: T(y) = T(y / 2) + O(1)
        else {
            double t = potencia(x, y / 2); // T(y/2) + O(1)
            return t * t; // O(1)
        }
    }

    /*
    Se observa que:

    • En el caso de y = 0:
      La complejidad es O(1)

    • En el caso de y impar:
      La complejidad es T(y - 1)
      porque se realiza una llamada recursiva con y - 1

    • En el caso de y par:
      La complejidad es T(y / 2)
      porque la llamada recursiva se realiza con y / 2

    EXPANSIÓN DE RECURRENCIAS

    Caso 1: y impar

    T(y) = T(y - 1) + O(1)
    T(y-1) = T(y - 2) + O(1)
    T(y-2) = T(y - 3) + O(1)
    ...
    Se continúa hasta llegar a T(0)

    Resultado:
    T(y) = O(y)

    Caso 2: y par

    T(y) = T(y / 2) + O(1)
    T(y/2) = T(y / 4) + O(1)
    T(y/4) = T(y / 8) + O(1)
    ...
    Se continúa hasta llegar a T(1)

    Resultado:
    T(y) = O(log y)

    */
}