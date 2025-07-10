# Question 1

 Use the statement counting approach to determine the number of statements executed by the f---

# Question 4

Do the answers to question 3 change if the call to `t.toString()` is Θ(n) rather than Θ(1)? Why or why not?

## Solution

**Answer: No, the asymptotic complexities remain the same.**

**Explanation:**

When analyzing asymptotic complexity, we consider how the dominant terms grow as the input size increases. Let's examine what happens when `t.toString()` changes from Θ(1) to Θ(n):

- **Original complexity:** The loop dominates with Θ(n) operations, plus a Θ(1) `toString()` call
- **Modified complexity:** The loop still has Θ(n) operations, but now `toString()` also takes Θ(n) time

**Mathematical reasoning:**
- **Worst case:** Originally O(n), now O(n) + O(n) = O(2n) = O(n) *(constants are dropped in Big-O)*
- **Best case:** Originally Ω(n), now Ω(n) + Ω(n) = Ω(2n) = Ω(n) *(constants are dropped in Big-Ω)*
- **Overall:** Still Θ(n) since both bounds remain linear

The key insight is that adding another linear operation to an already linear algorithm doesn't change the asymptotic growth rate—it just changes the constant factors.

--- Java method in the **worst case** (use the statement-counting approach). Assume that all methods and constructors called by the given method are Θ(1).

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




Question 4
Do the answers to question 3 change if the call to t.toString() is Θ(n) rather than Θ(1)? Why or
why not?
Solution: No, because the loop is already O(n) in the worst case so adding an additional O(n) cost
is 2O(n) which is just O(n). Similarly for the best case, the cost would become 2Ω(n) which is just
Ω ( n ).

Page 2

# Question 5

Determine the exact number of statements executed by the following method in the best- and worst-cases.

```java
public Integer[][] flip(Integer[][] matrix) {
    int h = matrix.length;                    // Line 2
    int w = matrix[0].length;                 // Line 3
    Integer[][] T = new Integer[h][w];        // Line 4
    
    for (int i = 0; i < h; i++) {            // Line 6
        for (int j = 0; j < w; j++) {        // Line 7
            T[j][i] = matrix[i][j];          // Line 8
        }
    }
    return T;                                // Line 11
}
```

## Solution

**Key insight:** Best case and worst case are identical since there is no conditional branching. The algorithm always processes every element of the matrix exactly once.

Let h = matrix height, w = matrix width.

### Detailed Statement Counting:

**Statements outside loops:**
- Lines 2, 3, 4, and 11: **4 statements total**

**Inner loop analysis (line 7-8):**
- Line 8 (assignment): 1 statement per iteration
- Line 7 (loop control: condition check + increment): 1 statement per iteration  
- **Statements per inner loop iteration:** 2
- **Inner loop iterations:** w times (for each column)
- **Extra condition check:** +1 when loop exits
- **Total for one execution of inner loop:** 2w + 1

**Outer loop analysis (line 6):**
- Line 6 (loop control): 1 statement per iteration
- Inner loop cost: 2w + 1
- **Statements per outer loop iteration:** 1 + (2w + 1) = 2w + 2
- **Outer loop iterations:** h times (for each row)
- **Extra condition check:** +1 when loop exits  
- **Total for outer loop:** h(2w + 2) + 1 = 2hw + 2h + 1

### Final Calculation:
**Total statements = Statements outside loops + Outer loop cost**
\[ 4 + (2hw + 2h + 1) = 2hw + 2h + 5 \]

---

# Question 6

Express the overall time complexity of the method in question 5 using the appropriate notation(s) (big-O, big-Θ, big-Ω).

## Solution

**Answer: Θ(hw)**

**Explanation:**

Since the best case and worst case are identical (as shown in Question 5), we can use Big-Θ notation to express a tight bound.

The exact statement count is 2hw + 2h + 5, where:
- The dominant term is **2hw** (quadratic in the dimensions)
- The linear term **2h** becomes negligible as h and w grow large
- The constant **5** is insignificant for large inputs

Therefore, the time complexity is **Θ(hw)**, meaning the algorithm's running time grows proportionally to the product of the matrix dimensions.

# Question 7

Determine the exact number of statements executed by the following method in the best- and worst-cases.

