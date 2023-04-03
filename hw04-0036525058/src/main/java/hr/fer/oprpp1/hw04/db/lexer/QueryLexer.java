package hr.fer.oprpp1.hw04.db.lexer;

import hr.fer.oprpp1.hw04.db.parser.QueryParserException;

import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author Veronika Žunar
 *
 */
public class QueryLexer {

    private static final String AND = "AND";
    private static final String LIKE = "LIKE";

    /**
     * Variable {@code data}, type array of chars, represents input string parted in
     * array. It represents main resource to be processed by QueryLexer.
     *
     */
    private char[] data;
    /**
     * Variable {@code currentIndex}, type int, represents index of next unprocessed
     * character.
     *
     */
    private int currentIndex;
    /**
     * Variable {@code token}, type {@code QueryToken}, represents last token that was
     * processed. Token is defined by its type and value.
     *
     */
    private QueryToken token;

    /**
     * Constructor that creates an instance of {@link QueryLexer}. Constructor excepts one parameter - text on which
     * process is to be executed.
     *
     * @param text String to be processed
     * @throws NullPointerException if given text is null
     */
    public QueryLexer(String text) {
        Objects.requireNonNull(text);
        this.data = text.trim().toCharArray();
        this.currentIndex = 0;
    }

    /**
     * Token getter. This method return last processed QueryToken.
     * QueryToken is consist of stored value and type of QueryTokenType.
     *
     * @return token
     */
    public QueryToken getToken() {
        return token;
    }

    /**
     * This method extracts next token from input string and also returns it.
     *
     * @return next token from processed text
     */
    public QueryToken nextToken() {
        if(token != null && token.getType() == QueryTokenType.END)
            throw new QueryLexerException("End of query has been reached!");
        skipEmptySpaces();
        if(currentIndex >= data.length) {
            token = new QueryToken(QueryTokenType.END, "#");
            return token;
        }
        StringBuilder sb = new StringBuilder();

        if(Character.isLetter(data[currentIndex])) {
            String word = processWord(sb);
            if (word.toUpperCase().equals("LIKE") && token != null && token.getType() == QueryTokenType.FIELD) {
                token = new QueryToken(QueryTokenType.COMPARISON_OP, word);
                return token;
            }
            if(word.toUpperCase().equals("AND") && token != null && token.getType() == QueryTokenType.VALUE) {
                token = new QueryToken(QueryTokenType.LOGICAL_OP, word);
                return token;
            }
            token = new QueryToken(QueryTokenType.FIELD, word);
            return token;
        }
        else if (data[currentIndex] == '"') {
            token = new QueryToken(QueryTokenType.VALUE, processValueString(sb));
            return token;
        }
        else if (isOperator(data[currentIndex])) {
            token = new QueryToken(QueryTokenType.COMPARISON_OP, processOperator(sb));
            return token;
        }

        throw new QueryLexerException("Invalid token passed.");
    }

    private String processWord(StringBuilder sb) {
        while (currentIndex < data.length && Character.isLetter(data[currentIndex])) {
            sb.append(data[currentIndex]);
            currentIndex++;
        }
        return sb.toString();
    }

    private String processValueString(StringBuilder sb) {
        currentIndex++;
        while (currentIndex < data.length && data[currentIndex] != '"'){
            sb.append(data[currentIndex]);
            currentIndex++;
        }
        currentIndex++;
        return sb.toString();
    }

    private String processOperator(StringBuilder sb) {
        while (currentIndex < data.length && isOperator(data[currentIndex])) {
            sb.append(data[currentIndex]);
            currentIndex++;
        }
        return sb.toString();
    }

    private boolean isOperator(char c) {
        return (data[currentIndex] == '<' || data[currentIndex] == '>'
                || data[currentIndex] == '=' || data[currentIndex] == '!');
    }

    /**
     * Using this method whitespace as ' '(space), '\r' (carriage return), '\t'
     * (tab) and '\n' (new line) is skipped so {@code Lexer} can process other chars.
     *
     */
    public void skipEmptySpaces() {
        while (currentIndex < data.length && (data[currentIndex] == '\r' || data[currentIndex] == '\n'
                || data[currentIndex] == '\t' || data[currentIndex] == ' ')) {
            currentIndex++;
        }
    }

}
