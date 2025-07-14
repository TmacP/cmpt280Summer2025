# CMPT 280 Tutorial: Timing Analysis


## O Ω Θ
| Name | Description |
-------|--------------
| **Big-O (O):** | An upper bound on the growth rate of a function. It describes the *worst-case* scenario|
| **Big-Omega (Ω):** | A lower bound on the growth rate. It describes the *best-case* scenario |
| **Big-Theta (Θ):** | the function grows at the same rate in both the best and worst cases |

---

## Question 1

Use the statement counting approach to determine the number of statements executed by the following Java method in the **worst case** (use the statement-counting approach). Assume that all methods and constructors called by the given method are Θ(1).

```java
StringBuilder t = new StringBuilder(s); // +1 (outside loop)
 
 for (int i = 0; i < s.length(); i++) {  // Loop structure:
     // For each iteration:
     // 1. Loop test:   +1
     // 2. If check:    +1
     // 3. Body:        +1 (worst case, always executes)
     if (Character.isLowerCase(s.charAt(i))) {
         t.setCharAt(i, (char)(s.charAt(i) - 'a' + 'A'));
     }
 }
 // After the loop: one extra test when i == s.length(): +1
 
 return t.toString(); // +1 (outside loop)
 
```
Worst case is when the if-statement is always true. Let $$n = s.length()$$. Beginning with the inner loop:

- Number of statements in one loop iteration: 3  
- Number of loop iterations: $$n$$
- Total for the loop (including +1 when loop condition is false): $$3n + 1$$

Total number of statements is the cost of the loop plus statements outside the loop:


$$1 + 3n + 1 + 1 = 3n + 3$$


---

# Question 2
Determine the number of statements executed by the Java method in question 1 in the **best case**.
 
```java
StringBuilder t = new StringBuilder(s); // +1 (outside loop)
 
 for (int i = 0; i < s.length(); i++) {  // Loop structure:
     // For each iteration:
     // 1. Loop test:   +1
     // 2. If check:    +1
     // 3. Body:        0 (best case, never executes)
     if (Character.isLowerCase(s.charAt(i))) {
         t.setCharAt(i, (char)(s.charAt(i) - 'a' + 'A'));
     }
 }
 // After the loop: one extra test when i == s.length(): +1
 
 return t.toString(); // +1 (outside loop)
 
```
best case is when the if-statement is always false. Let $$n = s.length()$$. Beginning with the inner loop:

- Number of statements in one loop iteration: 2  
- Number of loop iterations: $$n$$
- Total for the loop (including +1 when loop condition is false): $$2n + 1$$

Total number of statements is the cost of the loop plus statements outside the loop:


$$1 + 2n + 1 + 1 = 2n + 2$$

---

# Question 3
 Express the worst and best case time complexities of the method in question 1 in Big-O and Big-Ω notation, respectively. If possible, express the overall time complexity of the method using Big-Θ notation.
 
 worst case: O(n)  
 best case: Ω(n)  
 overall: since the best and worst case growth functions are the same, the method is Θ(n)
 
---

# Question 4
 Do the answers to question 3 change if the call to t.toString() is Θ(n) rather than Θ(1)? Why or why not?
 
 no, because the loop is already O(n) in the worst case so adding an additional O(n) cost is 2O(n) which is just O(n). similarly for the best case, the cost would become 2Ω(n) which is just Ω(n).
 

---

# Question 5
 Determine the exact number of statements executed by the following method in the best- and worst-cases.
 
 ```java
 public Integer[][] flip(Integer[][] matrix) {
     int h = matrix.length;           // +1
     int w = matrix[0].length;        // +1
     Integer[][] T = new Integer[h][w]; // +1
 
     for (int i = 0; i < h; i++) {        // Loop structure:
         // For each iteration of outer loop:
         // 1. Outer loop test: +1
         // 2. Outer loop increment: counted as part of loop structure
         for (int j = 0; j < w; j++) {   // Inner loop structure:
             // For each iteration of inner loop:
             // 1. Inner loop test: +1
             // 2. Assignment in body: +1
             // 3. Inner loop increment: counted as part of loop structure
             T[j][i] = matrix[i][j];
         }
         // After inner loop: one extra inner test when j == w: +1
     }
     // After outer loop: one extra outer test when i == h: +1
 
     return T;                       // +1
 }
```
 

worst case and best case are the same since there is no branching and we always iterate over the entire matrix.
 
 Number of statements in one iteration of inner loop: 2  
 Number of times inner loop executes: $w$  
 Total for inner loop (including $+1$ when loop condition is false): $2w + 1$  
 
 Number of statements in one iteration of outer loop: $1$ + cost of inner loop $= 2w + 2$  
 Number of times outer loop executes: $h$  
 Total for outer loop (including $+1$ when loop condition is false): $h(2w + 2) + 1 = 2hw + 2h + 1$
 
 Total cost of method $= 4$ + cost of outer loop $= 2hw + 2h + 5$
 

---

