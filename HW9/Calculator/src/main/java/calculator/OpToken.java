package calculator;

/**
 * Operation immutable token that is stored in calculation stack, for example, *+-/()
 * Is represented by one character.
 */
public class OpToken extends CalcToken {

    /**
     * The character corresponding to the operator.
     */
    private char operation;

    public OpToken(char operation) {
        super(true);
        this.operation = operation;
    }

    /**
     * Returns the character of this operation.
     * @return the character of this operation.
     */
    public char getOperation() {
        return operation;
    }
}
