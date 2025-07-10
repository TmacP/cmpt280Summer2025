# Question 1

 Use the statement counting approach to determine the number of statements executed by the following Java method in the **worst case** (use the statement-counting approach). Assume that all methods and constructors called by the given method are Θ(1).

 ```java
 // Convert all lowercase characters in string s to upper case.
 public String toUpperCase(String s) {
     StringBuilder t = new StringBuilder(s);

     for (int i = 0; i < s.length(); i++) {
         if (Character.isLowerCase(s.charAt(i))) {
             t.setCharAt(i, (char) (s.charAt(i) - 'a' + 'A'));
         }
     }

     return t.toString();
 }
 ```

 **Solution**  
 Worst case is when the `if`-statement is always true. Let \( n = s.length() \). Beginning with the inner loop:

 - Number of statements in one loop iteration: 3
 - Number of loop iterations: \( n \)
 - Total for the loop (including +1 when the loop condition is false): \( 3n + 1 \)

 Total number of statements is the cost of the loop plus statements outside the loop:

 \[ 1 + (3n + 1) + 1 = 3n + 3 \]



# Question 2

Determine the number of statements executed by the Java method in question 1 in the **best case**.

---

**Solution**  
The best case occurs when the `if`-statement inside the loop is **never** executed—i.e., every character fails the lowercase test. Let \(n = s.length()\).

1. **Initialization**:  
   - `StringBuilder t = new StringBuilder(s);` → 1 statement

2. **For Loop**:  
   - Loop control (`i < s.length()`) and update (`i++`), plus the `if` condition check → 2 statements per iteration
   - Number of iterations: \(n\)
   - One extra loop-condition check when the loop exits → +1 statement
   - **Total loop cost**: \(2n + 1\)

3. **Return**:  
   - `return t.toString();` → 1 statement

**Total Statements**  
\[ 1 + (2n + 1) + 1 = 2n + 3 \]

**Final Answer**: 2n + 3



# Question 3

Express the worst and best case time complexities of the method in question 1 in Big-O and Big-Ω notation, respectively. If possible, express the overall time complexity of the method using Big-Θ notation.

---

## Solution

Before we analyze, here is a quick reminder of what each notation means:

- **Big-O (O)**: An upper bound on the growth rate of a function. It describes the *worst-case* scenario—how quickly the running time can grow as the input size increases.
- **Big-Omega (Ω)**: A lower bound on the growth rate. It describes the *best-case* scenario—the minimum time the algorithm will take as the input size increases.
- **Big-Theta (Θ)**: A tight bound. It means the function grows at the same rate in both the best and worst cases; the running time is always proportional to this rate.

---

- **Worst Case (Big-O):**  
  In the worst case, every character in the string is a lowercase letter, so the body of the `if` statement executes every time. The loop runs for every character in the string, and all operations inside the loop are constant-time. Therefore, the worst-case time complexity is  
  \[
  O(n)
  \]
  where \( n \) is the length of the input string.

- **Best Case (Big-Ω):**  
  In the best case, none of the characters are lowercase (so the `if` body never executes). However, the loop still iterates over every character, performing the `if` check each time. Thus, even in the best case, the time taken is proportional to the length of the string:  
  \[
  \Omega(n)
  \]

- **Overall Time Complexity (Big-Θ):**  
  Since both the best-case and worst-case time complexities grow linearly with \( n \), the overall time complexity is tightly bounded (both above and below) by a linear function. Thus, the overall time complexity of the method is  
  \[
  \Theta(n)
  \]
  where \( n \) is the length of the input string.

---

**Summary:**
- Worst case: \( O(n) \)
- Best case: \( \Omega(n) \)
- Overall: \( \Theta(n) \)