```java
public void lower_triangle_row_sum(Float[][] s) {
    if (s.length != s[0].length)                           // Line 2
        throw new RuntimeException("Array is not square!"); // Line 3
    
    float sum = 0;                                          // Line 5
    for (int i = 0; i < s.length; i++) {                  // Line 6
        float rowsum = 0.0;                                // Line 7
        for (int j = i; j >= 0; j--) {                     // Line 8
            rowsum += s[i][j];                             // Line 9
        }
        System.out.println("Sum of row " + i + ": " + rowsum); // Line 11
    }
    System.out.println("Lower sum: " + sum);               // Line 13
}
```

## Solution

**Key insight:** This algorithm has two very different execution paths depending on whether the input array is square or not.

Let n = s.length (assuming square matrix when applicable).

### Best Case Analysis:

**Condition:** The array `s` is **not square** (s.length ≠ s[0].length).

**Execution:**
- Line 2: condition check → 1 statement
- Line 3: throw exception → 1 statement  
- **Total:** 2 statements

### Worst Case Analysis:

**Condition:** The array `s` **is square** (s.length = s[0].length = n).

**Statements outside loops:**
- Lines 2, 5, and 13: **3 statements total** (Line 3 is not executed)

**Inner loop analysis (lines 8-9):**
- Line 9 (addition): 1 statement per iteration
- Line 8 (loop control): 1 statement per iteration
- **Statements per inner loop iteration:** 2
- **Inner loop iterations:** For outer loop iteration i, inner loop runs from j=i down to j=0, so (i+1) iterations
- **Extra condition check:** +1 when loop exits
- **Total for one execution of inner loop:** 2(i+1) + 1 = 2i + 3

**Outer loop analysis (lines 6-11):**
- Line 6 (loop control): 1 statement per iteration
- Line 7 (assignment): 1 statement per iteration  
- Line 11 (print): 1 statement per iteration
- Inner loop cost: 2i + 3
- **Statements per outer loop iteration:** 3 + (2i + 3) = 2i + 6

**This is a dependent-quadratic loop** since the inner loop iterations depend on the outer loop variable i.

**Total outer loop cost:**
The outer loop runs for i = 0, 1, 2, ..., n-1, so we sum:

\[
\sum_{i=0}^{n-1}(2i + 6) + 1 = \sum_{i=0}^{n-1}2i + \sum_{i=0}^{n-1}6 + 1
\]

\[
= 2\sum_{i=0}^{n-1}i + 6n + 1 = 2 \cdot \frac{(n-1)n}{2} + 6n + 1
\]

\[
= (n-1)n + 6n + 1 = n^2 - n + 6n + 1 = n^2 + 5n + 1
\]

### Final Calculation:
**Total statements (worst case) = Statements outside loops + Outer loop cost**
\[ 3 + (n^2 + 5n + 1) = n^2 + 5n + 4 \]

**Summary:**
- **Best case:** 2 statements
- **Worst case:** n² + 5n + 4 statements

---

# Question 8

Express the overall time complexity of the method in question 7 using the appropriate notation(s) (big-O, big-Θ, big-Ω).

## Solution

**Answer:** 
- **Worst case:** O(n²)
- **Best case:** Ω(1)
- **No tight bound (Θ) exists**

**Explanation:**

The time complexity analysis reveals two fundamentally different execution scenarios:

### Best Case - Ω(1):
When the input array is not square, the method executes exactly 2 statements and terminates with an exception. This gives us a **constant-time lower bound: Ω(1)**.

### Worst Case - O(n²):
When the input array is square with dimension n×n, the method executes n² + 5n + 4 statements. The dominant term is n², giving us an **upper bound: O(n²)**.

### Why No Θ Bound Exists:
Since the best-case complexity Ω(1) and worst-case complexity O(n²) have different growth rates, we cannot establish a tight bound using Big-Θ notation. The algorithm's performance depends entirely on the input characteristics (square vs. non-square), leading to fundamentally different execution paths.

This is a perfect example of an algorithm where **input characteristics dramatically affect performance**, preventing us from making a tight asymptotic statement.

Page 4

# Question 9

Prove that the following code is O(log n) by calculating the exact number of statements executed and then expressing that function in big-O notation. Assume n is a float variable with a large positive value.

