package hr.fer.oprpp1.hw04.db.lexer;

/**
 * Custom exception thrown when there is an mistake in performance of {@link QueryLexer}.
 * {@code QueryLexerException} inherits from {@code RuntimeException}.
 * 
 * @author Veronika Žunar
 *
 */
public class QueryLexerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * QueryLexerException default constructor. Constructs a new exception with
	 * {@code null} as its detail message.
	 */
	public QueryLexerException() {
		super();
	}

	/**
	 * QueryLexerException with message constructor. Constructs a new exception with the
	 * specified detail message.
	 * 
	 * @param errMsg message to be printed
	 */
	public QueryLexerException(String message) {
		super(message);
	}

}
