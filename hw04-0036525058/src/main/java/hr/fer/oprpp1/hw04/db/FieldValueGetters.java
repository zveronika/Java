package hr.fer.oprpp1.hw04.db;

import java.util.Objects;

/**
 * Class {@link FieldValueGetters} offers public static final variables
 * (constants) of type {@link IFieldValueGetter}: FIRST_NAME, LAST_NAME and
 * JMBAG. Mentioned constants implements method {@code get} form interface
 * {@link IFieldValueGetter} using lambda expressions.
 * 
 * @author Veronika Žunar
 *
 */

public class FieldValueGetters {

	/**
	 * Getter that returns a value of field firstName of given student record.
	 */
	public static final IFieldValueGetter FIRST_NAME = (record) -> {
		Objects.requireNonNull(record);
		return record.getFirstName();
	};

	/**
	 * Getter that returns a value of field lastName of given student record.
	 */
	public static final IFieldValueGetter LAST_NAME = (record) -> {
		Objects.requireNonNull(record);
		return record.getLastName();
	};

	/**
	 * Getter that returns a value of field jmbag of given student record.
	 */
	public static final IFieldValueGetter JMBAG = (record) -> {
		Objects.requireNonNull(record);
		return record.getJmbag();
	};
}