# Question 6
 Express the overall time complexity of the method in question 7 using the appropriate notation(s) (big-O, big-Θ, big-Ω).
 
 Θ(hw)
 
---

# Question 7
 Determine the exact number of statements executed by the following method in the best- and worst-cases.
 
 ```java
public void lower_triangle_row_sum(Float[][] s) {
     // +1: if condition (best/worst case, always executed)
     if ( s.length != s[0].length )
         // +1: throw statement (best case only, stops execution)
         throw new RuntimeException("Array is not square!");
 
     // +1: float sum = 0;
     float sum = 0;
 
     // Outer loop (i from 0 to n-1):
     for(int i = 0; i < s.length; i++) { // 
         // +1: float rowsum = 0.0;
         float rowsum = 0.0;
 
         // Inner loop (j from i down to 0):
         for(int j = i; j >= 0; j--) {
             // +1: rowsum += s[i][j]; (per iteration)
             rowsum += s[i][j];
             // +1: inner loop test (j >= 0) (per iteration + 1 for final test)
         }
         // +1: System.out.println(...) (per i)
         System.out.println("Sum of row " + i + ": " + rowsum);
         // +1: outer loop test (i < s.length) (per iteration + 1 for final test)
     }
     // +1: System.out.println("Lower sum: " + sum);
     System.out.println("Lower sum: " + sum);
 }
 
 ```
 
 Let $n = s.length$, $m = s[i].length$.
 
 The best case is when the array $s$ is not square, which results in the exception. Best case executes exactly 2 statements.
 
 The worst case is when the array is square. We begin, as usual, with the inner loop:
 
 Number of statements in one iteration of inner loop: 2  
 Number of iterations of the inner loop: $i + 1$  
 Total for inner loop: $2(i + 1) + 1 = 2i + 3$
 
 Number of statements in one iteration of outer loop: $3 +$ cost of inner loop $= 2i + 6$
 
 This is a dependent-quadratic loop since the number of iterations of the inner loop depends on the value of $i$ in the outer loop.
q7 comment code
 
 Outer loop iterates for values of $i$ between $0$ and $n-1$. Thus the total cost of the outer loop is (the extra $+1$ is for when the outer loop condition is false):
 
 $$
 \begin{align*}
 &\sum_{i=0}^{n-1} (2i + 6) + 1 \\
 &= \sum_{i=0}^{n-1} (2i) + \sum_{i=0}^{n-1} 6 + 1 \\
 &= 2\sum_{i=0}^{n-1} i + 6n + 1 \\
 &= 2\left(\frac{(n-1)n}{2}\right) + 6n + 1 \\
 &= (n-1)n + 6n + 1 \\
 &= n^2 - n + 6n + 1 \\
 &= n^2 + 5n + 1
 \end{align*}
 $$
 
 Total for the whole method in the worst case is: cost of outer loop $+ 3 = n^2 + 5n + 4$  
 (Line 3 is not executed)
 
---

# Question 8
 Express the overall time complexity of the method in question 7 using the appropriate notation(s) (big-O, big-Θ, big-Ω).
 
 The method is $O(n^2)$ (worst case) and $\Omega(1)$ (best case). Since the best and worst cases are not the same, no big-$\Theta$ statement can be made.

---

# Question 9
 Prove that the following code is $O(\log n)$ by calculating the exact number of statements executed and then expressing that function in big-O notation. Assume $n$ is an `float` variable with a large positive value.
 
 ```java
 float i = n;                   // +1 (initialization)
 while(i > 1.0) {               // loop test counted per iteration (+1 per loop)
     float jump_dist = i - i / 2.0;           // +1 (per iteration)
     System.out.println("Jump " + jump_dist + " light years..."); // +1 (per iteration)
     i = i / 2.0;               // +1 (per iteration)
     // total: 4 statements per loop iteration (including test)
 }
 // After the loop: one extra test when i <= 1.0 (+1)
 System.out.println("You're now within 1 light year of your destination."); // +1
 ```
 
 Number of statements per loop iteration: 4
 
 Number of times the loop executes:
 
 Suppose the loop condition is true for $r$ iterations. On the first iteration, $i = n$, on the second iteration $i = n/2$. In the third loop iteration, $i = n/4$. On the fourth iteration $i = n/8$. On the $r$-th iteration, $i = n/2^{r-1}$.
 
 This implies that $n/2^{r-1} > 1.0$ and $n/2^r \leq 1$. Rearranging these inequalities yields the following two inequalities:
 
 $$
 n > 2^{r-1}
 $$
 $$
 n \leq 2^r
 $$
 
 or, more compactly,
 
 $$
 2^{r-1} < n \leq 2^r
 $$
 
 Taking the base-2 logarithm of all terms gives us:
 
 $$
 r - 1 < \log n \leq r
 $$
 
 Since $r$ is the number of iterations, it must be an integer. The only integer that solves these inequalities is $r = \lceil \log n \rceil$. $\lceil \log n \rceil - 1$ is definitely less than $\log n$ (the increase from the ceiling operator is always smaller than 1) so the $<$ is satisfied. $r = \lceil \log n \rceil$ definitely at least as large as $\log n$ since $\log n$ is being rounded up so the $\leq$ is satisfied.
 
 Any smaller integer $r$ will not satisfy the $\leq$ because $r = \lceil \log n \rceil - 1$ is not greater than or equal to $\log n$. Any larger $r$ will not satisfy the $<$ because $r = \lceil \log n \rceil + 1$ is not smaller than $\log n$. Thus $r = \lceil \log n \rceil$ is the only integer value of $r$ that satisfies both inequalities.
 
 Hence the loop runs $\lceil \log n \rceil$ times.
 
 The total cost of the loop is therefore $4\lceil \log n \rceil + 1$.
 
 The total cost of the entire code snippet is the cost of the loop, plus the cost of the statements outside the loop which is:
 
 $$
 4\lceil \log n \rceil + 3
 $$
 
 This function is clearly $O(\log n)$, so the code snippet is $O(\log n)$ (and also $\Omega(\log n)$ and therefore $\Theta(\log n)$ ).
 


