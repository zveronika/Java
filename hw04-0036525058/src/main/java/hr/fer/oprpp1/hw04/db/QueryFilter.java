package hr.fer.oprpp1.hw04.db;

import java.util.List;

/**
 * Class {@link QueryFilter}, which implements {@link IFilter}, represents a
 * filter for queries. It has one private variable - list of
 * {@link ConditionalExpression} objects.
 * 
 * @author Veronika Žunar
 *
 */

public class QueryFilter implements IFilter {

	/**
	 * Private variable that represents a list of all ConditionalExpressions in
	 * query.
	 */
	private List<ConditionalExpression> conditionalExpressionsList;

	/**
	 * Public constructor which receives one argument: a list of
	 * ConditionalExpression objects.
	 * 
	 * @param conditionalExpressionsList
	 */
	public QueryFilter(List<ConditionalExpression> conditionalExpressionsList) {
		this.conditionalExpressionsList = conditionalExpressionsList;
	}

	/**
	 * Implementation of method accepts that is defined by {@link IFilter}
	 * interface. Method returns true if all of the conditional expressions from
	 * query are accepted, otherwise returns false.
	 * 
	 */
	@Override
	public boolean accepts(StudentRecord record) {
		for (ConditionalExpression ce : conditionalExpressionsList) {
			if (!ce.getComparisonOperator().satisfied(ce.getFieldGetter().get(record),
					ce.getStringLiteral()))
				return false;
		}
		return true;
	}
}
