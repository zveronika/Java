package hr.fer.oprpp1.custom.collections;


/**
 * Class {@code Collection} represents some general collection of objects. This
 * class does not actually have any storage capabilities.
 *
 * @author Veronika Žunar
 *
 */
public class Collection {

    /**
     * A protected default constructor. Creates default instance of class
     * {@code Collection}.
     *
     */
    protected Collection() {
    }

    /**
     * Returns true if collection contains no objects and false otherwise.
     *
     * This method here is implemented to determine result by utilizing method
     * {@code size()}.
     *
     * @return true if collection contains no objects, false otherwise
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Returns the number of currently stored objects in this collection.
     *
     * Here implemented to always return 0. Every class extending {@code Collection}
     * must override this method to work properly.
     *
     * @return number of currently stored objects in this collection
     */
    public int size() {
        return 0;
    }

    /**
     * Adds the given object into this collection.
     *
     * Here implemented to do nothing. Every class extending {@code Collection} must
     * override this method to work properly.
     *
     * @param value to be added in this collection
     */
    public void add(Object value) {
    }

    /**
     * Returns true only if the collection contains given value, as determined by
     * equals method.
     *
     * Given parameter can be null. This method is here implemented to
     * always return false.Every class extending {@code Collection} must override
     * this method to work properly.
     *
     * @param value to be checked
     * @return true only if the collection contains given value
     */
    public boolean contains(Object value) {
        return false;
    }

    /**
     * Returns true only if the collection contains given value as determined by
     * equals method and removes one occurrence of it (in this class it is not
     * specified which one).
     *
     * Here implemented to always return false. Every class extending
     * {@code Collection} must override this method to work properly.
     *
     * @param value to be removed
     * @return true only if the collection contains given value and removes one
     *         occurrence of it
     */
    public boolean remove(Object value) {
        return false;
    }

    /**
     * Allocates new array with size equals to the size of this collection, fills
     * it with collection content and returns the array. This method never returns
     * null.
     *
     * Here implemented to throw {@code UnsupportedOperationException}. Every class
     * extending {@code Collection} must override this method to work properly.
     *
     * @return array of objects
     * @throws UnsupportedOperationException if this collection is null
     */
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    /**
     * Method calls processor.process(.) for each element of this collection. The
     * order in which elements will be sent is undefined in this class.
     *
     * Here implemented as an empty method. Every class extending {@code Collection}
     * must override this method to work properly.
     *
     * @param processor called for each element of this collection
     */
    public void forEach(Processor processor) {
    }

    /**
     * Method adds into the current collection all elements from the given
     * collection. This other collection remains unchanged.
     *
     * Here implemented to define a local processor class whose method process will
     * add each item into the current collection by calling method add, and then
     * call forEach on the other collection with this processor as argument.
     *
     * @param other collection whose elements are meant to be added to this
     *              collection
     */
    public void addAll(Collection other) {
        class LocalProcessor extends Processor {
            @Override
            public void process(Object value) {
                Collection.this.add(value);
            }
        }
        other.forEach(new LocalProcessor());
    }

    /**
     * Removes all elements from this collection.
     *
     * Here implemented as an empty method. Every class extending {@code Collection}
     * must override this method to work properly.
     *
     */
    public void clear() {
    }
}
