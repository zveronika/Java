package hr.fer.oprpp1.hw04.db;

/**
 * Interface {@link IFilter} defines a method accepts that checks if conditional
 * expression from query satisfies note in student record.
 * 
 * @author Veronika Žunar
 *
 */

public interface IFilter {

	/**
	 * This method checks if given student record satisfies filter condition.
	 * 
	 * @param record student record to be checked
	 * @return true if conditional expressions form query satisfy record
	 */
	boolean accepts(StudentRecord record);

}
