package hr.fer.oprpp1.hw02.prob1;

/**
 * Class {@link Lexer} represents an lexer for input string. It tokenizes its parts 
 * @author Veronika Žunar
 *
 */

public class Lexer {

	/**
	 * Variable {@code data}, type array of chars, represents input string parted in
	 * array. It represents main resource to be processed by Lexer.
	 * 
	 */
	private char[] data;

	/**
	 * Variable {@code token}, type {@code Token}, represents last token that was
	 * processed. Token is defined by its type and value.
	 * 
	 */
	private Token token;

	/**
	 * Variable {@code index}, type int, represents index of next unprocessed
	 * character.
	 * 
	 */
	private int currentIndex;

	/**
	 * Variable {@code state}, type {@code LexerState}, represents the state of
	 * Lexer processing. BASIC is default. Different states have different approach
	 * to processing.
	 */
	private LexerState state;

//	private static final char[] EMPTY_SPACES = {'\t', '\r', '\n', ' '};

	/**
	 * Constructor that creates an instance of Lexer. LexerState of processing by
	 * default is set to BASIC. Constructor excepts one parameter - text on which
	 * process is to be executed.
	 * 
	 * @param text String to be processed
	 * @throws NullPointerException if given text is null
	 */
	public Lexer(String text) {
		if (text == null)
			throw new NullPointerException();
		
		this.data = text.toCharArray();
		this.currentIndex = 0;
		this.state = LexerState.BASIC;
	}

	/**
	 * This method return last processed Token. Token is consist of stored value and
	 * type of Token.
	 * 
	 * @return token
	 */
	public Token getToken() {
		return this.token;
	}

	/**
	 * Using this method, state of Lexer processing can be set. Rules of processing
	 * for different states can be found in definition of enumeration
	 * {@code LexerState}.
	 * 
	 * @param state value of state to be set
	 * @throws NullPointerException if guven state is null
	 */
	public void setState(LexerState state) {
		if(state == null) 
			throw new NullPointerException();
		
		this.state = state;
	}

	/**
	 * This method extracts next token from input string and also returns it.
	 * Depending on LexerState there are variations of processing Tokens.
	 * 
	 * @return next token from processed text
	 */
	public Token nextToken() {

		StringBuilder sb = new StringBuilder();

		if (this.token != null && this.token.getType() == TokenType.EOF) {
			throw new LexerException("Already got Token whos type if EOF.");
		}

		if (currentIndex >= data.length) {
			this.token = new Token(TokenType.EOF, null);
			return this.token;
		}

		if (this.state.equals(LexerState.EXTENDED)) {
			
			skipEmptySpaces();

			if (currentIndex >= data.length) {
				this.token = new Token(TokenType.EOF, null);
				return this.token;

			}
			if (data[currentIndex] == '#') {
				setState(LexerState.BASIC);
				this.token = new Token(TokenType.SYMBOL, Character.valueOf('#'));
				currentIndex++;
				return this.token;
			}

			String word = extendedProcessWord(sb);
			this.token = new Token(TokenType.WORD, word);
			return this.token;
			
		} else { // this.state.equals(LexerState.BASIC))
			
			skipEmptySpaces();
			
			if (currentIndex >= data.length) {
				this.token = new Token(TokenType.EOF, null);
				return this.token;

			}

			if (data[currentIndex] == '#') {
				setState(LexerState.EXTENDED);
				this.token = new Token(TokenType.SYMBOL, Character.valueOf(data[currentIndex]));
				currentIndex++;
				return this.token;

			} else if (Character.isLetter(data[currentIndex]) || escapeSequence()) {
				String word = basicProcessWord(sb);
				this.token = new Token(TokenType.WORD, word);
				return this.token;

			} else if (Character.isDigit(data[currentIndex])) {
				Long number = processNumber(sb);
				this.token = new Token(TokenType.NUMBER, number);
				return this.token;

			}
			this.token = new Token(TokenType.SYMBOL, Character.valueOf(data[currentIndex]));
			currentIndex++;
			return this.token;

		}
	}

	/**
	 * Helping method that process token type WORD in EXTENDED state of Lexer.
	 * Method excepts one parameter - StringBuilder {@code sb}. Using {@code sb}
	 * method crates string that will be returned.
	 * 
	 * @param sb StringBuilder for returning string
	 * @return built string Token type WORD in EXTENDED state of processing
	 */
	public String extendedProcessWord(StringBuilder sb) {
		while (currentIndex < data.length && data[currentIndex] != '#' && !Character.isWhitespace(data[currentIndex])) {
			sb.append(data[currentIndex]);
			currentIndex++;
		}
		return sb.toString();
	}

	/**
	 * Helping method that process token type WORD in BASIC state of Lexer. Method
	 * excepts one parameter - StringBuilder {@code sb}. Using {@code sb} method
	 * crates string that will be returned.
	 * 
	 * @param sb StringBuilder for returning string
	 * @return built string Token type WORD in BASIC state of processing
	 */
	public String basicProcessWord(StringBuilder sb) {

		while (currentIndex < data.length && (Character.isLetter(data[currentIndex]) || data[currentIndex] == '\\')) {
			if (data[currentIndex] == '\\' && escapeSequence()) {
				currentIndex++;
				sb.append(data[currentIndex]);
				currentIndex++;

			} else {
				sb.append(data[currentIndex]);
				currentIndex++;
			}
		}
		return sb.toString();
	}

	/**
	 * Helping method that process token type NUMBER in BASIC state of Lexer. Method
	 * excepts one parameter - StringBuilder {@code sb}. Using {@code sb} method
	 * crates number type Long that will be returned.
	 * 
	 * @param sb StringBuilder for returning number type Long
	 * @return built string Token type NUMBER in BASIC state of processing
	 */
	public Long processNumber(StringBuilder sb) {
		while (currentIndex < data.length && Character.isDigit(data[currentIndex])) {
			sb.append(data[currentIndex]);
			currentIndex++;
		}

		try {
			return Long.parseLong(sb.toString());

		} catch (NumberFormatException e) {
			throw new LexerException();
		}
	}

	/**
	 * Helping method that detect escape sequence. Escape sequence is acceptable in
	 * two cases: if there are two backslash symbols in row as '\\' or the first
	 * backslash is followed by digit as '\[digit]'. This method is called if Lexer
	 * find backslash in input text. If escape sequence is detected this method
	 * returns true.
	 * 
	 * @return true if 'escapeSequence' is detected
	 * @throws LexerException if end of input array is reached or escape sequence is
	 *                        invalid
	 * 
	 */
	public boolean escapeSequence() {
		if (currentIndex + 1 == data.length)
			throw new LexerException();

		if (data[currentIndex] == '\\') {
			currentIndex++;
			if (!Character.isDigit(data[currentIndex]) && data[currentIndex] != '\\') {
				throw new LexerException();
			} else {
				currentIndex--;
				return true;
			}
		}
		return false;
	}

	/**
	 * Using this method whitespace as ' '(space), '\r' (carriage return), '\t'
	 * (tab) and '\n' (new line) is skipped so Lexer can process other chars.
	 * 
	 */
	public void skipEmptySpaces() {
		while (currentIndex < data.length && (data[currentIndex] == '\r' || data[currentIndex] == '\n'
				|| data[currentIndex] == '\t' || data[currentIndex] == ' ')) {

			currentIndex++;
		}
	}
}
