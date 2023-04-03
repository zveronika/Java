package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CollectionTest {

	Collection coll = new Collection();
	
	@Test
	public void collectionIsEmpty() {
		assertTrue(coll.isEmpty());
	}
	
	@Test
	public void collectionSizeTest() {
		assertEquals(0, coll.size());
	}
	
	@Test
	public void collectionAddTest() {
		coll.add((Integer) 1);
		assertEquals(0, coll.size());
	}
	
	@Test
	public void collectionContainsTest() {
		assertFalse(coll.contains((Integer) 1));
	}
	
	@Test
	public void collectionRemoveTest() {
		assertFalse(coll.remove((Integer) 1));
	}
	
	@Test
	public void collectionToArrayTest() {
		assertThrows(UnsupportedOperationException.class, () -> coll.toArray());
	}
	
	@Test
	public void collectionForEach() {
		
	}
	
	@Test
	public void collectionAddAll() {
		Collection coll2 = new Collection();
		coll.addAll(coll2);
	}
	
	@Test
	public void collectionClear() {
		coll.clear();
	}
}
