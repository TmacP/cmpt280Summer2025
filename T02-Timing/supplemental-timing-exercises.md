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