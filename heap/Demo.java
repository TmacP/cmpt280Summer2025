public class Demo {
     // 1-based index; 0 is unused. Plenty of room for demo purposes.
     public static int[] heap = new int[25];
     public static int heapSize = 0;
 
     public static void main(String[] args) {
         // Initial demo: insert several values
         insert(101);
         insert(75);
         insert(88);
         insert(61);
         insert(43);
         insert(67);
         insert(79);
         insert(17);
         insert(3);
         insert(8);
 
         // Try inserting another value
         insert(81);
 
         // Try deleting the root a couple of times
         deleteRoot();
     }
 
     // Insert a value, maintaining the max-heap property.
     public static void insert(int value) {
         // Check if heap is full (fixed size)
         if (heapSize == heap.length - 1) {
             System.err.println("Heap is full!");
             return;
         }
         heapSize++;
         int i = heapSize;
         heap[i] = value;
 
         // Bubble up
         while (i > 1 && heap[i] > heap[i / 2]) {
             int temp = heap[i];
             heap[i] = heap[i / 2];
             heap[i / 2] = temp;
             i = i / 2;
         }
     }
 
     // Remove the root (max element), maintain heap property.
     public static void deleteRoot() {
         if (heapSize == 0) {
             System.err.println("Heap is empty!");
             return;
         }
         // Move the last item to the root and decrease size
         heap[1] = heap[heapSize];
         heapSize--;
 
         // Bubble down
         int i = 1;
         while (true) {
             int left = 2 * i;
             int right = 2 * i + 1;
             int largest = i;
 
             if (left <= heapSize && heap[left] > heap[largest]) largest = left;
             if (right <= heapSize && heap[right] > heap[largest]) largest = right;
 
             if (largest != i) {
                 int temp = heap[i];
                 heap[i] = heap[largest];
                 heap[largest] = temp;
                 i = largest;
             } else {
                 break;
             }
         }
     }
 }
 
