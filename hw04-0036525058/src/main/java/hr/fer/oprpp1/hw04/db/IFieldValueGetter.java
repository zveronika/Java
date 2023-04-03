package hr.fer.oprpp1.hw04.db;

/**
 * Interface that defines a simple method get. Its implementation is given in
 * class {@link FieldValueGetters} and they return the value of certain fields
 * form given {@link StudentRecord}.
 * 
 * @author Veronika Žunar
 *
 */

public interface IFieldValueGetter {

	/**
	 * Method {@code get} returns the value of wanted field from given
	 * StudentRecord.
	 * 
	 * @param record which field should be get
	 * @return field from record
	 */
	String get(StudentRecord record);

}
