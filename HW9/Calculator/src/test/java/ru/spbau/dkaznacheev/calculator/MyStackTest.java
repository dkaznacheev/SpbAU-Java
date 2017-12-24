package ru.spbau.dkaznacheev.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyStackTest {
    @Test
    public void emptyStackTest() throws Exception {
        MyStack<String> stack = new MyStack<>();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void simpleAddTest() throws Exception {
        MyStack<String> stack = new MyStack<>();
        stack.push("1");
        assertFalse(stack.isEmpty());
        assertEquals("1", stack.peek());
    }

    @Test
    public void multipleAddTest() throws Exception {
        MyStack<String> stack = new MyStack<>();
        stack.push("1");
        assertEquals("1", stack.peek());
        stack.push("2");
        assertEquals("2", stack.peek());
        stack.push("3");
        assertEquals("3", stack.peek());
    }

    @Test
    public void popTest() throws Exception {
        MyStack<String> stack = new MyStack<>();
        stack.push("1");
        stack.push("2");
        stack.pop();
        assertEquals("1", stack.peek());
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}