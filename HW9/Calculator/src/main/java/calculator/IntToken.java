package calculator;

/**
 * Integer immutable token that is stored in calculation stack.
 */
public class IntToken extends CalcToken {

    /**
     * The value of token
     */
    private int value;

    public IntToken(int value) {
        super(false);
        this.value = value;
    }

    /**
     * Returns the token's value.
     * @return the value of token
     */
    public int getValue() {
        return value;
    }
}
