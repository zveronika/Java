package hr.fer.oprpp1.hw04.db.lexer;

/**
 * Enumeration that specifies type of {@code QueryToken}. Possible types are -
 * END, FIELD, COMPARISON_OP, LOGICAL_OP and VALUE.
 * 
 * @author Veronika Žunar
 *
 */
public enum QueryTokenType {
	/**
	 * QueryToken that represents field in student record. In this task it can be
	 * lastName, firstName, jmbag or finalGrade.
	 */
	FIELD,
	/**
	 * QueryToken that stand between field and value. It can be ">", ">=", "<",
	 * "<=", "=", "!=" or "LIKE".
	 */
	COMPARISON_OP,
	/**
	 * QueryToken that represents logical operator "AND". In this task, other
	 * logical operators are not used.
	 */
	LOGICAL_OP,
	/**
	 * A QueryToken that is surrounded by character '"'. It represents the value
	 * assigned to the field.
	 */
	VALUE,
	/**
	 * Generated at the end of query.
	 */
	END
}
