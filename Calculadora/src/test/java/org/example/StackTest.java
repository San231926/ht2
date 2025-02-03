package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackTest {

    private IStack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack<>(5);  // Tamaño de la pila
    }

    @Test
    public void testPushAndPop() {
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    public void testPeek() {
        stack.push(10);
        stack.push(20);
        assertEquals(20, stack.peek());  // Verifica el último elemento sin eliminarlo
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(10);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testStackOverflow() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        assertThrows(StackOverflowError.class, () -> stack.push(6));
    }

    @Test
    public void testStackUnderflow() {
        assertThrows(IllegalStateException.class, () -> stack.pop());
    }
}