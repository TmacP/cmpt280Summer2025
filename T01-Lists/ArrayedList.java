// *** Exercise 4 ***


/**
 * ArrayedList is a generic, array-based implementation of the ListADT interface.
 * It stores elements in a fixed-size array and supports basic list operations.
 *
 * @param <I> The type of elements stored in the list.
 *
 * Exercise 4: Implement the ArrayedList class skeleton and constructor.
 */
public class ArrayedList<I> implements ListADT<I> {

    /**
     * The array that holds the elements of the list.
     * Because Java does not allow generic array creation, we use a workaround with Object[].
     */
    protected I[] listElements;

    /**
     * The index one past the last element in the list (i.e., the size of the list).
     */
    protected int listTail;

    /**
     * The maximum number of elements the list can hold.
     */
    protected int capacity;

    /**
     * Constructor: creates an empty list with the given capacity.
     *
     * Exercise 4: Implement the constructor and array allocation.
     *
     * @param capacity the maximum number of elements the list can hold
     */
    public ArrayedList(int capacity) {
        this.capacity = capacity;
        // Workaround for an array of a generic type. Causes a warning that can be suppressed.
        this.listElements = (I[]) new Object[capacity];

        // This does not work in Java:
        // this.listElements = new I[capacity];
    }

    /**
     * Checks if the list is empty (contains no elements).
     *
     * Exercise 5: Implement isEmpty().
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return listTail == 0;
    }

    /**
     * Checks if the list is full (cannot accept more elements).
     *
     * Exercise 5: Implement isFull().
     *
     * @return true if the list is full, false otherwise
     */
    public boolean isFull() {
        return listTail == capacity;
    }

    /**
     * Inserts an item at the beginning of the list.
     * Shifts all existing elements one position to the right.
     *
     * Exercise 5: Implement insertFirst().
     *
     * @param x the item to insert
     * @throws RuntimeException if the list is full
     */
    public void insertFirst(I x) {
        if(this.isFull())
            throw new RuntimeException("Error: Cannot insert an item into a full list.");

        // Shift all elements one position to the right to make space at index 0
        for(int i=listTail; i > 0; i--)
            this.listElements[i] = this.listElements[i-1];
        this.listElements[0] = x;
        this.listTail = this.listTail + 1;
    }

    /**
     * Deletes the first item from the list.
     * Shifts all remaining elements one position to the left.
     *
     * Exercise 6: Implement deleteFirst().
     *
     * @throws RuntimeException if the list is empty
     */
    public void deleteFirst()  {
        if(isEmpty())
            throw new RuntimeException("Error: cannot delete an item from an empty list.");

        // Shift all elements one position to the left to fill the gap at index 0
        for(int i =0; i < this.listTail-1; i++) {
            this.listElements[i] = this.listElements[i+1];
        }
        this.listTail--;
    }

    /**
     * Returns the first item in the list without removing it.
     *
     * Exercise 7: Implement firstItem().
     *
     * @return the first item in the list
     * @throws RuntimeException if the list is empty
     */
    public I firstItem()  {
        if(this.isEmpty()) throw new RuntimeException("Error: cannot obtain an item from an empty list.");

        return this.listElements[0];
    }

}