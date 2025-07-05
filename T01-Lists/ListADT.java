// Exercise 8

/**
 * ListADT is a generic interface that defines the basic operations for a list data structure.
 * This interface uses a type parameter I, so it can be used for lists of any object type.
 *
 * By defining this interface, we can write code that works with any kind of list (e.g., array-based or linked),
 * as long as that list implements these methods. This is useful for abstraction and code reuse.
 */
public interface ListADT<I> {
    /**
     * Insert an item at the beginning of the list.
     * @param x the item to insert
     */
    void insertFirst(I x);

    /**
     * Remove the first item from the list.
     * If the list is empty, this should throw an exception.
     */
    void deleteFirst();

    /**
     * Get (but do not remove) the first item in the list.
     * If the list is empty, this should throw an exception.
     * @return the first item in the list
     */
    I firstItem();

    /**
     * Check if the list is empty (contains no items).
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Check if the list is full (cannot accept more items).
     * For array-based lists, this means the array is at capacity.
     * For linked lists, this usually always returns false.
     * @return true if the list is full, false otherwise
     */
    boolean isFull();
}
