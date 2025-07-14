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
