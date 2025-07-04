
// Demo.java
// Demonstrates usage of LinkedList, ArrayedList, and LinkedNode
//
// This file is designed to help you visualize and understand how the data structures
// you implemented work in practice. It uses ListPrinter to show the internal state
// of each structure after each operation. Follow the comments to see what each step is doing.

public class Demo {
    public static void main(String[] args) {

        // --- LinkedList Demo ---
        // This section demonstrates the LinkedList class, which is a singly-linked list.
        // We will insert and remove elements, and print the list after each operation.
        System.out.println("--- LinkedList Demo ---");
        LinkedList<Integer> linkedList = new LinkedList<>();
        // The list should be empty initially
        System.out.println("Is empty? " + linkedList.isEmpty());
        ListPrinter.printLinkedList(linkedList);
        // Insert 10 at the front
        System.out.println("Inserting 10 at the front");
        linkedList.insertFirst(10);
        ListPrinter.printLinkedList(linkedList);
        // The list should now have one element
        System.out.println("Is empty? " + linkedList.isEmpty());
        System.out.println("First item: " + linkedList.firstItem());
        // Insert 20 at the front
        System.out.println("Inserting 20 at the front");
        linkedList.insertFirst(20);
        ListPrinter.printLinkedList(linkedList);
        System.out.println("First item: " + linkedList.firstItem());
        // Insert 30 at the front
        System.out.println("Inserting 30 at the front");
        linkedList.insertFirst(30);
        ListPrinter.printLinkedList(linkedList);
        System.out.println("First item: " + linkedList.firstItem());
        // Remove the first item
        System.out.println("Deleting first item");
        linkedList.deleteFirst();
        ListPrinter.printLinkedList(linkedList);
        System.out.println("First item after delete: " + linkedList.firstItem());
        System.out.println("Notice: Deleting from LinkedList just changes the head pointer!");
        // Linked lists are never full
        System.out.println("Is full? " + linkedList.isFull());


        // --- ArrayedList Demo ---
        // This section demonstrates the ArrayedList class, which is an array-based list.
        // We will insert and remove elements, and print the array after each operation.
        System.out.println("\n--- ArrayedList Demo ---");
        ArrayedList<Integer> arrayedList = new ArrayedList<>(3);
        // The list should be empty initially
        System.out.println("Is empty? " + arrayedList.isEmpty());
        ListPrinter.printArrayedList(arrayedList);
        // Insert 100 at the front
        System.out.println("Inserting 100 at the front");
        arrayedList.insertFirst(100);
        ListPrinter.printArrayedList(arrayedList);
        // Insert 200 at the front
        System.out.println("Inserting 200 at the front");
        arrayedList.insertFirst(200);
        ListPrinter.printArrayedList(arrayedList);
        // Insert 300 at the front
        System.out.println("Inserting 300 at the front");
        arrayedList.insertFirst(300);
        ListPrinter.printArrayedList(arrayedList);
        // The list should now be full
        System.out.println("Is full? " + arrayedList.isFull());
        System.out.println("First item: " + arrayedList.firstItem());
        System.out.println("Notice: insertFirst() shifts all elements to the right!");
        // Remove the first item
        System.out.println("Deleting first item");
        arrayedList.deleteFirst();
        ListPrinter.printArrayedList(arrayedList);
        System.out.println("First item after delete: " + arrayedList.firstItem());
        System.out.println("Notice: deleteFirst() shifts all elements to the left!");
        System.out.println("Is empty? " + arrayedList.isEmpty());
        System.out.println("Is full? " + arrayedList.isFull());
        // Try to overfill the arrayed list to show exception handling
        arrayedList.insertFirst(400); // Fill it back up first
        try {
            System.out.println("Attempting to insert 400 into a full ArrayedList");
            arrayedList.insertFirst(400);
        } catch (RuntimeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        // Try to delete from empty ArrayedList to show exception handling
        ArrayedList<Integer> emptyArrayedList = new ArrayedList<>(2);
        try {
            System.out.println("Attempting to delete from empty ArrayedList");
            emptyArrayedList.deleteFirst();
        } catch (RuntimeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }


        // --- LinkedNode Demo ---
        // This section demonstrates the LinkedNode class, which is the building block for LinkedList.
        // We show how nodes can be linked and how their fields can be accessed and changed.
        System.out.println("\n--- LinkedNode Demo ---");
        System.out.println("Creating two separate nodes:");
        LinkedNode<String> node1 = new LinkedNode<>("A");
        LinkedNode<String> node2 = new LinkedNode<>("B");
        System.out.println("Node1 item: " + node1.item() + ", next: " + node1.nextNode());
        System.out.println("Node2 item: " + node2.item() + ", next: " + node2.nextNode());
        
        System.out.println("\nLinking node1 to node2:");
        node1.setNextNode(node2); // Link node1 to node2
        System.out.println("Node1 item: " + node1.item());
        System.out.println("Node2 item via node1.next: " + node1.nextNode().item());
        System.out.println("Node1's next field now points to: Node@" + 
                          Integer.toHexString(System.identityHashCode(node1.nextNode())));
        
        // Change the value in node2
        System.out.println("\nChanging node2's item to 'C':");
        node2.setItem("C");
        System.out.println("Node2 item after change: " + node1.nextNode().item());
        System.out.println("This shows that node1's next pointer still points to the same node,");
        System.out.println("but that node's content has changed.");
        
        // --- Summary ---
        System.out.println("\n=== KEY DIFFERENCES SUMMARY ===");
        System.out.println("LinkedList:");
        System.out.println("  - Uses nodes with pointers to next element");
        System.out.println("  - insertFirst() just creates new node and updates head pointer");
        System.out.println("  - deleteFirst() just updates head pointer");
        System.out.println("  - Never full (until out of memory)");
        System.out.println("  - No shifting of elements needed");
        System.out.println();
        System.out.println("ArrayedList:");
        System.out.println("  - Uses a fixed-size array with tail pointer");
        System.out.println("  - insertFirst() shifts ALL elements to the right");
        System.out.println("  - deleteFirst() shifts ALL elements to the left");
        System.out.println("  - Can become full when tail = capacity");
        System.out.println("  - Shifting makes insertFirst() and deleteFirst() slower for large lists");
    }
}
