package hr.fer.oprpp1.hw04.db;

/**
 * Interface {@link IComparisonOperator} represents a strategy defining one
 * boolean method - satisfied that accepts two String arguments.
 * 
 * @author Veronika Žunar
 *
 */

public interface IComparisonOperator {

	/**
	 * Definition of boolean method satisfied which arguments are two string
	 * literals (not field names). Method is implemented in class
	 * {@link ComparisonOperators}.
	 * 
	 * @param value1 first string literal
	 * @param value2 second string literal
	 * @return true if given arguments satisfy certain comparison operator
	 */
	boolean satisfied(String value1, String value2);

}
