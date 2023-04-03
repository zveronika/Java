package hr.fer.oprpp1.hw04.db;

/**
 * Class {@link ConditionalExpression} represents a model that consists of three
 * parts - field value getter strategy, string literal and comparison operator
 * strategy. For example, full expression looks like :
 * 
 * " lastName LIKE "Bos*" "
 * 
 * --> new ConditionalExpression(FieldValueGetters.LAST_NAME,
 * "Bos*", ComparisonOperators.LIKE)
 * 
 * " jmbag = "0036525058" "
 --> new ConditionalExpression(FieldValueGetters.JMBAG,
 * "0036525058", ComparisonOperators.EQUALS)
 * 
 * @author Veronika Žunar
 *
 */
public class ConditionalExpression {

	/**
	 * Variable type {@link IFieldValueGetter} represents the first part of
	 * {@link ConditionalExpression} - field value getter strategy.
	 */
	private IFieldValueGetter fieldGetter;

	/**
	 * variable expression represents a reference to string literal.
	 */
	private String stringLiteral;

	/**
	 * Variable type {@link IComparisonOperator} represents the last part of
	 * {@link ConditionalExpression} - comparison operator strategy.
	 */
	private IComparisonOperator comparisonOperator;

	/**
	 * Public constructor for ConditionalExpression.
	 * 
	 * @param fieldValueGetter  IFieldValueGetter strategy
	 * @param expression                 string literal
	 * @param comparisonOperator IComparisonOperator strategy
	 */
	public ConditionalExpression(IFieldValueGetter fieldValueGetter, String expression,
			IComparisonOperator comparisonOperator) {
		this.fieldGetter = fieldValueGetter;
		this.stringLiteral = expression;
		this.comparisonOperator = comparisonOperator;
	}

	/**
	 * Public getter for field value.
	 * 
	 * @return the fieldValueGetterStrategy
	 */
	public IFieldValueGetter getFieldGetter() {
		return fieldGetter;
	}

	/**
	 * Public getter for String expression.
	 * 
	 * @return the expression
	 */
	public String getStringLiteral() {
		return stringLiteral;
	}

	/**
	 * Public getter for comparison operator.
	 * 
	 * @return the comparisonOperatorStrategy
	 */
	public IComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}

}
