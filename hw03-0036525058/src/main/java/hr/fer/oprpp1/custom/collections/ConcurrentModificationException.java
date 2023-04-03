package hr.fer.oprpp1.custom.collections;

/**
 * Custom exception thrown when a change on a collection is made during
 * iteration through same collection. {@code ConcurrentModificationException}
 * inherits from {@code RuntimeException}.
 * 
 * @author Veronika Žunar
 */
public class ConcurrentModificationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ConcurrentModificationException default constructor. Constructs a new exception with
	 * {@code null} as its detail message.
	 */
	public ConcurrentModificationException() {
		super();
	}

	/**
	 * ConcurrentModificationException with message constructor. Constructs a new exception with
	 * the specified detail message.
	 * 
	 * @param errMsg message to be printed
	 */
	public ConcurrentModificationException(String errMsg) {
		super(errMsg);
	}
}
