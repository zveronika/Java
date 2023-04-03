package hr.fer.oprpp1.hw02.prob1;

/**
 * Custom exception thrown when there is an mistake in performance of
 * {@code Lexer}. {@code LexerException} inherits from {@code RuntimeException}.
 * 
 * @author Veronika Žunar
 *
 */
public class LexerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * LexerException default constructor. Constructs a new exception with
	 * {@code null} as its detail message.
	 */
	public LexerException() {
		super();
	}

	/**
	 * LexerException with message constructor. Constructs a new exception with the
	 * specified detail message.
	 * 
	 * @param errMsg message to be printed
	 */
	public LexerException(String errMsg) {
		super(errMsg);
	}

}
