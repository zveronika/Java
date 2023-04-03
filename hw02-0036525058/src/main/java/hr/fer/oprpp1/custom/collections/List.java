package hr.fer.oprpp1.custom.collections;

/**
 * An interface that extends interface {@code Collection}.
 * 
 * @author Veronika Žunar
 *
 */
public interface List extends Collection {

	/**
	 * Returns the object that is stored in backing array at position index.
	 * 
	 * @param index position
	 */
	public Object get(int index);

	/**
	 * Inserts does not overwrite the given value at the given position in array!
	 * Method inserts value at the given position and elements at {@code position}
	 * and at greater indexes must be shifted one place toward the end, so that an
	 * empty place is created at {@code position}.
	 *
	 * @param value    of an object to be inserted
	 * @param position in array
	 */
	public void insert(Object value, int position);

	/**
	 * Searches the collection and returns the index of the first occurrence of the
	 * given value or -1 if the value is not found.
	 * 
	 * @param value of object to be searched
	 */
	public int indexOf(Object value);

	/**
	 * Removes element at specified index from collection. Element that was
	 * previously at location {@code index+1} after this operation is on location
	 * {@code index}, etc.
	 * 
	 * @param index of object to be removed
	 */
	public void remove(int index);

}
