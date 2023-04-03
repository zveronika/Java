package hr.fer.oprpp1.custom.collections;
/**
 * Custom exception thrown when operation is not able to perform due empty
 * stack. {@code EmptyStackException} inherits from {@code RuntimeException}.
 *
 * @author Veronika Žunar
 *
 */
public class EmptyStackException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * EmptyStackException default constructor. Constructs a new exception with
     * {@code null} as its detail message.
     */
    public EmptyStackException() {
        super();
    }

    /**
     * EmptyStackException with message constructor. Constructs a new exception with
     * the specified detail message.
     *
     * @param errMsg message to be printed
     */
    public EmptyStackException(String errMsg) {
        super(errMsg);
    }
}