package org.example;
import java.util.*;

public class CalculadoraPostfix implements ICalculator {
    private IStack<Integer> stack;

    public CalculadoraPostfix(IStack<Integer> stack) {
        this.stack = stack;
    }

    @Override
    public int evaluate(String expression) throws Exception {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("La expresión no puede estar vacía");
        }
        String[] tokens = expression.split(" ");
        for (String token : tokens) {
            if (isNumeric(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Expresión mal formateada");
                }
                int b = stack.pop();
                int a = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        if (b == 0) {
                            throw new ArithmeticException("División por cero");
                        }
                        stack.push(a / b);
                        break;
                    case "%":
                        if (b == 0) {
                            throw new ArithmeticException("División por cero");
                        }
                        stack.push(a % b);
                        break;
                    default:
                        throw new IllegalArgumentException("Operador desconocido: " + token);
                }
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Expresión mal formateada");
        }
        return stack.pop();
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}