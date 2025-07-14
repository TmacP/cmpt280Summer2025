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
 n > 2^{r-1} \\
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
 
 This function is clearly $O(\log n)$, so the code snippet is $O(\log n)$ (and also $\Omega(\log n)$ and therefore $\Theta(\log n)$).
 
