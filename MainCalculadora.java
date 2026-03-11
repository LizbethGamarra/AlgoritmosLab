package EjercicioParticipacion;

import java.util.Scanner;

public class MainCalculadora {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculadora calc = new Calculadora();

        try {
            System.out.print("Ingrese el primer número: ");
            double num1 = sc.nextDouble();

            System.out.print("Ingrese el segundo número: ");
            double num2 = sc.nextDouble();

            System.out.println("Seleccione operación: ");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            int opcion = sc.nextInt();

            double resultado = 0;

            switch (opcion) {
                case 1:
                    resultado = calc.sumar(num1, num2);
                    break;
                case 2:
                    resultado = calc.restar(num1, num2);
                    break;
                case 3:
                    resultado = calc.multiplicar(num1, num2);
                    break;
                case 4:
                    resultado = calc.dividir(num1, num2);
                    break;
                default:
                    throw new IllegalArgumentException("Opción no válida.");
            }

            System.out.println("Resultado: " + resultado);

        } catch (DivisionPorCeroException e) {
            System.out.println("Excepción personalizada capturada: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}