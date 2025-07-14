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
 
 Number of statements in one iteration of outer loop: $1$ (outer loop test) $+$ cost of inner loop $= 2w + 2$  
 Number of times outer loop executes: $h$  
 Total for outer loop (including $+1$ when outer loop condition is false):  
 $h(2w + 2) + 1 = 2hw + 2h + 1$
 
 Total cost of method $= 3$ (setup) $+$ cost of outer loop $+ 1$ (return)  
 $= 3 + 2hw + 2h + 1 + 1$  
 $= 2hw + 2h + 5$
 
