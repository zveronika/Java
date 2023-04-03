package hr.fer.oprpp1.custom.collections;

/**
 * Class that defines pair of key and value. It represents parameterized class
 * which works as adapter using one variable - {@link ArrayIndexedCollection}.
 * Entries in used {@link ArrayIndexedCollection} are already mentioned pairs of
 * key (K) and value (V).
 * 
 * @author Veronika Žunar
 *
 */

public class Dictionary<K, V> {

	public ArrayIndexedCollection<Entry<K, V>> dictionary;

	private static class Entry<K, V> {
		/**
		 * 
		 */
		private K key;
		/**
		 * 
		 */
		private V value;

		/**
		 * 
		 * @param key
		 * @param value
		 */
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

	}

	/**
	 * 
	 */
	public Dictionary() {
		this.dictionary = new ArrayIndexedCollection<Entry<K, V>>();
	}

	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return this.dictionary.isEmpty();
	}

	/**
	 * 
	 * @return
	 */
	public int size() {
		return this.dictionary.size();
	}

	/**
	 * 
	 */
	public void clear() {
		this.dictionary.clear();
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public V put(K key, V value) throws NullPointerException {
		if (key == null) 
			throw new NullPointerException();
		
		ElementsGetter<Entry<K, V>> getEntry = this.dictionary.createElementsGetter();

		while (getEntry.hasNextElement()) {
			Entry<K, V> entry = (Entry<K, V>) getEntry.getNextElement();
			if (entry.key.equals(key)) {
				V oldValue = entry.value;
				entry.value = value;
				return oldValue;
			}
		}

		this.dictionary.add(new Entry<>(key, value));
		return null;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public V get(Object key) {
		if (key == null) 
			throw new NullPointerException();

		ElementsGetter<Entry<K, V>> getEntry = this.dictionary.createElementsGetter();

		while (getEntry.hasNextElement()) {
			Entry<K, V> entry = (Entry<K, V>) getEntry.getNextElement();
			if (entry.key.equals(key)) {
				V retValue = entry.value;
				return retValue;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public V remove(K key) {
		if (key == null) 
			throw new NullPointerException();
		
		ElementsGetter<Entry<K, V>> getEntry = this.dictionary.createElementsGetter();

		while (getEntry.hasNextElement()) {
			Entry<K, V> entry = (Entry<K, V>) getEntry.getNextElement();
			if (entry.key.equals(key)) {
				V retValue = entry.value;
				this.dictionary.remove(entry);
				return retValue;
			}
		}
		return null;
	}
}