```java
float i = n;                                                           // Line 1
while (i > 1.0) {                                                     // Line 2
    float jump_dist = i - i / 2.0;                                   // Line 3
    System.out.println("Jump " + jump_dist + " light years...");     // Line 4
    i = i / 2.0;                                                      // Line 5
}
System.out.println("You're now within 1 light year of your destination."); // Line 8
```

## Solution

### Step 1: Count Statements Per Loop Iteration

**Loop body (lines 3-5):**
- Line 3: calculation and assignment → 1 statement
- Line 4: print statement → 1 statement  
- Line 5: division and assignment → 1 statement
- Line 2: loop condition check → 1 statement
- **Total per iteration:** 4 statements

### Step 2: Determine Number of Loop Iterations

**Key insight:** The loop halves the value of `i` in each iteration until `i ≤ 1.0`.

Let's trace the sequence:
- **Iteration 1:** i = n  
- **Iteration 2:** i = n/2
- **Iteration 3:** i = n/4  
- **Iteration 4:** i = n/8
- **Iteration r:** i = n/2^(r-1)

**Loop termination condition:** The loop continues while i > 1.0 and stops when i ≤ 1.0.

After r iterations: i = n/2^(r-1)

**For the loop to continue:** n/2^(r-1) > 1.0  
**For the loop to stop:** n/2^r ≤ 1.0

This gives us the inequalities:
\[ n > 2^{r-1} \text{ and } n ≤ 2^r \]

Or more compactly:
\[ 2^{r-1} < n ≤ 2^r \]

### Step 3: Solve for r

Taking the base-2 logarithm of all parts:
\[ r-1 < \log_2 n ≤ r \]

Since r must be an integer (number of iterations), the unique solution is:
\[ r = ⌈\log_2 n⌉ \]

**Verification:**
- **Lower bound:** ⌈log₂ n⌉ - 1 < log₂ n ✓ (ceiling function increases by less than 1)
- **Upper bound:** ⌈log₂ n⌉ ≥ log₂ n ✓ (ceiling function rounds up)

### Step 4: Calculate Total Statements

**Loop cost:** 4 statements/iteration × ⌈log₂ n⌉ iterations + 1 extra condition check  
= 4⌈log₂ n⌉ + 1

**Statements outside loop:** Lines 1 and 8 = 2 statements

**Total statements:** 4⌈log₂ n⌉ + 1 + 2 = **4⌈log₂ n⌉ + 3**

### Step 5: Express in Big-O Notation

Since ⌈log₂ n⌉ ≤ log₂ n + 1, we have:
4⌈log₂ n⌉ + 3 ≤ 4(log₂ n + 1) + 3 = 4log₂ n + 7

Therefore: **f(n) = O(log n)**

**Additional observations:**
- Since ⌈log₂ n⌉ ≥ log₂ n, we also have f(n) = Ω(log n)
- **Conclusion:** f(n) = Θ(log n) - tight logarithmic bound

This proves the algorithm has **logarithmic time complexity**, which makes intuitive sense since we're repeatedly halving the value until it becomes small enough.

Page 5

# Question 10

Determine the time complexity of the following pseudocode snippet using the **active operation approach**.

```java
Let A be a 2D array of size n by n;
Let T be an AVL tree with m items;

for (int r = 0; r < n; r++) {
    for (int c = 0; c < n; c++) {
        A[r][c] = A[r-1][c] + A[r][c-1] - A[r-1][c-1];
        T.insert(A[r][c]);
    }
}
```

## Solution

### Active Operation Analysis

**Step 1: Identify potential active operations**
- **Line 5:** Loop control (inner loop header)
- **Line 6:** Array operations  
- **Line 7:** AVL tree insertion

**Step 2: Determine execution frequency**
- Line 5 executes n² + n times (n times per outer loop iteration, plus condition checks)
- Line 6 executes n² times (once per matrix element)
- Line 7 executes n² times (once per matrix element)

**Step 3: Determine operation costs**
- Line 5: O(1) - simple condition checking
- Line 6: O(1) - constant-time array operations
- Line 7: O(log m) - AVL tree insertion where m is current tree size

