package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConditionalExpressionTest {

    public static ConditionalExpression expr1;
    public static ConditionalExpression expr2;

    @BeforeEach
    public void createConditionalExpression() {
        expr1 = new ConditionalExpression(
                FieldValueGetters.LAST_NAME,
                "Bos*",
                ComparisonOperators.LIKE
        );
        expr2 = new ConditionalExpression(
                FieldValueGetters.LAST_NAME,
                "A*",
                ComparisonOperators.LIKE
        );
    }

    @Test
    public void recordNotSatisfiesTest() {
        StudentRecord record = new StudentRecord("0000000001","Akšamović","Marin",2);
        assertFalse(expr1.getComparisonOperator().satisfied(
                expr1.getFieldGetter().get(record), // returns lastName from given record
                expr1.getStringLiteral() // returns "Bos*"
        ));
    }

    @Test
    public void recordSatisfiesTest() {
        StudentRecord record = new StudentRecord("0000000001","Akšamović","Marin",2);
        assertTrue(expr2.getComparisonOperator().satisfied(
                expr2.getFieldGetter().get(record), // returns lastName from given record
                expr2.getStringLiteral() // returns "Bos*"
        ));
    }
}
