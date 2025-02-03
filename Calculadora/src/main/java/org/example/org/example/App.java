package org.example;

import java.io.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IStack<Integer> stack = new Stack<>(100); // Definimos capacidad 100 para la pila
        CalculadoraPostfix calculadora = new CalculadoraPostfix(stack);

        while (true) {
            System.out.println("\n--- CALCULADORA POSTFIX ---");
            System.out.println("1. Ingresar expresión manualmente");
            System.out.println("2. Leer expresión desde archivo (datos.txt)");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Ingrese la expresión en notación postfix: ");
                    String inputExpression = scanner.nextLine();
                    evaluarExpresion(calculadora, inputExpression);
                    break;
                case "2":
                    leerYEvaluarArchivo(calculadora, "datos.txt");
                    break;
                case "3":
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // Método protegido para evaluación de expresiones
    protected static void evaluarExpresion(CalculadoraPostfix calculadora, String expresion) {
        try {
            int resultado = calculadora.evaluate(expresion);
            System.out.println("Resultado: " + resultado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Método protegido para leer y evaluar expresiones desde un archivo
    protected static void leerYEvaluarArchivo(CalculadoraPostfix calculadora, String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String expresion = reader.readLine();
            if (expresion != null) {
                System.out.println("Expresión encontrada en archivo: " + expresion);
                evaluarExpresion(calculadora, expresion);
            } else {
                System.out.println("El archivo está vacío.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: El archivo '" + nombreArchivo + "' no existe.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }
    }
}