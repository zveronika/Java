package hr.fer.oprpp1.custom.collections;

import java.util.NoSuchElementException;

/**
 * This class represents of linked list-backed collection of objects. It
 * implements interface {@code List} and manages three private variables - size,
 * first, last.
 * 
 * @author Veronika Žunar
 *
 */

public class LinkedListIndexedCollection<T> implements List<T> {

	/**
	 * Variable {@code size}, type int, represents current size of collection. In
	 * other words, it is a number of elements actually stored - number of nodes in
	 * list.
	 */
	private int size;

	/**
	 * Variable {@code first}, type ListNode, is reference to the first node of the
	 * linked list.
	 */
	private ListNode<T> first;

	/**
	 * Variable {@code last}, type ListNode, is reference to the last node of the
	 * linked list.
	 */
	private ListNode<T> last;

	/**
	 * Variable {@code modificationCount}, type int, represents number of
	 * modifications of elements in the collection during the iteration.
	 * 
	 */
	private long modificationCount;

	/**
	 * Inner class representing a list of nodes. It defines pointers to previous and
	 * next list node and additional reference for value storage.
	 * 
	 * @author Veronika Žunar
	 *
	 */
	private static class ListNode<T> {
		/**
		 * Variable {@code data}, type Object, is a reference for value storage.
		 */
		T data;
		/**
		 * Variable {@code previous}, type Object, is a reference to the previous node.
		 */
		ListNode<T> previous;
		/**
		 * Variable {@code next}, type Object, is a reference to the next node.
		 */
		ListNode<T> next;

		/**
		 * Constructor which creates an instance of {@code ListNode} with {@code data}
		 * set to value of a single integer parameter: {@code data}.
		 * 
		 * @param data value to be stored
		 */
		public ListNode(T data) {
			this.data = data;
			this.next = null;
			this.previous = null;
		}
	}

	/**
	 * Default constructor. Creates an empty instance of
	 * {@code LinkedListIndexedCollection} whose nodes are set to {@code null}
	 * (first = last = null).
	 */
	public LinkedListIndexedCollection() {
		this.first = new ListNode<>(null);
		this.last = this.first;
		this.size = 0;
		this.modificationCount = 0;
	}

	/**
	 * Constructor. The elements of some other Collection are copied into this newly
	 * constructed collection.
	 * 
	 * @param collection a non-null reference to some other {@code Collection} which
	 *                   elements are copied
	 */
	public LinkedListIndexedCollection(Collection<? extends T> collection) {
		this.addAll(collection);
		this.modificationCount = 0;
	}

	/**
	 * Returns true if collection contains no objects and false otherwise.
	 * 
	 * This method is implemented to determine result by utilizing method
	 * {@code size()}.
	 * 
	 * @return true if collection contains no objects, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Returns the number of currently stored objects in this collections.
	 * 
	 * @return number of currently stored objects in this collection
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Adds the given object into this collection (reference is added into first
	 * empty place in the elements array).
	 * 
	 * This operation is done in O(1) complexity.
	 * 
	 * @param value to be added in this collection
	 * @throws NullPointerException if given {@code value} is {@code null}
	 */
	@Override
	public void add(T value) {
		if (value == null)
			throw new NullPointerException();

		ListNode<T> node = new ListNode<>(value);
		if (this.first == null) {
			this.first = node;
			this.last = this.first;
		} else {
			this.last.next = node;
			node.previous = this.last;
			this.last = node;
		}

		this.size++;
		this.modificationCount++;
	}

	/**
	 * Returns true only if the collection contains given value, as determined by
	 * equals method. Given parameter can be {@code null}.
	 * 
	 * @param value to be checked
	 * @return true only if the collection contains given value
	 */
	@Override
	public boolean contains(Object value) {
		ListNode<T> node = this.first;
		while (node.data != null) {
			if (node.data.equals(value)) {
				return true;
			}
			node = node.next;
		}
		return false;
	}

	/**
	 * Returns true only if the collection contains given value as determined by
	 * equals method and removes one occurrence of it (in this class it is not
	 * specified which one).
	 * 
	 * @param value to be removed
	 * @return true only if the collection contains given value as determined by
	 *         equals method and removes one occurrence of it
	 */
	@Override
	public boolean remove(Object value) {
		if (this.contains(value)) {
			int index = this.indexOf(value);
			this.remove(index);
			return true;
		}
		return false;
	}

	/**
	 * Allocates new array with size equals to the size of this collections, fills
	 * it with collection content and returns the array. This method never returns
	 * null.
	 * 
	 * @return array of objects
	 */
	@Override
	public Object[] toArray() {
		Object[] toArrays = new Object[this.size];
		ListNode<T> node = this.first;
		int i = 0;
		while (node != null) {
			toArrays[i] = (T) node.data;
			node = node.next;
			i++;
		}
		return toArrays;
	}

	/**
	 * Removes all elements from this collection.
	 */
	@Override
	public void clear() {
		this.first = null;
		this.last = null;
		this.size = 0;
		this.modificationCount++;
	}

