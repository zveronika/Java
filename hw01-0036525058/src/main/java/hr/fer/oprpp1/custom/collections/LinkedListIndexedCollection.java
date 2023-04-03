package hr.fer.oprpp1.custom.collections;

import java.util.Objects;

/**
 * This class represents of linked list-backed collection of objects. It extends
 * class {@code Collection} and manages three private variables - size, first,
 * last. Duplicate elements in this collection are allowed (they are held in different
 * list node), but storage of null references are not allowed.
 *
 * @author Veronika Žunar
 *
 */


public class LinkedListIndexedCollection extends Collection {
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
    private ListNode first;
    /**
     * Variable {@code last}, type ListNode, is reference to the last node of the
     * linked list.
     */
    private ListNode last;

    /**
     * Inner class representing a list of nodes. It defines pointers to previous and
     * next list node and additional reference for value storage.
     *
     * @author Veronika Žunar
     *
     */
    private static class ListNode {
        /**
         * Variable {@code data}, type Object, is a reference for value storage.
         */
        private Object data;
        /**
         * Variable {@code data}, type Object, is a reference to the previous node.
         */
        private ListNode prev;
        /**
         * Variable {@code data}, type Object, is a reference to the next node.
         */
        private ListNode next;

        /**
         * Constructor which creates an instance of {@code ListNode} with {@code data}
         * set to value of a single integer parameter: {@code data}.
         *
         */
        public ListNode(ListNode prev, Object data, ListNode next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    /**
     * Default constructor. Creates an empty instance of
     * {@code LinkedListIndexedCollection} whose nodes are set to {@code null}
     * (first = last = null).
     */
    public LinkedListIndexedCollection() {
    }

    /**
     * Constructor. The elements of some other Collection are copied into this newly
     * constructed collection.
     *
     * @param collection a non-null reference to some other {@code Collection} which
     *                   elements are copied
     */
    public LinkedListIndexedCollection(Collection collection) {
        this();
        addAll(collection);
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
    public void add(Object value) {
        Objects.requireNonNull(value);

        ListNode l = this.last;
        ListNode node = new ListNode(this.last, value, null);
        this.last = node;
        if (l == null)
            this.first = node;
        else
            l.next = node;
        this.size++;
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
        return indexOf(value) >= 0;
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
            this.remove(this.indexOf(value));
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
        Object[] toArray = new Object[size];
        int i = 0;
        for (ListNode n = first; n != null; n = n.next)
            toArray[i++] = n.data;
        return toArray;
    }

    /**
     * Method calls processor.process(.) for each element of this collection. The
     * order in which elements will be sent is undefined in this class.
     *
     * @param processor called for each element of this collection
     */
    @Override
    public void forEach(Processor processor) {
        for (ListNode f = this.first; f != null; f = f.next)
            processor.process(f.data);
    }

    /**
     * Removes all elements from this collection.
     */
    @Override
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
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
    public Object get(int index) {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();
        return getListNode(index).data;
    }

    private ListNode getListNode(int index) {
        ListNode n;
        if (index < (this.size / 2)) {
            n = this.first;
            for (int i = 0; i < index; i++)
                n = n.next;
        } else {
            n = this.last;
            for (int i = this.size - 1; i > index; i--)
                n = n.prev;
        }
        return n;
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
    public void insert(Object value, int position) {
        Objects.requireNonNull(value);
        if (position < 0 || position > this.size)
            throw new IndexOutOfBoundsException();

        if (position == this.size) add(value);
        else {
            ListNode node = getListNode(position);
            ListNode pred = node.prev;
            ListNode newNode = new ListNode(pred, value, node);
            node.prev = newNode;
            if (pred == null)
                this.first = newNode;
            else
                pred.next = newNode;
            this.size++;
        }
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
        int i = 0;
        for(ListNode n = this.first; n != null; n = n.next, i++)
            if (n.data.equals(value)) return i;
        return -1;
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

        if (this.size == 1) {
            clear();
        } else {
            if (index == 0) {
                this.first = this.first.next;
                this.first.prev = null;

            } else if (index == this.size - 1) {
                this.last = this.last.prev;
                this.last.next = null;

            } else {
                ListNode seekNode = this.first;
                int i = 0;
                while (i < index) {
                    seekNode = seekNode.next;
                    i++;
                }
                seekNode.prev.next = seekNode.next;
                seekNode.next.prev = seekNode.prev;
            }
            this.size--;
        }
    }

    @Override
    public String toString() {
        String st = "LinkedListIndexedCollection [size=" + size + ", elements=[";
        for(int i = 0; i < size; i++) {
            if (i == size - 1) st += this.get(i).toString();
            else st += this.get(i).toString() + ", ";
        }
        return st + "]]";
    }
}