---

# Question 10
Determine the time complexity of the following pseudocode snippet using the active operation approach.

```java
Let A be a 2D array of size n by n.
Let T be an AVL tree with m items.

for each row r of A:                // outer loop: n times
    for each column c of A:         // inner loop: n times per row, total n^2 times
        A[r][c] = A[r-1][c] + A[r][c-1] - A[r-1][c-1]   // O(1) per iteration
        T.insert(A[r][c])                                 // O(log m) per iteration (active op)
```

The active operation could be the assignment or the insert, since both are executed $n^2$ times, but only the insert is non-constant time.  
Therefore, the insert operation is chosen as the active operation.

In the worst case, the active operation (insert) has a cost of $O(\log m)$ where $m$ is the number of items in the AVL tree.

The number of times the active operation is executed is $n^2$, thus the total cost of the pseudocode is $n^2 \cdot O(\log m)$ which is $O(n^2 \log m)$.

---

# Question 11
 Determine the time complexity of the following pseudocode snippet using the active operation approach (careful! This one is tricky!).
 
 ```java
 Let G be a weighted graph that is implemented with an adjacency list
     with |V| nodes and |E| edges.
 Let H be a heap of edges ordered by edge weight (initially empty).
 
 for each vertex v in G:
     if there is an edge from vertex v to vertex 0:
         insert the edge (v,0) to H
 ```
 
 There are two possible active operations: Line 6 and line 7 since both execute the same number of times in the worst case and both have non-constant cost. Since it isn’t immediately obvious which should be the active operation, we perform the analysis for both.
 
 The cost of line 6 is $O(|V|)$ since in the worst case of a complete graph it takes $O(|V|)$ time to search the adjacency list for an edge to vertex 0.
 
 The cost of line 7 is $O(\log |V|)$ since in the worst case there are $|V| - 1$ edges in the heap (when the last vertex v is processed).
 
 Line 6 executes exactly $|V|$ times, once for each vertex $v$.
 
 Line 7 executes exactly $|V|$ times in the worst case, once for each vertex $v$.
 
 Using line 6 as the active operation, the total cost is $|V| \cdot O(|V|)$ which is $O(|V|^2)$.
 
 Using line 7 as the active operation, the total cost is $|V| \cdot O(\log |V|)$. This is cheaper than line 6, thus line 6 is the active operation, and the algorithm is $O(|V|^2)$.
 
---

# Question 12
 Determine exact number of statements executed by the following pseudocode function in the worst case (this one is a bit tricky too!):
 
 ```java
 Algorithm treeconcat(root)
 
 Let root be the root node of an m-ary tree containing n nodes where each
     node contains a string and each non-leaf node has exactly
     m children.
 Let s be the empty string.
 
 s <- root.string()                 // assign the string stored in root to s
 
 if root is not a leaf node:        // Assume this line is O(1)
     for each child q of root:
         s <- s + treeconcat(q)     // string concatenation of s with the
                                    // return value, assume the concatenation
                                    // operation is O(1).
 
 return s
 ```
 
 The worse case and best case are the same here. We recurse through every node of the tree. The leaf nodes are the base case of the recursion.
 
 The number of statements executed after the recursive call is 1.
 
 The number of statements executed before the recursive call before the for-loop is 2 (we don’t count the for-loop on line 11 for now).
 
 Regardless of the structure of the tree, a recursive call on line 12 will happen once for each node in the tree except the root. Thus there are $n - 1$ recursive calls.
 
 Moreover, and again regardless of the structure of the tree, the for loop will execute once for each node other than the root, thus the for loop executes $n - 1$ times over all recursive calls.
 
 So to find the total lines executed, take the sum of the lines executed before and after the recursive call (not counting line 11) multiply that by the number of recursive calls, then add to the result the number of times line 11 executes.
 
 Therefore the total number of lines executed is:
 
 $$
 \begin{align*}
 3(n-1) + (n-1) & = 3n - 3 + n - 1 \\
 & = 4n - 4
 \end{align*}
 $$
 
