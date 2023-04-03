package hr.fer.oprpp1.hw02.prob1;

/**
 * Enumeration that specifies type of {@code Token}. Possible types are - EOF,
 * WORD, NUMBER and SYMBOL.
 * 
 * @author Veronika Žunar
 *
 */
public enum TokenType {
	/**
	 * Generated at the end of file. It is a special type that is generated in both
	 * LexerStates.
	 * 
	 */
	EOF,
	/**
	 * Represents string as if it is defined in Java. In BASIC lexerState WORD can
	 * only consist of letters, but in EXTENDED state of processing it can consist
	 * of any string characters (letters, including digits and symbols).
	 * 
	 */
	WORD,
	/**
	 * Represents number. NUMBER consist of digits and can be shown in Long format.
	 * 
	 */
	NUMBER,
	/**
	 * Represents a special type of character. In other words, this token is
	 * everything else that in not extracted as EOF, WORD or NUMBER.
	 * 
	 */
	SYMBOL;

}