**Step 4: Select active operation**
Since Line 7 has **non-constant cost** while Lines 5 and 6 are constant-time, **Line 7 (AVL insertion) is the active operation**.

### Complexity Calculation

**Active operation cost:** O(log m) in the worst case, where m is the number of items currently in the AVL tree.

**Number of executions:** n² (once for each matrix element)

**Total complexity:** n² × O(log m) = **O(n² log m)**

**Note:** The parameter m represents the size of the pre-existing AVL tree, making this algorithm's complexity dependent on both the matrix size (n) and the initial tree size (m).

---

# Question 11

Determine the time complexity of the following pseudocode snippet using the active operation approach (**careful! This one is tricky!**).

```java
Let G be a weighted graph implemented with an adjacency list;
Let H be a heap of edges ordered by edge weight (initially empty);

for (Vertex v : G.vertices()) {
    if (G.hasEdge(v, 0)) {
        H.insert(new Edge(v, 0));
    }
}
```

## Solution

### Why This is Tricky

This problem requires careful analysis because **two operations have non-constant cost** and execute the same number of times, making the choice of active operation non-obvious.

### Active Operation Analysis

**Potential active operations:**
- **Line 6:** Edge existence check
- **Line 7:** Heap insertion

**Step 1: Determine execution frequency**
- Line 6 executes exactly |V| times (once for each vertex)
- Line 7 executes at most |V| times in worst case (when every vertex has an edge to vertex 0)

**Step 2: Determine operation costs**

**Line 6 cost analysis:**
- **Best case:** O(1) - edge to vertex 0 is first in adjacency list
- **Worst case:** O(|V|) - in a complete graph, we may need to search through all |V|-1 adjacent vertices

**Line 7 cost analysis:**
- **Insertion cost:** O(log k) where k is current heap size
- **Worst case:** O(log |V|) when heap contains |V|-1 edges (processing the last vertex)

### Complexity Calculation

**Using Line 6 as active operation:**
|V| executions × O(|V|) cost = **O(|V|²)**

**Using Line 7 as active operation:**  
|V| executions × O(log |V|) cost = **O(|V| log |V|)**

### Final Answer

Since O(|V|²) > O(|V| log |V|), **Line 6 dominates** and becomes the active operation.

**Total complexity: O(|V|²)**

**Key insight:** Even though heap operations are typically expensive, the linear search through adjacency lists in a dense graph becomes the bottleneck.

---

# Question 12

Determine exact number of statements executed by the following pseudocode function in the worst case (**this one is a bit tricky too!**):

```java
public String treeConcat(Node root) {
    String s = root.string();

    if (!root.isLeaf()) {
        for (Node child : root.children()) {
            s += treeConcat(child);
        }
    }

    return s;
}
```

## Solution

### Key Insights

1. **Best case = Worst case:** No conditional branching that changes execution path based on data values
2. **Recursive traversal:** Every node in the tree is visited exactly once
3. **Statement counting:** Focus on operations, not data structure navigation

### Statement Counting Analysis

**Per recursive call, we execute:**
- Line 1: 1 statement (string assignment)
- Line 2: 1 statement (leaf node check)  
- Line 6: 1 statement (return)
- **Subtotal:** 3 statements per call

**For non-leaf nodes, we also execute:**
- Line 3: 1 statement per child (loop control)
- Line 4: 1 statement per child (concatenation + assignment)

### Execution Frequency Analysis

**Recursive calls:** Every node except the root requires a recursive call.
- **Total recursive calls:** n - 1

**Loop executions (Line 3):** The loop executes once for each child of each non-leaf node.
- Since each internal node has exactly m children, and we have (n-1) total calls...
- But we need to count loop executions across ALL recursive calls
- **Total loop executions:** n - 1 (once per recursive call, since each call handles one child)

### Final Calculation

**Statements before/after recursive calls:** 3 statements × (n-1) calls = 3(n-1)

**Loop control statements:** 1 statement × (n-1) executions = (n-1)

**Total statements:**
\[ 3(n-1) + (n-1) = 3n - 3 + n - 1 = 4n - 4 \]

**Answer: 4n - 4 statements**

**Verification:** This makes intuitive sense—we visit each of the n nodes and perform a constant amount of work per node, resulting in linear complexity.

