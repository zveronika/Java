package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.Tester;

/**
 * Class {@link EvenIntegerTester} overrides method {@code test(Object obj)}
 * implemented from interfaceTester. This class checks if given object is an
 * even number.
 * 
 * @author Veronika Žunar
 *
 */

class EvenIntegerTester implements Tester {

	/**
	 * This method is implemented from interface Tester.
	 * 
	 * Method test returns true if given {@code object} satisfies the criteria set
	 * by the tester, returns false otherwise. Method here is implemented to test if
	 * given object is instance of Integer, and also even.
	 * 
	 * @param object to be tested
	 */
	@Override
	public boolean test(Object obj) {
		if (!(obj instanceof Integer)) {
			return false;
		}
		Integer i = (Integer) obj;

		return (i % 2 == 0);
	}
	
}
