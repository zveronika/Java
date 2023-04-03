package hr.fer.oprpp1.hw04.db.parser;

import hr.fer.oprpp1.hw04.db.QueryParser;

/**
 * Custom exception thrown when there is an mistake in performance of {@link QueryParser}.
 * {@code QueryParserException} inherits from {@code RuntimeException}.
 * 
 * @author Veronika Žunar
 *
 */
public class QueryParserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * {@link QueryParserException} default constructor. Constructs a new exception with
	 * {@code null} as its detail message.
	 */
	public QueryParserException() {
		super();
	}

	/**
	 * {@link QueryParserException} with message constructor. Constructs a new exception with the
	 * specified detail message.
	 * 
	 * @param errMsg message to be printed
	 */
	public QueryParserException(String errMsg) {
		super(errMsg);
	}

}
