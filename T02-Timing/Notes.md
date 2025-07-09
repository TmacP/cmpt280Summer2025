[200~
# CMPT 280 Tutorial: Timing Analysis

## 1  Introduction to Growth Functions

Growth functions describe how running time (or memory) increases as the input size *n* grows.

| Slowest → fastest | Typical name               |
| ----------------- | -------------------------- |
| log log n         | doubly‑logarithmic         |
| log n             | logarithmic                |
| √ n               | square‑root                |
| n                 | linear                     |
| n log n           | linearithmic               |
| n²                | quadratic                  |
| n³                | cubic                      |
| nᵏ                | k‑th‑degree polynomial     |
| aⁿ                | exponential (base *a* > 1) |
| n!                | factorial                  |

---

## 2  Classifying Expressions with Big‑O (Slide 2)

The Big‑O “bucket” is the *dominant* term after you drop lower‑order terms & constant factors.

| Expression from slide         | Dominant term | Bucket (O)      |
| ----------------------------- | ------------- | --------------- |
| 5 log n + √n + 1000 n²        | 1000 n²       | **O(n²)**       |
| 15 n log n + 2 n − 100        | 15 n log n    | **O(n log n)**  |
| 42 n³ + 3 n² + 2 n + 1        | 42 n³         | **O(n³)**       |
| 7 n + 1400 n! + 12 ∕ 7 log n  | 1400 n!       | **O(n!)**       |
| 7700 n² log n                 | 7700 n² log n | **O(n² log n)** |
| (8⁄11) n + 4 log n + 17 (n−1) | 0.727 n       | **O(n)**        |

---

## 3  Full Statement Counting — `arrayMax` (Slide 4)

```java
Algorithm arrayMax(A, n)
  currentMax = A[0];    // 1
  i = 1;                // 1
  
  while (i < n) {       // condition test every pass
    if (currentMax < A[i]) // 1 per pass
      currentMax = A[i];   // 0 or 1 per pass
    i = i + 1;             // 1 per pass
  }                       // extra failed test
  return currentMax;      // 1
```

| Phase               | Count       |
| ------------------- | ----------- |
| Setup lines         | 2           |
| Loop test           | 1 × n       |
| `if` test           | 1 × (n−1)   |
| Possible assignment | 0–1 × (n−1) |
| Increment           | 1 × (n−1)   |
| Final failed test   | 1           |
| Return              | 1           |

- Worst case: 4n steps.
- Best case: 3n steps.
- Complexity: **Θ(n)**.

---

## 4  Active‑Operation Counting — `arrayMax` (Slide 5)

```java
int arrayMax(int[] A, int n) {
    int currentMax = A[0];  // executed once (constant)
    int i = 1;              // executed once (constant)

    while (i < n) {         // <-- Active Operation (loop-condition check)
        // The active operation (i < n) runs exactly n times:
        // once for each element (1 to n-1) and once when condition fails.

        if (currentMax < A[i])  // comparison: constant per iteration
            currentMax = A[i];  // assignment: at most once per iteration (constant)

        i++;                    // increment: constant per iteration
    }

    return currentMax;      // executed once (constant)
}
```

1. **Pick one operation**: loop‑condition `i < n`.
2. **Count its frequency**: *n* times.
3. **All other work** constant per pass.
4. Complexity: `T(n)=c · n ∈ Θ(n)`.

---

## 5 Linear Loops (Slide 6)

Linear loops occur whenever the loop index increments or decrements by a constant amount on each iteration. Here's the detailed Java code from slide 6:

### Example 1: Standard Increment Loop

```java
// Simple counting loops are Linear Loops:
for(int i = 0; i < n; i++) {
    // statements executed are O(1)
    // executed exactly n times
}
```

**Explanation:**  
- The loop runs exactly **n** times, incrementing `i` by **1** each iteration.

---

### Example 2: Standard While Loop

```java
int i = 0;
while(i < n) {
    // statements executed are O(1)
    i++;  // increment is constant per iteration
}
```

**Explanation:**  
- This loop behaves identically to the previous for-loop.
- Executed exactly **n** times

---

**Final Complexity (Both examples):** **Θ(n)**

---

## 6  Logarithmic Loops (Slides 7–9)

```java
for(i = 1; i < n; i = i* 2) {
  // O(1) work
}
```
```java
for(i = n; i >= 1; i = i/2) {
  // O(1) work
}
```
Iterations: ⌈log₂ n⌉ → **Θ(log n)**.

---

## 7  Quadratic Nested Loops (Slide 10)

```java
for(i = 0; i < n; i++)
  for(j = 0; j < n; j++)
    // O(1) work
```

Complexity: **Θ(n²)**.

---

## 8  Dependent Quadratic Loops (Slide 11)

```java
for(i = 0; i < n; i++)
  for(j = 0; j < i; j++)
    // O(1) work
```

Complexity: (n(n-1))/2 ≈ ½ n² → **Θ(n²)**.

---

## 9  Worked Examples (Slides 12–14)

**Matrix Sum:**

```java
for(i = 0; i < n; i++)
  for(j = 0; j < n; j++)
    matrix3[i][j] = matrix1[i][j] + matrix2[i][j];
```

Complexity: **Θ(n²)**.

**Prefix Averages:**

```java
for(i = 0; i < n; i++) {
  avg = 0;
  for(j = 0; j <= i; j++)
    avg += X[j];
  A[i] = avg / (i+1);
}
```

Complexity: **Θ(n²)**.

**Binary Search:**

```java
lo = 0; hi = n-1;
while(lo <= hi) {
  mid = (lo + hi) / 2;
  if(key < arr[mid]) hi = mid - 1;
  else if(key > arr[mid]) lo = mid + 1;
  else return mid;
}
return -1;
```

Complexity: **Θ(log n)**.

---

## 10  Statement Counting vs Active‑Operation Counting

| Aspect                                               | Statement Counting                                          | Active‑Op Counting                                                                                     |
| ---------------------------------------------------- | ----------------------------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| **What you do**                                      | Tally *every* primitive step.                               | Pick **one** operation repeated proportional to the work; multiply by a constant.                      |
| **Precision**                                        | Gives an *exact* linear formula (reveals constant factors). | Only provides order of growth (hides constants).                                                       |
| **Effort**                                           | Tedious for long code; error‑prone.                         | Quick; ideal under test‑time pressure.                                                                 |
| **When to use**                                      | • Profiling small‑n performance.                            |                                                                                                        |
| • Hardware‑level tuning.                             | • Proving Θ‑class on exams.                                 |                                                                                                        |
| • Large, nested code where constants are irrelevant. |                                                             |                                                                                                        |
| **Danger points**                                    | Must assume each primitive really is O(1).                  | Must ensure loop body truly is **O(1)** per pass; if it calls an O(n) sub‑routine, the shortcut fails. |

## 11  Asymptotic Definitions

An **asymptote** is a curve that another curve approaches arbitrarily closely as *n → ∞*.

> In algorithm analysis we say *T(n) is ****asymptotically**** f(n)* if the ratio T(n)/f(n) tends to a constant when n becomes very large.
---
