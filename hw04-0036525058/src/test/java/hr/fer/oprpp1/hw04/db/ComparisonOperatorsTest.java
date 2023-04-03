package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ComparisonOperatorsTest
{
    @Test
    public void lessTest() {
        IComparisonOperator less = ComparisonOperators.LESS;
        assertTrue(less.satisfied("Ana", "Jasna"));
    }

    @Test
    public void lessOrEqualsTest() {
        IComparisonOperator lessOrEquals = ComparisonOperators.LESS_OR_EQUALS;
        assertTrue(lessOrEquals.satisfied("Ana", "Jana"));
        assertTrue(lessOrEquals.satisfied("Jana", "Jana"));
        assertTrue(lessOrEquals.satisfied("Jana", "Jasna"));
    }

    @Test
    public void greaterTest() {
        IComparisonOperator greater = ComparisonOperators.GREATER;
        assertFalse(greater.satisfied("Ana", "Jasna"));
    }

    @Test
    public void greaterOrEqualsTest() {
        IComparisonOperator greaterOrEquals = ComparisonOperators.GREATER_OR_EQUALS;
        assertFalse(greaterOrEquals.satisfied("Ana", "Jana"));
        assertTrue(greaterOrEquals.satisfied("Jana", "Jana"));
        assertFalse(greaterOrEquals.satisfied("Jana", "Jasna"));
    }

    @Test
    public void equalsTest() {
        IComparisonOperator equals = ComparisonOperators.EQUALS;
        assertTrue(equals.satisfied("JEDAN", "JEDAN"));
        assertFalse(equals.satisfied("JEDAN", "jedan"));
    }

    @Test
    public void notEqualsTest() {
        IComparisonOperator notEquals = ComparisonOperators.NOT_EQUALS;
        assertTrue(notEquals.satisfied("JEDAN", "jedan"));
        assertFalse(notEquals.satisfied("JEDAN", "JEDAN"));
    }

    @Test
    public void likeTest() {
        IComparisonOperator like = ComparisonOperators.LIKE;
        assertTrue(like.satisfied("Jasna", "Ja*"));
        assertTrue(like.satisfied("Jana", "Ja*"));
        assertTrue(like.satisfied("Ana","*na"));
        assertTrue(like.satisfied("Jana","*na"));
        assertTrue(like.satisfied("Jasna", "J*a"));
        assertTrue(like.satisfied("Jana", "J*a"));

        assertFalse(like.satisfied("Zagreb", "Aba*"));
        assertFalse(like.satisfied("AAA", "AA*AA"));
        assertTrue(like.satisfied("AAAA", "AA*AA"));
    }

}
