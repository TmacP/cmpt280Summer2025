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
// Convert all lowercase characters in string s to upper case.
public String toUpperCase(String s) {

    StringBuilder t = new StringBuilder(s); // +1 (statement outside the loop)

    for (int i = 0; i < s.length(); i++) { // +1 (loop init, counted once)
        // Loop test and increment counted in the loop body below
        if (Character.isLowerCase(s.charAt(i))) { // +1 per iteration (if check)
            t.setCharAt(i, (char)(s.charAt(i) - 'a' + 'A')); // +1 per iteration (setCharAt)
        }
        // Loop increment: +1 per iteration (i++)
        // Loop test: +1 per iteration (i < s.length())
    }

    return t.toString(); // +1 (statement outside the loop)
}
```
Worst case is when the if-statement is always true. Let $$n = s.length()$$. Beginning with the inner loop:

- Number of statements in one loop iteration: 3  
- Number of loop iterations: $$n$$
- Total for the loop (including +1 when loop condition is false): $$3n + 1$$

Total number of statements is the cost of the loop plus statements outside the loop:


$$1 + 3n + 1 + 1 = 3n + 3$$


