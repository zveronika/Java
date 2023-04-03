package hr.fer.oprpp1.custom.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class {@link SimpleHashtable} defines a simple hash table of entries that
 * consists of pairs key and value.
 * 
 * @author Veronika Žunar
 *
 * @param <K> type of the key of entry in hash table
 * @param <V> type of the value under the key
 */

public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K, V>> {

	/**
	 * Variable {@code table}, type array of parameterized entries, represents an
	 * array of object references. It's length determines its current capacity
	 * (obviously, at any time size can not be greater than table length).
	 * 
	 */
	private TableEntry<K, V>[] table;

	/**
	 * Variable {@code size}, type int, represents number of elements actually
	 * stored in {@code elements} array. In other words, it represents current size
	 * of collection.
	 * 
	 */
	private int size;

	/**
	 * Variable {@code modificationCount}, type int, represents number of
	 * modifications of elements in the collection during the iteration.
	 * 
	 */
	private long modificationCount;

	/**
	 * Public constant that defines allowed percentage of fullness for elements
	 * table.
	 */
	public static final double LIMIT_PERCENT = 0.75;

	/**
	 * Default constructor. Creates an instance of {@code SimpleHashtable} with
	 * capacity set to 16, which also means that constructor should preallocate the
	 * elements table of that size.
	 */
	public static final int DEFAULT = 16;

	public SimpleHashtable() {
		this(DEFAULT);
	}

	/**
	 * Constructor which creates an instance of {@code SimpleHashtable} with the
	 * capacity set to value of a single integer parameter: {@code initialCapacity}.
	 * 
	 * @param initialCapacity initial capacity
	 * @throws IllegalArgumentException if {@code initialCapacity} is less then 1
	 */
	@SuppressWarnings("unchecked")
	public SimpleHashtable(int initialCapacity) {
		if (initialCapacity < 1)
			throw new IllegalArgumentException();

		int setCapacity;
		for (setCapacity = 1; setCapacity < initialCapacity; setCapacity *= 2)
			;

		this.table = (TableEntry<K, V>[]) new TableEntry[setCapacity];
		this.size = 0;
		this.modificationCount = 0;
	}

	/**
	 * Inner parameterized class that defines pair of to values (key and value) in
	 * the hash table. It also defines a pointer to the next entry in table.
	 * 
	 * @author Veronika Žunar
	 *
	 * @param <K> type of the key of the entry
	 * @param <V> type of the value of the entry
	 */
	public static class TableEntry<K, V> {
		/**
		 * Assigned key to the entry (pair of key and value).
		 */
		private K key;
		/**
		 * Value stored under given key in entry (pair of key and value).
		 */
		private V value;
		/**
		 * Variable {@code next}, type {@code TableEntry} is a reference to the next
		 * node.
		 */
		private TableEntry<K, V> next;

		/**
		 * Constructor which creates an instance of {@code TableEntry} with {@code key}
		 * set to key of entry, and assigned {@code value} to that key.
		 *
		 * @param key
		 * @param value
		 */
		public TableEntry(K key, V value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}

		/**
		 * Public getter for key.
		 * 
		 * @return key of entry
		 */
		public K getKey() {
			return this.key;
		}

		/**
		 * Public setter for value. This method set variable {@code value} to given
		 * value of parameter
		 * 
		 * @param value to be set
		 */
		public void setValue(V value) {
			this.value = value;
		}

		/**
		 * Public getter for value.
		 * 
		 * @return value of entry
		 */
		public V getValue() {
			return this.value;
		}

		/**
		 * Override implementation of method {@code toString()}. Using this method
		 * output text is formated as it is assigned.
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(key).append("=").append(value);

			return sb.toString();
		}

	}

	/**
	 * Inserts given entry at the slot determined using method slot. If given key
	 * already exists in table, value is overwritten. Otherwise new empty slot is
	 * found.
	 * 
	 * @param key   of entry
	 * @param value assigned to given key
	 * @return null if new entry is added, otherwise returns previous value if key
	 *         already existed
	 */
	public V put(K key, V value) {
		if (key == null)
			throw new NullPointerException();

		TableEntry<K, V> entry = table[slot(key)];
		if (entry == null) {
			this.table[slot(key)] = new TableEntry<>(key, value);
			size++;
			modificationCount++;
			checkFullness();
			return null;

		}
		for (; entry.next != null || entry.key.equals(key); entry = entry.next) {
			V retValue;

			if (entry.key.equals(key)) {
				retValue = entry.value;
				entry.value = value;
				modificationCount++;
				return retValue;
			}
		}

		entry.next = new TableEntry<K, V>(key, value);
		size++;
		modificationCount++;
		checkFullness();
		return null;
	}

	/**
	 * This method checks if table's fullness is bigger than assigned percent.
	 * Method checks if size is less than percent multiplied by table length.
	 */
	@SuppressWarnings("unchecked")
	private void checkFullness() {
		if (this.size >= LIMIT_PERCENT * this.table.length) {
			TableEntry<K, V>[] toArray = this.toArray();
			this.table = (TableEntry<K, V>[]) new TableEntry[2 * this.table.length];
			this.size = 0;
			for (TableEntry<K, V> e : toArray)
				this.put(e.key, e.value);

			this.modificationCount++;
		}
	}

	/**
	 * This helping method determines position in table where given entry should be
	 * put. Method uses hashCode of given key to calculate index.
	 * 
	 * @param key of the entry to be put
	 * @return calculated index in table
	 */
	public int slot(Object key) {
		return (Math.abs(key.hashCode()) % table.length);
	}

	/**
	 * Returns the value that is assigned to given key.
	 * 
	 * @param key who's value is to be reached
	 * @return value of wanted key, null otherwise
	 */
	public V get(K key) {
		if (this.containsKey(key)) {
			for (TableEntry<K, V> entry : this.table) {
				while (entry != null) {
					if (entry.key.equals(key))
						return entry.value;

					entry = entry.next;
				}
			}
		}
		return null;
	}

	/**
	 * Returns the number of currently stored entries in this table.
	 * 
	 * @return number of currently stored entries
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Returns true only if the table contains given key, as determined by equals
	 * method. Given parameter can be {@code null}.
	 * 
	 * @param key to be checked
	 * @return true only if the table contains given key
	 */
	public boolean containsKey(Object key) {
		if (key != null) {
			TableEntry<K, V> entry = this.table[slot(key)];
			while (entry != null) {
				if (entry.key.equals(key))
					return true;

				entry = entry.next;
			}
		}
		return false;
	}

	/**
	 * Returns true only if the table contains given value, as determined by equals
	 * method. Given parameter can be {@code null}.
	 * 
	 * @param value to be checked
	 * @return true only if the table contains given value
	 */
	public boolean containsValue(Object value) {
		for (TableEntry<K, V> entry : this.table) {
			while (entry != null) {
				if (entry.value.equals(value))
					return true;

				entry = entry.next;
			}
		}
		return false;
	}

	/**
	 * Returns removed value only if the table contains given value as determined by
	 * key and removes its occurrence, otherwise returns null.
	 * 
	 * @param key to value to be removed
	 * @return value only if the that value was successfully removed, null otherwise
	 */
	public V remove(Object key) {
		if (key == null || this.table[slot(key)] == null) {
			return null;
		} else {
			TableEntry<K, V> existingEntry = this.table[slot(key)];
			TableEntry<K, V> previousEntry = existingEntry;

			if (existingEntry.key.equals(key)) {
				V retValue = existingEntry.value;
				table[slot(key)] = existingEntry.next;
				size--;
				modificationCount++;
				return retValue;
			}

			existingEntry = existingEntry.next;
			while (existingEntry != null) {
				if (existingEntry.key.equals(key)) {
					V retValue = existingEntry.value;
					previousEntry.next = existingEntry.next;
					size--;
					modificationCount++;
					return retValue;
				}

				previousEntry = existingEntry;
				existingEntry = existingEntry.next;
			}
			return null;
		}
	}

	/**
	 * Returns true if table contains no entries and false otherwise.
	 * 
	 * This method is implemented to determine result by utilizing method
	 * {@code size()}.
	 * 
	 * @return true if collection contains no objects, false otherwise
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Override implementation of method {@code toString()}. Using this method
	 * output text is formated as it is assigned.
	 */
	@Override
	public String toString() {
		if (this.isEmpty())
			return "[]";

		StringBuilder sb = new StringBuilder();
		sb.append("[");

		Iterator<TableEntry<K, V>> it = iterator();
		sb.append(it.next());
		while (it.hasNext())
			sb.append(", ").append(it.next());

		return sb.append("]").toString();
	}

	/**
	 * Allocates new array with size equals to the size of this table, fills it with
	 * entries and returns the array. This method never returns null.
	 * 
	 * @return table in array form
	 */
	@SuppressWarnings("unchecked")
	public TableEntry<K, V>[] toArray() {
		int i = 0;
		TableEntry<K, V>[] toArray = new TableEntry[this.size];
		for (TableEntry<K, V> entry : this.table) {
			while (entry != null) {
				toArray[i] = entry;
				i++;
				entry = entry.next;
			}

		}
		return toArray;
	}

	/**
	 * Removes all elements from this table of entries.
	 * 
	 */
	public void clear() {
		for (int i = 0; i < this.table.length; i++)
			this.table[i] = null;

		this.size = 0;
		this.modificationCount++;
	}

	/**
	 * Creates new instance of {@link Iterator} for iteration trough
	 * {@link SimpleHashtable}.
	 */
	@Override
	public Iterator<TableEntry<K, V>> iterator() {
		return new IteratorImpl();
	}

	/**
	 * Inner parameterized class representing a hash table iterator. It defines
	 * reference to current entry in table.
	 * 
	 * @author Veronika Žunar
	 *
	 */
	private class IteratorImpl implements Iterator<TableEntry<K, V>> {

		/**
		 * Variable {@code current}, type {@code TableEntry}, represents current entry
		 * in table.
		 */
		private TableEntry<K, V> current;

		/**
		 * Variable {@code lastReturned}, type {@code TableEntry}, represents last
		 * returned entry in table.
		 */
		private TableEntry<K, V> lastReturned;

		/**
		 * Variable {@code index}, type {@code int} represents current position of entry
		 * in {@link SimpleHashtable} that is currently iterated.
		 */
		private int currentIndex;

		/**
		 * Variable {@code savedModificationCount}, type int, represents couter of saved
		 * modification on currently iterated table.
		 */
		private long savedModificationCount;

		/**
		 * Constructor that creates an instance of {@code IteratorImpl} iterator thats
		 * called by {@code SimpleHashtable}.
		 * 
		 */
		public IteratorImpl() {
			this.currentIndex = 0;
			this.savedModificationCount = modificationCount;
			nextSlot();
		}

		/**
		 * Helping method that iterates through table and finds next entry if one exist.
		 * If next entry is not found or doesn't exist, method set current entry to
		 * null.
		 */
		private void nextSlot() {
			currentIndex++;
			for (; currentIndex < table.length; currentIndex++) {
				if (table[currentIndex] != null) {
					this.current = table[currentIndex];
					return;
				}
			}
			this.current = null;
		}

		/**
		 * This method checks if entry is followed by another entry. Using this method
		 * it can be checked whether the iterator reached the end of table.
		 * 
		 * @return true if element has next one, false otherwise
		 */
		public boolean hasNext() {
			if (savedModificationCount != modificationCount)
				throw new ConcurrentModificationException();

			return (current != null);
		}

		/**
		 * This method gets the next entry of {@link SimpleHashtable}. Returning an
		 * element is successful only if the collection hasn't been modified since the
		 * creation of {@code IteratorImpl}.
		 * 
		 * @return next element of collection
		 */
		public TableEntry<K, V> next() {
			if (!this.hasNext())
				throw new NoSuchElementException();

			this.lastReturned = this.current;
			this.current = this.current.next;
			if (this.current == null)
				nextSlot();

			return lastReturned;

		}

		/**
		 * Removes last returned entry if one exists, otherwise throws
		 * {@link IllegalStateException}. If iterated table is modified since
		 * IteratorImpl was created it throws {@link ConcurrentModificationException}.
		 * 
		 * @throws ConcurrentModificationException if table is modified during iteration
		 * @throws IllegalStateException           if last returned value doesn't exist
		 *                                         or has already been removed
		 */
		public void remove() {
			if (savedModificationCount != modificationCount)
				throw new ConcurrentModificationException();

			if (lastReturned != null) {
				SimpleHashtable.this.remove(lastReturned.key);
				lastReturned = null;
			} else {
				throw new IllegalStateException();
			}
			savedModificationCount++;
		}
	}

}
