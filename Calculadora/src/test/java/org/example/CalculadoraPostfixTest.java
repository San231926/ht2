package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadoraPostfixTest {

    private CalculadoraPostfix calculadora;
    private IStack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack<>(100);  // Usamos una pila con capacidad de 100
        calculadora = new CalculadoraPostfix(stack);
    }

    @Test
    public void testEvaluateAdd() throws Exception {
        String expression = "3 4 +";
        int resultado = calculadora.evaluate(expression);
        assertEquals(7, resultado);
    }

    @Test
    public void testEvaluateSubtract() throws Exception {
        String expression = "10 4 -";
        int resultado = calculadora.evaluate(expression);
        assertEquals(6, resultado);
    }

    @Test
    public void testEvaluateMultiply() throws Exception {
        String expression = "2 3 *";
        int resultado = calculadora.evaluate(expression);
        assertEquals(6, resultado);
    }

    @Test
    public void testEvaluateDivide() throws Exception {
        String expression = "10 2 /";
        int resultado = calculadora.evaluate(expression);
        assertEquals(5, resultado);
    }

    @Test
    public void testEvaluateModulus() throws Exception {
        String expression = "10 3 %";
        int resultado = calculadora.evaluate(expression);
        assertEquals(1, resultado);
    }

    @Test
    public void testEvaluateDivideByZero() {
        String expression = "10 0 /";
        assertThrows(ArithmeticException.class, () -> calculadora.evaluate(expression));
    }

    @Test
    public void testEvaluateMalformedExpression() {
        String expression = "3 +";
        assertThrows(IllegalArgumentException.class, () -> calculadora.evaluate(expression));
    }

    @Test
    public void testEvaluateEmptyExpression() {
        String expression = "";
        assertThrows(IllegalArgumentException.class, () -> calculadora.evaluate(expression));
    }

    @Test
    public void testEvaluateInvalidOperator() {
        String expression = "3 4 &";
        assertThrows(IllegalArgumentException.class, () -> calculadora.evaluate(expression));
    }
}