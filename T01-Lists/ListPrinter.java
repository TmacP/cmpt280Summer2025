// ListPrinter.java
// Utility class to pretty-print LinkedList and ArrayedList contents

public class ListPrinter {

    // Vertical ASCII art visualization for LinkedList showing node fields
    public static <I> void printLinkedList(LinkedList<I> list) {
        System.out.println();
        System.out.println("LinkedList visualization:");
        LinkedNode<I> current = list.head;
        if (current == null) {
            System.out.println("  (empty)");
            System.out.println();
            return;
        }
        while (current != null) {
            String nextStr = (current.nextNode() == null) ? "null" : "Node@" + Integer.toHexString(System.identityHashCode(current.nextNode()));
            System.out.println("+-----------------------------+");
            System.out.printf("| item: %-22s|\n", String.valueOf(current.item()));
            System.out.printf("| next: %-22s|\n", nextStr);
            System.out.println("+-----------------------------+");
            if (current.nextNode() != null) {
                System.out.println("         |");
                System.out.println("         v");
            }
            current = current.nextNode();
        }
        System.out.println();
    }

    // Vertical ASCII art visualization for ArrayedList showing array fields and tail
    public static <I> void printArrayedList(ArrayedList<I> list) {
        System.out.println();
        System.out.println("ArrayedList visualization:");
        for (int i = 0; i < list.capacity; i++) {
            System.out.println("+---------------------+");
            String val = (i < list.listTail) ? String.valueOf(list.listElements[i]) : "";
            System.out.printf("| index: %-3d value: %-6s|\n", i, val);
            System.out.println("+---------------------+");
            // Show tail pointer after the last valid element
            if (i == list.listTail) {
                System.out.println("   ^");
                System.out.println("   | (tail points here)");
            }
        }
        // If tail is at the end of the array, show it after the last slot
        if (list.listTail >= list.capacity) {
            System.out.println("   ^");
            System.out.println("   | (tail points here)");
        }
        System.out.printf("listTail = %d, capacity = %d\n", list.listTail, list.capacity);
        System.out.println();
    }
}
