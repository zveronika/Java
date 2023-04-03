package hr.fer.oprpp1.hw02.prob1;

/**
 * Class {@code Token} represents an meaningful part of input text. It has its
 * type, {@code TokenType}, defined by rules of {@code LexerState}, also a given
 * value to contain.
 * 
 * @author Veronika Žunar
 *
 */

public class Token {

	/**
	 * Variable {@code type}, type {@code TokenType}, defines the type of this
	 * Token.
	 * 
	 */
	private TokenType type;

	/**
	 * Variable {@code value}, type {@code Object}, represents data this Token
	 * stores.
	 * 
	 */
	private Object value;

	/**
	 * Default constructor that gets two parameters - {@code type} and {@code value}
	 * and creates a new instance of Token.
	 * 
	 * @param type assigned type of Token
	 * @param value to be stored
	 * 
	 */
	public Token(TokenType type, Object value) {
		this.type = type;
		this.value = value;
	}

	/**
	 * This method returns value stored in this Token.
	 * 
	 * @return stored value of Token
	 * 
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * This method returns type of this Token.
	 * 
	 * @return type of Token
	 * 
	 */
	public TokenType getType() {
		return this.type;
	}

	/**
	 * Override implementation of method {@code toString()}. Using this method
	 * output text is formated as it is assigned.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('(').append(type).append(", ").append(value).append(')');
		return sb.toString();
	}
}
