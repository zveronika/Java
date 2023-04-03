package hr.fer.oprpp1.hw02.prob1;

/**
 * An enumeration that defines the state of {@code Lexer} processing. There are
 * two possible states - BASIC and EXTENDED. {@code Lexer} can switch state by
 * reading char '#'.
 * 
 * @author Veronika Žunar
 *
 */

public enum LexerState {
	/**
	 * Basic way of processing. In this state of processing Lexer extract input into
	 * 3 different types - WORD, NUMBER and SYMBOL. Using escape sequences digits
	 * and symbol '/' can be extracted into WORD. If there is no escape sequence,
	 * extraction of WORD Tokens stops as the input char is digit or symbol.
	 * 
	 */
	BASIC,
	/**
	 * Extended way of processing. In this state of processing Lexer extract WORD
	 * Tokens till input is a white space or a symbol '#'. WORD Tokens can include
	 * digits. Escape sequences are not supported in this state.
	 * 
	 */
	EXTENDED;
}
