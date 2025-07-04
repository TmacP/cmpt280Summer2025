/**
 * LinkedList is a generic, singly-linked list implementation of the ListADT interface.
 * It uses LinkedNode objects to store elements and supports basic list operations.
 *
 * @param <I> The type of elements stored in the list.
 *
 * Exercise 1: Implement the LinkedList class skeleton, fields, and constructor.
 */
public class LinkedList<I> implements ListADT<I> {

   /**
	* First node in the list, or null if the list is empty.
	*
	* Exercise 1: Add the head field.
	*/
   protected LinkedNode<I> head;
   
   /**
	* Number of elements in the list.
	*
	* Exercise 1: Add the numEl field.
	*/
   protected int numEl;
   
   /**
	* Constructor: creates an empty linked list.
	*
	* Exercise 1: Implement the constructor.
	*/
   public LinkedList() {
	   this.head = null;
	   this.numEl = 0;
   }

   /**
	* Checks if the list is empty (contains no elements).
	*
	* Exercise 2: Implement isEmpty().
	*
	* @return true if the list is empty, false otherwise
	*/
   public boolean isEmpty() {
	   return this.head == null;
   }

   /**
	* Checks if the list is full (cannot accept more elements).
	* For a linked list, this is always false (unless out of memory).
	*
	* Exercise 2: Implement isFull().
	*
	* @return false (linked lists are never full)
	*/
   public boolean isFull() { return false; }

   /**
	* Inserts an item at the beginning of the list.
	* Creates a new node and makes it the new head.
	*
	* Exercise 2: Implement insertFirst().
	*
	* @param x the item to insert
	*/
   public void insertFirst(I x)  {
	   LinkedNode<I> newItem = new LinkedNode<I>(x);
	   newItem.setNextNode(this.head);              
	   this.head = newItem;
	   this.numEl++;
   }

   /**
	* Deletes the first item from the list.
	* Unlinks the head node and updates the head reference.
	*
	* Exercise 2: Implement deleteFirst().
	*
	* @throws RuntimeException if the list is empty
	*/
   public void deleteFirst() {
	   if( this.isEmpty() ) throw new RuntimeException("Error: Cannot delete an item from an empty list.");
			   
	   // Unlink the first node.
	   LinkedNode<I> oldhead = this.head;
	   this.head = this.head.nextNode();
	   oldhead.setNextNode(null);
	   this.numEl--;
   }

   /**
	* Returns the first item in the list without removing it.
	*
	* Exercise 2: Implement firstItem().
	*
	* @return the first item in the list
	* @throws RuntimeException if the list is empty
	*/
   public I firstItem()  {
	   if( this.isEmpty() ) throw new RuntimeException("Error: firstItem() cannot get element from an empty list.");
	   
	   // Return the first item.
	   return this.head.item();
   }

}
