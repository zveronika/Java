package hr.fer.oprpp1.hw04.db;

/**
 * Custom exception thrown when there is unsatisfied condition in {@link StudentDatabase}.
 * {@code StudentDatabaseException} inherits from {@code RuntimeException}.
 * 
 * @author Veronika Žunar
 *
 */

public class StudentDatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * {@link StudentDatabaseException} default constructor. Constructs a new exception with
	 * {@code null} as its detail message.
	 */
	public StudentDatabaseException() {
		super();
	}

	/**
	 * {@link StudentDatabaseException} with message constructor. Constructs a new exception with the
	 * specified detail message.
	 * 
	 * @param errMsg message to be printed
	 */
	public StudentDatabaseException(String errMsg) {
		super(errMsg);
	}
	
	

}
