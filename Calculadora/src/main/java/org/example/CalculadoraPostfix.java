import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class CalculadoraPostfix implements ICalculadora {
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

// junitx d
class CalculadoraPostfixTest {
    private CalculadoraPostfix calculadora;
    private IStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new StackArray<>(); 
        calculadora = new CalculadoraPostfix(stack);
    }

    @Test
    void testSuma() throws Exception {
        assertEquals(7, calculadora.evaluate("3 4 +"));
    }

    @Test
    void testResta() throws Exception {
        assertEquals(1, calculadora.evaluate("5 4 -"));
    }

    @Test
    void testMultiplicacion() throws Exception {
        assertEquals(20, calculadora.evaluate("5 4 *"));
    }

    @Test
    void testDivision() throws Exception {
        assertEquals(2, calculadora.evaluate("8 4 /"));
    }

    @Test
    void testModulo() throws Exception {
        assertEquals(1, calculadora.evaluate("9 4 %"));
    }

    @Test
    void testModuloPorCero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculadora.evaluate("8 0 %");
        });
        assertEquals("División por cero", exception.getMessage());
    }

    @Test
    void testDivisionPorCero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculadora.evaluate("8 0 /");
        });
        assertEquals("División por cero", exception.getMessage());
    }

    @Test
    void testExpresionMalFormateada() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.evaluate("5 +");
        });
        assertEquals("Expresión mal formateada", exception.getMessage());
    }
}
