package Actividades;

public class actividad2{

    public static double potencia(double x, int y) {
        // 1.IDENTIFICACIÓN DE CASOS
        // Caso base:
        // Si y == 0 → retorna 1
        // No hay recursión
        // Complejidad: O(1)
        if (y == 0)
            return 1.0; // O(1)

        // Caso recursivo:
        // Si y es impar:
        // Llama a potencia(x, y - 1)
        // Luego multiplica por x
        // Complejidad: T(y - 1) + O(1)
        if (y % 2 == 1)
            return x * potencia(x, y - 1); // T(y-1) + O(1)
        // Si y es par:
        // Llama a potencia(x, y / 2)
        // Luego multiplica t * t
        // Complejidad: T(y / 2) + O(1)
        else {
            double t = potencia(x, y / 2); // T(y/2) + O(1)
            return t * t; // O(1)
        }
    }

    /*
    2.COSTO DE LAS OPERACIONES
    - Comparaciones (y == 0, y % 2 == 1): O(1)
    - Multiplicaciones: O(1)
    - Asignaciones: O(1)
    - Llamadas recursivas:
        • T(y - 1)  → caso impar
        • T(y / 2)  → caso par
        
    3. ECUACIÓN DE RECURRENCIA
    Caso base:
    T(0) = O(1)
    Caso impar:
    T(y) = T(y - 1) + O(1)
    Caso par:
    T(y) = T(y / 2) + O(1)

    4. EXPANSIÓN DE LA RECURRENCIA
    Caso impar:
    T(y) = T(y-1) + c
    T(y-1) = T(y-2) + c
    T(y-2) = T(y-3) + c
    ...
    T(1) = T(0) + c

    Sumando:
    T(y) = T(0) + y*c

    Resultado:
    T(y) = O(y)

    Caso par:
    T(y) = T(y/2) + c
    T(y/2) = T(y/4) + c
    T(y/4) = T(y/8) + c
    ...
    Número de pasos: log₂(y)

    Resultado:
    T(y) = O(log y)
    
    5.COMPLEJIDAD BIG-O

    Caso impar: O(y)
    Caso par: O(log y)

    6. MEJOR Y PEOR CASO
    Mejor caso:
    - Cuando y es par (se divide entre 2)
    - Complejidad: O(log y)

    Peor caso:
    - Cuando y es impar (disminuye de 1 en 1)
    - Complejidad: O(y)

    7.INTERPRETACIÓN DEL CRECIMIENTO

    - Crecimiento lineal en el peor caso:
      Se reduce lentamente → y, y-1, y-2, ...
      
    - Crecimiento logarítmico en el mejor caso:
      Se reduce rápidamente → y, y/2, y/4, ...

    - En la práctica:
      Este algoritmo es "exponenciación rápida"
      por lo que su comportamiento típico es:

      O(log y)

    */
}