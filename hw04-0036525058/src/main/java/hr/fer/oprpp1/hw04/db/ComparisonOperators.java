package hr.fer.oprpp1.hw04.db;

import java.util.Objects;

/**
 * Class {@link ComparisonOperators} offers public static final variables
 * (constants) of type {@link IComparisonOperator}: LESS, LESS_OR_EQUALS,
 * GREATER, GREATER_OR_EQUALS, EQUALS, NOT_EQUALS and LIKE. Mentioned constants
 * implements method {@code satisfied} form interface
 * {@link IComparisonOperator} using lambda expressions.
 * 
 * 
 * @author Veronika Žunar
 *
 */
public class ComparisonOperators {

	/**
	 * The less operator on strings does a lexicographical comparison. This compares
	 * strings in the same way that they would be listed in dictionary order.
	 */
	public static final IComparisonOperator LESS = (value1, value2) -> {
		Objects.requireNonNull(value1);
		Objects.requireNonNull(value2);
		return value1.compareTo(value2) < 0;
	};

	/**
	 * Same as less operator, but also can check if strings are equal.
	 */
	public static final IComparisonOperator LESS_OR_EQUALS = (value1, value2) -> {
		Objects.requireNonNull(value1);
		Objects.requireNonNull(value2);
		return value1.compareTo(value2) <= 0;
	};

	/**
	 * The greater operator on strings does a reverse lexicographical comparison.
	 * This compares strings in the reversed way that they would be listed in
	 * dictionary.
	 */
	public static final IComparisonOperator GREATER = (value1, value2) -> {
		Objects.requireNonNull(value1);
		Objects.requireNonNull(value2);
		return value1.compareTo(value2) > 0;
	};

	/**
	 * Same as grater operator, but also can check if strings are equal.
	 */
	public static final IComparisonOperator GREATER_OR_EQUALS = (value1, value2) -> {
		Objects.requireNonNull(value1);
		Objects.requireNonNull(value2);
		return value1.compareTo(value2) >= 0;
	};

	/**
	 * The equals operator compares the two given strings based on the data/content
	 * of the string. If all the contents of both the strings are same then it
	 * returns true. If any character does not match, then it returns false.
	 */
	public static final IComparisonOperator EQUALS = (value1, value2) -> {
		Objects.requireNonNull(value1);
		Objects.requireNonNull(value2);
		return value1.compareTo(value2) == 0;
	};

	/**
	 * Opposite of equals operator. The not equals operator compares the two given
	 * strings based on the data/content of the string. If any character does not
	 * match, then it returns true.
	 */
	public static final IComparisonOperator NOT_EQUALS = (value1, value2) -> {
		Objects.requireNonNull(value1);
		Objects.requireNonNull(value2);
		return value1.compareTo(value2) != 0;
	};

	/**
	 * LIKE is a string operator that compares character by character. Use the like
	 * operator is to compare a string to a regular expression. The regular
	 * expressions available with like include wildcard and escape characters.
	 * 
	 * When LIKE operator is used, string literal can contain a wildcard * (other
	 * comparisons don't support this and treat * as regular character). This
	 * character, if present, can occur at most once, but it can be at the
	 * beginning, at the end or somewhere in the middle). If user enters more
	 * wildcard characters, throw an exception (and catch it where appropriate and
	 * write error message to user; don't terminate the program).
	 * 
	 */
	public static final IComparisonOperator LIKE = (value1, value2) -> {
		Objects.requireNonNull(value1);
		Objects.requireNonNull(value2);
		try {
			if (value2.indexOf("*") != value2.lastIndexOf("*")) {
				throw new IllegalArgumentException("Charachter \"*\" appears more than once !");
			}
		} catch (IllegalArgumentException ex) {
			System.err.println(ex.getMessage());
			return false;
		}

		int index = value2.indexOf("*");
		int len1 = value1.length();
		int len2 = value2.length();

		if (len1 >= len2 - 1) {
			if (index == -1) {
				return EQUALS.satisfied(value1, value2);
			}
			if (index == 0) {
				return value1.endsWith(value2.substring(1));
			}
			if (index == value2.length() - 1) {
				return value1.startsWith(value2.substring(0, len2 - 1));
			}

			String firstPart = value2.substring(0, index);
			String secondPart = value2.substring(index + 1);

			if (value1.startsWith(firstPart) && value1.endsWith(secondPart))
				return true;
		}
		return false;
	};
}
