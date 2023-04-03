package hr.fer.oprpp1.custom.collections;

import java.util.Objects;

/**
 * Stack-like collection of elements.
 *
 * The goal that {@code ObjectStack} should provide for it users appropriate
 * interface but at the same time avoid code duplication will be accomplished by
 * using delegation.
 *
 * @author Veronika Žunar
 *
 */


public class ObjectStack {
    /**
     * Private variable type {@code ArrayIndexedCollection} represents a collection
     * which is used for actual element storage.
     */
    private ArrayIndexedCollection arrayIndexedCollection;

    /**
     * Default constructor that creates a new empty instance of {@code ObjectStack}.
     */
    public ObjectStack() {
        this.arrayIndexedCollection = new ArrayIndexedCollection();
    }

    /**
     * Returns true if collection contains no objects and false otherwise.
     *
     * This method implements its functionality by calling (i.e. delegating) method
     * {@code isEmpty()} of its internal collection of type ArrayIndexedCollection.
     *
     * @return true if collection contains no objects, false otherwise
     */
    public boolean isEmpty() {
        return this.arrayIndexedCollection.isEmpty();
    }

    /**
     * Returns the number of currently stored objects in this collections.
     *
     * This method implements its functionality by calling (i.e. delegating) method
     * {@code size()} of its internal collection of type ArrayIndexedCollection.
     *
     * @return number of currently stored objects in this collection
     */
    public int size() {
        return this.arrayIndexedCollection.size();
    }

    /**
     * Pushes given value on the stack.
     *
     * This method implements its functionality by calling (i.e. delegating) method
     * {@code add()} of its internal collection of type ArrayIndexedCollection.
     *
     * This operation is done in O(1) complexity.
     *
     * @param value to be added on stack
     * @throws NullPointerException if {@code value} equals {@code null}
     */
    public void push(Object value) {
        Objects.requireNonNull(value);
        this.arrayIndexedCollection.add(value);
    }

    /**
     * Removes last value pushed on stack from stack and returns it.
     *
     * This method implements its functionality by calling (i.e. delegating) method
     * {@code get()} and {@code remove()} of its internal collection of type
     * ArrayIndexedCollection.
     *
     * This operation is done in O(1) complexity.
     *
     * @return last value from stack
     * @throws EmptyStackException if stack is empty when method pop is called
     */
    public Object pop() {
        if (this.arrayIndexedCollection.isEmpty())
            throw new EmptyStackException();

        Object value = this.peek();
        this.arrayIndexedCollection.remove(this.size() - 1);
        return value;
    }

    /**
     * Returns last element placed on stack but does not delete it from stack.
     *
     * This method implements its functionality by calling (i.e. delegating) method
     * {@code get()} of its internal collection of type ArrayIndexedCollection.
     *
     * This operation is done in O(1) complexity.
     *
     * @return last value from stack
     * @throws EmptyStackException if stack is empty when method peek is called
     */
    public Object peek() throws EmptyStackException {
        if (this.arrayIndexedCollection.isEmpty())
            throw new EmptyStackException();

        return this.arrayIndexedCollection.get(this.size() - 1);
    }

    /**
     * Removes all elements from this collection.
     *
     * This method implements its functionality by calling (i.e. delegating) method
     * {@code clear()} of its internal collection of type ArrayIndexedCollection.
     */
    public void clear() {
        this.arrayIndexedCollection.clear();
    }
}
