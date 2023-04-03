package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListIndexedCollectionTest {

	private LinkedListIndexedCollection l;

	@BeforeEach
	public void createNewLinkedListIndexedCollection() {
		l = new LinkedListIndexedCollection();
	}

	@Test
	public void defaultConstructorTest() {
		assertEquals(0, l.size());
		assertTrue(l.isEmpty());
	}

	@Test
	public void fromOtherCollectionConstructorTest() {
		l.add("Rajcica");
		l.add("Tikivca");
		l.add("Paprika");
		LinkedListIndexedCollection l2 = new LinkedListIndexedCollection(l);
		assertEquals(3, l2.size());
		assertFalse(l2.isEmpty());
	}

	@Test
	public void fromNullCollectionConstructorTest() {
		assertThrows(NullPointerException.class, () -> {
			LinkedListIndexedCollection list = new LinkedListIndexedCollection(null);
			list.add(1);
		});
	}

	@Test
	public void isEmptyTest() {
		assertTrue(l.isEmpty());
	}

	@Test
	public void sizeTest() {
		assertEquals(0, l.size());
	}

	@Test
	public void addNullTest() {
		assertThrows(NullPointerException.class, () -> l.add(null));
	}

	@Test
	public void addTest() {
		l.add(1);
		l.add(2);
		l.add(3);
		assertEquals(3, l.size());
		assertFalse(l.isEmpty());
	}

	@Test
	public void containsTest() {
		l.add("Tikvica");
		assertFalse(l.contains(null));
		assertEquals(1, l.size());
		assertTrue(l.contains("Tikvica"));
	}

	@Test
	public void removeIndexFirstTest() {
		l.add("Tikvica");
		assertEquals(1, l.size());
		l.remove(0);
		assertTrue(l.isEmpty());
	}
	
	@Test
	public void removeIndexLastTest() {
		l.add("Tikvica");
		l.add("Paprika");
		assertEquals(2, l.size());
		l.remove(1);
		assertEquals(1, l.size());
	}
	
	@Test
	public void removeIndexTest() {
		l.add("Tikvica");
		l.add("Paprika");
		l.add("Rajcica");
		assertEquals(3, l.size());
		l.remove(1);
		assertEquals(2, l.size());
	}
	
	@Test
	public void getTest() {
		l.add("Tikvica");
		l.add("Paprika");
		l.add("Rajcica");
		assertEquals("Paprika", l.get(1));
	}
	
	@Test
	public void clearTest() {
		l.add("Tikvica");
		l.add("Paprika");
		l.add("Rajcica");
		l.clear();
		assertTrue(l.isEmpty());
	}
	
	@Test
	public void addAllTest() {
		LinkedListIndexedCollection l2 = new LinkedListIndexedCollection();

		l.add("Tikvica");
		l.add("Paprika");
		l.add("Rajcica");
		
		l2.addAll(l);
		assertEquals(3, l.size());
	}
	
	@Test
	public void insertTest() {
		l.add("Tikvica");
		l.add("Paprika");
		
		l.insert("Rajcica", 1);
		
		assertEquals(3, l.size());
		assertEquals("Tikvica", l.get(0));
		assertEquals("Rajcica", l.get(1));
		assertEquals("Paprika", l.get(2));
	}
	
	@Test
	public void indexOfTest() {
		l.add("Tikvica");
		l.add("Paprika");
		l.add("Rajcica");
		assertEquals(0, l.indexOf("Tikvica"));
		assertEquals(2, l.indexOf("Rajcica"));
	}
	
//	@Test
//	public void toArrayTest() {
//		Object[] ar = {"Tikvica", "Paprika", "Rajcica"};
//		LinkedListIndexedCollection l = new LinkedListIndexedCollection();
//		l.add("Tikvica");
//		l.add("Paprika");
//		l.add("Rajcica");
//		assertEquals(ar, l.toArray());
//	}
	
}