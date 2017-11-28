package calculator;

import java.util.LinkedList;

/**
 * Custom implementation of generic stack.
 * Uses LinkedList as a base.
 * @param <T> type of values in the stack.
 */
public class MyStack<T> {

    /**
     * The stack that stores the values.
     */
    private LinkedList<T> stack;

    public MyStack() {
        stack = new LinkedList<>();
    }

    /**
     * Add value to the top of stack.
     * @param value value to add
     */
    public void push(T value) {
        stack.push(value);
    }

    /**
     * Remove the top element from the stack.
     */
    public void pop() {
        stack.pop();
    }

    /**
     * Returns the top element from the stack.
     * @return top element from the stack
     */
    public T peek() {
        return stack.peekFirst();
    }

    /**
     * Checks if the stack is empty.
     * @return true if the stack is empty
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
