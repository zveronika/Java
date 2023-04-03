package hr.fer.oprpp1.hw04.db;

import hr.fer.oprpp1.hw04.db.lexer.*;
import hr.fer.oprpp1.hw04.db.parser.QueryParserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QueryParser {

    private QueryLexer lexer;

    private List<ConditionalExpression> conditions;

    public QueryParser(String query) {
        Objects.requireNonNull(query);
        this.lexer = new QueryLexer(query);
        this.conditions = new ArrayList<>();
        parse();
    }

    public List<ConditionalExpression> getQuery() {
        return conditions;
    }

    private void parse() {
        QueryToken token = lexer.nextToken();
        if(token.getType() == QueryTokenType.END)
            throw new QueryParserException("Query is empty!");

        while(token != null && token.getType() != QueryTokenType.END) {
            if(token.getType() != QueryTokenType.FIELD)
                throw new QueryParserException("First argument in a expression must be a field value.");
            IFieldValueGetter fieldValue = detectFieldType(token);

            token = lexer.nextToken();
            if(token.getType() != QueryTokenType.COMPARISON_OP) {
                endOfQuery(token);
                throw new QueryParserException("An operator must follow a field value in expression.");
            }
            IComparisonOperator operator = detectComparisonOperatorType(token);

            token = lexer.nextToken();
            if(token.getType()!= QueryTokenType.VALUE) {
                endOfQuery(token);
                throw new QueryParserException("Second argument in a query expression must be string value.");
            }
            String stringLiteral = detectStringLiteral(token, operator);

            conditions.add(new ConditionalExpression(fieldValue, stringLiteral, operator));
            token = lexer.nextToken();
            if(token.getType() == QueryTokenType.LOGICAL_OP) {
                token = lexer.nextToken();
                if(token.getType() == QueryTokenType.END) {
                    throw new QueryParserException("Unexpected end of query after logical operator.");
                }
            } else if(token.getType() != QueryTokenType.END)
                throw new QueryParserException("Unexpected token after string literal:" + token.getValue());
        }
    }

    public boolean isDirectQuery() {
        if(conditions.size()==1) {
            ConditionalExpression ce = conditions.get(0);
            return(ce.getFieldGetter().equals(FieldValueGetters.JMBAG) &&
                    ce.getComparisonOperator().equals(ComparisonOperators.EQUALS));
        }
        return false;
    }

    public String getQueriedJMBAG() {
        if (!isDirectQuery())
            throw new IllegalStateException();
        return conditions.get(0).getStringLiteral();
    }

    private IFieldValueGetter detectFieldType(QueryToken token) {
        String value = (String) token.getValue();
        if (value.toUpperCase().equals("LASTNAME"))
            return FieldValueGetters.LAST_NAME;
        if (value.toUpperCase().equals("FIRSTNAME"))
            return FieldValueGetters.FIRST_NAME;
        if (value.toUpperCase().equals("JMBAG"))
            return FieldValueGetters.JMBAG;

        throw new IllegalArgumentException("Token type of field was expected.");
    }

    private IComparisonOperator detectComparisonOperatorType(QueryToken token) {
        String value = (String) token.getValue();
        if (value.equals("<"))
            return ComparisonOperators.LESS;
        if (value.equals("<="))
            return ComparisonOperators.LESS_OR_EQUALS;
        if (value.equals(">"))
            return ComparisonOperators.GREATER;
        if (value.equals(">="))
            return ComparisonOperators.GREATER_OR_EQUALS;
        if (value.equals("="))
            return ComparisonOperators.EQUALS;
        if (value.equals("!="))
            return ComparisonOperators.NOT_EQUALS;
        if (value.toUpperCase().equals("LIKE"))
            return ComparisonOperators.LIKE;

        throw new IllegalArgumentException("Token type of comparison operator was expected.");
    }

    private String detectStringLiteral(QueryToken token, IComparisonOperator operator) {
        String literal = (String) token.getValue();
        if(operator.equals(ComparisonOperators.LIKE)) {
            if(literal.indexOf("*") != literal.lastIndexOf("*")) {
                throw new QueryParserException("There is more than one '*' in string literal!");
            }
        }
        return literal;
    }


    private void endOfQuery(QueryToken token) {
        if(token.getType() == QueryTokenType.END) {
            throw new QueryParserException("Unexpected end of query!");
        }
    }

}
