import lib280.tree.BasicMAryTree280;
 
 public class Demo {
 
     public static void main(String[] args) {
         // Build the tree from slide 10 (Mungo Baggins example)
         BasicMAryTree280<String> T = new BasicMAryTree280<>("Mungo", 5);
         T.setRootSubtree(new BasicMAryTree280<>("Bungo", 5), 1);
         T.setRootSubtree(new BasicMAryTree280<>("Belba", 5), 2);
         T.setRootSubtree(new BasicMAryTree280<>("Longo", 5), 3);
         T.setRootSubtree(new BasicMAryTree280<>("Linda", 5), 4);
         T.setRootSubtree(new BasicMAryTree280<>("Bingo", 5), 5);
 
         BasicMAryTree280<String> Bungo = T.rootSubtree(1);
         Bungo.setRootSubtree(new BasicMAryTree280<>("Bilbo Baggins", 5), 1);
 
         // Print the tree contents recursively
         printContents(T);
     }
 
     // Public method to start recursion at root
     public static void printContents(BasicMAryTree280<String> tree) {
         if (!tree.isEmpty()) {
             printContentsHelper(tree);
         }
     }
 
     // Helper: recursively print this tree and all non-empty subtrees
     protected static void printContentsHelper(BasicMAryTree280<String> tree) {
         System.out.println(tree.rootItem());
         int last = tree.rootLastNonEmptyChild();
         for (int i = 1; i <= last; i++) {
             BasicMAryTree280<String> child = tree.rootSubtree(i);
             if (child != null && !child.isEmpty()) {
                 printContentsHelper(child);
             }
         }
     }
 }
 
