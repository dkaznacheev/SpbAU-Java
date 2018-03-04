package ru.spbau.dkaznacheev.calculator;

/**
 * Abstract immutable token that is stored in calculation stack.
 * Can be either a number or an operator.
 */
public abstract class CalcToken {

    /**
     * Whether the token is an operation.
     */
    private final boolean isOperation;

    protected CalcToken(boolean isOperation) {
        this.isOperation = isOperation;
    }

    /**
     * Returns if the token is an operation.
     * @return true if the token is an operation, false if number.
     */
    public boolean isOperation() {
        return isOperation;
    }
}
