package hr.fer.oprpp1.custom.collections;

/**
 * Interface {@code Tester} is used to model objects that check if given object
 * meet the tester's condition.
 * 
 * @author Veronika Žunar
 *
 */

public interface Tester {

	/**
	 * Method test returns true if given {@code object} satisfies the criteria set
	 * by the tester, returns false otherwise. Every class implementing
	 * {@code Tester} must override this method to work properly.
	 * 
	 * @param object to be tested
	 */
	public boolean test(Object object);

}
