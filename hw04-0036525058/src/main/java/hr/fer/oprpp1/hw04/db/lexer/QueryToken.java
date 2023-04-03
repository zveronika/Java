package hr.fer.oprpp1.hw04.db.lexer;

/**
 * Class {@code QueryToken} represents an meaningful part of input text. It has
 * its type, {@code QueryTokenType}, defined by rules of {@code LexerState},
 * also a given value to contain.
 * 
 * @author Veronika Žunar
 *
 */

public class QueryToken {

	/**
	 * Variable {@code type}, type {@code QueryTokenType}, defines the type of this
	 * QueryToken.
	 * 
	 */
	private QueryTokenType type;

	/**
	 * Variable {@code value}, type {@code Object}, represents data this QueryToken
	 * stores.
	 * 
	 */
	private Object value;

	/**
	 * Default constructor that gets two parameters - {@code type} and {@code value}
	 * and creates a new instance of QueryToken.
	 * 
	 * @param type  assigned type of QueryToken
	 * @param value to be stored
	 * 
	 */
	public QueryToken(QueryTokenType type, Object value) {
		this.type = type;
		this.value = value;
	}

	/**
	 * This method returns type of this QueryToken.
	 * 
	 * @return the type of QueryToken
	 */
	public QueryTokenType getType() {
		return this.type;
	}

	/**
	 * This method returns value stored in this QueryToken.
	 * 
	 * @return the value of QueryToken
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * Override implementation of method {@code toString()}. Using this method
	 * output text is formated as it is assigned.
	 */
	@Override
	public String toString() {
		return " " + getValue() + " ";
	}

}
