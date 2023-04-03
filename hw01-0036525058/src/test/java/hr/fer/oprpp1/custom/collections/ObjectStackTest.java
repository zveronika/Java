package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectStackTest {

	private ObjectStack stack;

	@BeforeEach
	public void createNewStack() {
		stack = new ObjectStack();
	}

	@Test
	public void sizeTest() {
		assertEquals(0, stack.size());
	}

	@Test
	public void isEmptyTest() {
		assertTrue(stack.isEmpty());
	}

	@Test
	public void pushToStackTest() {
		stack.push(1);
		stack.push(2);
		assertEquals(2, stack.size());
		assertFalse(stack.isEmpty());
	}

	@Test
	public void pushNullTest() {
		assertThrows(NullPointerException.class, () -> stack.push(null));
	}

	@Test
	public void popFromEmptyStack() {
		assertThrows(EmptyStackException.class, () -> stack.pop());
	}

	@Test
	public void popFromStackTest() {
		stack.push(1);
		stack.push(2);
		assertEquals(2, stack.pop());
		assertEquals(1, stack.size());
	}

	@Test
	public void peekFromEmptyStack() {
		assertThrows(EmptyStackException.class, () -> stack.peek());
	}

	@Test
	public void peekFromStackTest() {
		stack.push(1);
		stack.push(2);
		assertEquals(2, stack.peek());
		assertEquals(2, stack.size());
	}

	@Test
	public void clearStackTest() {
		stack.push(2);
		stack.push(1);
		assertFalse(stack.isEmpty());
		stack.clear();
		assertTrue(stack.isEmpty());
	}
}