	/**
	 * Returns the object that is stored in linked list at position index.
	 * 
	 * This method never has complexity greater than O(n/2 + 1). It searches the
	 * LinkedListIndexedCollection from either side, depending on value of
	 * {@code index}.
	 * 
	 * @param index position
	 * @return value of object at position index
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	public T get(int index) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();

		ListNode<T> node;
		if (index > (this.size / 2)) {
			node = this.last;
			for (int i = this.size - 1; i > index; i--) {
				node = node.previous;
			}
		} else {
			node = this.first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
		}
		return node.data;
	}

	/**
	 * Inserts (does not overwrite) the given value at the given position in
	 * linked-list. Elements starting from this position are shifted one position.
	 * 
	 * Operation is done in O(n/2+1) complexity.
	 * 
	 * @param value    of object to be added
	 * @param position in linked-list at which value should be added
	 * @throws IndexOutOfBoundsException if {@code index} is invalid
	 * @throws NullPointerException      if {@code value} equals {@code null}
	 */
	public void insert(T value, int position) {
		if (position < 0 || position > this.size)
			throw new IndexOutOfBoundsException();

		if (value == null)
			throw new NullPointerException();

		ListNode<T> node = new ListNode<>(value);
		if (position == 0) {
			node.next = this.first;
			this.first.previous = node;
			this.first = node;

		} else if (position == this.size) {
			node.previous = this.last;
			this.last.next = node;
			this.last = node;

		} else {
			int i = 0;
			ListNode<T> seekNode = this.first;
			while (i < position) {
				seekNode = seekNode.next;
				i++;
			}
			node.next = seekNode;
			node.previous = seekNode.previous;
			seekNode.previous = node;
			node.previous.next = node;
		}

		this.size++;
		this.modificationCount++;
	}

	/**
	 * Searches the collection and returns the index of the first occurrence of the
	 * given value or -1 if the value is not found.
	 * 
	 * This operation is done in O(n) complexity (O(n/2) average).
	 * 
	 * @param value of object to be searched
	 */
	public int indexOf(Object value) {
		int index = -1;
		ListNode<T> node = this.first;
		for (int i = 0; i < this.size; i++) {
			if (node.data.equals(value)) {
				index = i;
				break;
			}
			node = node.next;
		}
		return index;
	}

	/**
	 * Removes element at specified index from collection. Element that was
	 * previously at location {@code index+1} after this operation is on location
	 * {@code index}, etc.
	 * 
	 * This operation is done in O(n/2+1) complexity.
	 * 
	 * @param index of object to be removed
	 * @throws IndexOutOfBoundsException if {@code index} is invalid
	 */
	public void remove(int index) {
		if (index < 0 || index > this.size - 1) 
			throw new IndexOutOfBoundsException();

		if (index == 0) {
			this.first = this.first.next;
			this.first.previous = null;

		} else if (index == this.size) {
			this.last = this.last.previous;
			this.last.next = null;

		} else {
			ListNode<T> seekNode = this.first;
			int i = 0;
			while (i < index) {
				seekNode = seekNode.next;
				i++;
			}
			seekNode.previous.next = seekNode.next;
			seekNode.next.previous = seekNode.previous;
			seekNode = null;
		}
		this.size--;
		this.modificationCount++;
	}

	/**
	 * Inner parameterized class representing a list elements iterator. It defines
	 * reference to current node in the linked-list.
	 * 
	 * @author Veronika Žunar
	 *
	 */
	private static class ListElementsGetter<T> implements ElementsGetter<T> {

		/**
		 * Variable {@code node}, type {@code ListNode} represents current node in
		 * {@link LinkedListIndexedCollection} that is currently iterated.
		 */
		private ListNode<T> node;
		/**
		 * Variable {@code coll}, type {@code LinkedListIndexedCollection}, represents
		 * currently iterated collection.
		 */
		private LinkedListIndexedCollection<T> coll;
		/**
		 * Variable {@code savedModificationCount}, type int, represents couter of saved
		 * modification on currently iterated collection.
		 */
		private long savedModificationCount;

		/**
		 * Constructor that creates an instance of {@code LinkedListIndexedCollection}
		 * iterator thats called {@code ListElementsGetter}. It takes one parameter -
		 * {@code LinkedListIndexedCollection collection} whose elements are about to be
		 * iterated.
		 * 
		 * @param collection to be iterated
		 */
		public ListElementsGetter(LinkedListIndexedCollection<T> collection) {
			this.coll = collection;
			this.node = collection.first;
			this.savedModificationCount = collection.modificationCount;
		}

		/**
		 * This method checks if element is followed by another element. Using this
		 * method it can be checked whether the iterator reached the end of linked-list.
		 * 
		 * @return true if element has next one, false otherwise
		 */
		@Override
		public boolean hasNextElement() {
			if (savedModificationCount != coll.modificationCount) 
				throw new ConcurrentModificationException();
			
			return this.node.next != null;
		}

		/**
		 * This method gets the next element of array collection. Returning an element
		 * is successful only if the collection hasn't been modified since the creation
		 * of {@code ListElementsGetter}.
		 * 
		 * @return next element of collection
		 */
		@Override
		public T getNextElement() {
			T value;
			if (this.hasNextElement()) {
				value = this.node.next.data;
				this.node = this.node.next;
			} else {
				throw new NoSuchElementException();
			}
			return value;
		}
	}

	/**
	 * Creates new instance of ElementsGetter for iteration trough linked-list
	 * collection.
	 */
	@Override
	public ElementsGetter<T> createElementsGetter() {
		return new ListElementsGetter<>(this);
	}

}
