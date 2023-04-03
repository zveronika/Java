package hr.fer.oprpp1.custom.collections;

/**
 * Interface {@code Collection} represents some default and abstract methods for
 * general collection of objects.
 *
 * @author Veronika Žunar
 *
 */

public interface Collection {

    /**
     * Returns true if collection contains no objects and false otherwise.
     *
     * This is default method here implemented to determine result by utilizing
     * method {@code size()}.
     *
     * @return true if collection contains no objects, false otherwise
     */
    default boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Returns the number of currently stored objects in this collection. Every
     * class implementing {@code Collection} must override this method to work
     * properly.
     *
     * @return number of currently stored objects in this collection
     */
    public int size();

    /**
     * Adds the given object into this collection. Every class implementing
     * {@code Collection} must override this method to work properly.
     *
     * @param value to be added in this collection
     */
    public void add(Object value);

    /**
     * Returns true only if the collection contains given value, as determined by
     * equals method. Given parameter can be {@code null}. Every class implementing
     * {@code Collection} must override this method to work properly.
     *
     * @param value to be checked
     * @return true only if the collection contains given value
     */
    public boolean contains(Object value);

    /**
     * Returns true only if the collection contains given value as determined by
     * equals method and removes one occurrence of it (in this class it is not
     * specified which one). Every class implementing {@code Collection} must
     * override this method to work properly.
     *
     * @param value to be removed
     * @return true only if the collection contains given value as determined by
     *         equals method and removes one occurrence of it
     */
    public boolean remove(Object value);

    /**
     * Allocates new array with size equals to the size of this collections, fills
     * it with collection content and returns the array. This method never returns
     * null. Every class implementing {@code Collection} must override this method
     * to work properly.
     *
     * @return array of objects
     */
    public Object[] toArray();

    /**
     * Default method that calls processor.process(.) for each element of this
     * collection.
     *
     * @param processor called for each element of this collection
     */
    default void forEach(Processor processor) {
        ElementsGetter elemGet = this.createElementsGetter();
        while (elemGet.hasNextElement())
            processor.process(elemGet.getNextElement());
    }

    /**
     * Default method that adds all elements from the given collection into the
     * current collection. This other collection remains unchanged.
     *
     * @param other collection to be added
     */
    default void addAll(Collection other) {
        class LocalProcessor implements Processor {
            @Override
            public void process(Object value) {
                add(value);
            }
        }
        other.forEach(new LocalProcessor());
    }

    /**
     * Removes all elements from this collection. Every class implementing
     * {@code Collection} must override this method to work properly.
     */
    public void clear();

    /**
     * Creates new instance of ElementsGetter. Every class implementing
     * {@code Collection} must override this method to work properly.
     */
    public ElementsGetter createElementsGetter();

    /**
     * This method adds all acceptable elements from {@code Collection col} to this
     * collection. {@code Tester tester} checks if element is acceptable or not.
     *
     * @param col    collection whose elements are going to be added
     * @param tester checks if element is acceptable or not
     */
    default void addAllSatisfying(Collection col, Tester tester) {
        ElementsGetter elemGet = col.createElementsGetter();

        Object value;
        while (elemGet.hasNextElement()) {
            value = elemGet.getNextElement();
            if (tester.test(value))
                this.add(value);
        }
    }

}
