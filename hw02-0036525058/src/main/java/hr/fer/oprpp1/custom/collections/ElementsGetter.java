package hr.fer.oprpp1.custom.collections;

/**
 * An interface that iterates through collection.
 * 
 * ElementsGetter are used for returning an element at the time to the user, on
 * his request. In this interface there are defined two methods -
 * hasNextElement() and getNextElement() that are used for communication between
 * user and objects.
 * 
 * @author Veronika Žunar
 *
 */
public interface ElementsGetter {

	/**
	 * This method checks if element is followed by another element. Using this
	 * method it can be checked whether the iterator reached the end of collection.
	 * 
	 * @return true if element has next one
	 */
	public boolean hasNextElement();

	/**
	 * This method gets the next element of collection and returns it to user.
	 * 
	 * @return next element of collection
	 */
	public Object getNextElement();

	/**
	 * Default method that performs an action on remaining (not processed) elements
	 * in the collection.
	 * 
	 * @param processor which determines an action to be performed
	 */
	default void processRemaining(Processor processor) {
		while (this.hasNextElement())
			processor.process(getNextElement());
	}
	
}
