package org.example;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.*;

public class AppTest {

    private CalculadoraPostfix calculadora;
    private IStack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack<>(100);
        calculadora = new CalculadoraPostfix(stack);
    }

    @Test
    public void testEvaluarExpresion() throws Exception {
        // Simula la entrada del usuario
        String expresion = "3 4 +";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Llama al método evaluarExpresion directamente
        App.evaluarExpresion(calculadora, expresion);

        String expectedOutput = "Resultado: 7\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testLeerYEvaluarArchivo() throws Exception {
        // Crea un archivo temporal para probar la lectura
        String contenidoArchivo = "3 4 +";
        File tempFile = File.createTempFile("test", ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(contenidoArchivo);
        }

        // Simula la lectura del archivo
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.leerYEvaluarArchivo(calculadora, tempFile.getAbsolutePath());

        String expectedOutput = "Expresión encontrada en archivo: 3 4 +\nResultado: 7\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testLeerYEvaluarArchivoVacío() throws Exception {
        // Crea un archivo vacío para probar
        File tempFile = File.createTempFile("emptyTest", ".txt");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.leerYEvaluarArchivo(calculadora, tempFile.getAbsolutePath());

        String expectedOutput = "El archivo está vacío.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testLeerYEvaluarArchivoNoExistente() throws Exception {
        // Archivo inexistente
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        App.leerYEvaluarArchivo(calculadora, "archivoInexistente.txt");

        String expectedOutput = "Error: El archivo 'archivoInexistente.txt' no existe.\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}