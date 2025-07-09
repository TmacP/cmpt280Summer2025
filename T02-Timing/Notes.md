# CMPT 280 Tutorial: Timing Analysis

> **Purpose of this hand‑out**  Provide you—as a TA—with enough *depth* and *rigour* to answer every likely student question about the timing‑analysis slides (02T‑timing) and their solutions.

---

## 1  Introduction to Growth Functions

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
| aⁿ                | exponential (base *a* > 1) |
| n!                | factorial                  |

---

## 2  Classifying Expressions with Big‑O (Slide 2)

The Big‑O “bucket” is the *dominant* term after you drop lower‑order terms & constant factors.

| Expression from slide         | Dominant term | Bucket (O)      |
| ----------------------------- | ------------- | --------------- |
| 5 log n + √n + 1000 n²        | 1000 n²       | **O(n²)**       |
| 15 n log n + 2 n − 100        | 15 n log n    | **O(n log n)**  |
| 42 n³ + 3 n² + 2 n + 1        | 42 n³         | **O(n³)**       |
| 7 n + 1400 n! + 12 ∕ 7 log n  | 1400 n!       | **O(n!)**       |
| 7700 n² log n                 | 7700 n² log n | **O(n² log n)** |
| (8⁄11) n + 4 log n + 17 (n−1) | 0.727 n       | **O(n)**        |

---

## 3  Full Statement Counting — `arrayMax` (Slide 4)

```text
5  currentMax ← A[0]      // 1
6  i ← 1                  // 1
8  while (i < n) {        // condition test every pass
9     if (currentMax < A[i]) // 1 per pass
10        currentMax ← A[i]  // 0 or 1 per pass
11     i ← i + 1            // 1 per pass
12  }                      // extra failed test
13  return currentMax      // 1
```

| Phase               | Count       |
| ------------------- | ----------- |
| Setup lines 5–6     | 2           |
| Loop test           | 1 × n       |
| `if` test           | 1 × (n−1)   |
| Possible assignment | 0–1 × (n−1) |
| Increment           | 1 × (n−1)   |
| Final failed test   | 1           |
| Return              | 1           |

- Worst case (strictly increasing array) ⇒ 4n steps.
- Best case (first element already max) ⇒ 3n steps.
- Either way **Θ(n)**.

---

## 4  Active‑Operation Counting — `arrayMax` (Slide 5)

1. **Pick one operation** executed every pass: here the loop‑condition test `i < n`.
2. **Count its frequency**: *n* times (one per element plus final fail).
3. **Assert** that all other work is a constant number of primitives per pass.
4. Therefore `T(n)=c · n ∈ Θ(n)`.

*Why it works*: the constant bundle of work that accompanies each condition check cannot change the order of growth—only the multiplicative constant *c*.

---

## 5  Linear Loops (Slide 6)

Any loop whose counter changes by ±1 (or any constant) per iteration and whose body is O(1) ⇒ Θ(n).

## 6  Logarithmic Loops (Slide 7–9)

Counter multiplied/divided by a constant each time:

```c
for (i=1; i<n; i*=2) { … }
```

Number of iterations = ⌈log₂ n⌉ ⇒ Θ(log n).

## 7  Quadratic Nested Loops (Slide 10)

Independent inner/outer loops each Θ(n) ⇒ Θ(n²).

## 8  Dependent Quadratic Loops (Slide 11)

Inner loop runs 0…(i−1) ⇒ Σ₀^{n−1} i = n(n−1)/2 ≈ ½ n² ⇒ Θ(n²).

## 9  Worked Examples (Slides 12–14)

- **Matrix Sum**: two independent Θ(n) loops nested ⇒ 2 n² + 2 n + 1 ⇒ Θ(n²).
- **Prefix Averages**: dependent nest ⇒ 1.5 n² + 7.5 n + 3 ⇒ Θ(n²).
- **Binary Search**: halving window each pass ⇒ 5 log n + 9 ⇒ Θ(log n).

---

## 10  Statement Counting vs Active‑Operation Counting

| Aspect                                               | Statement Counting                                          | Active‑Op Counting                                                                                     |
| ---------------------------------------------------- | ----------------------------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| **What you do**                                      | Tally *every* primitive step.                               | Pick **one** operation repeated proportional to the work; multiply by a constant.                      |
| **Precision**                                        | Gives an *exact* linear formula (reveals constant factors). | Only provides order of growth (hides constants).                                                       |
| **Effort**                                           | Tedious for long code; error‑prone.                         | Quick; ideal under test‑time pressure.                                                                 |
| **When to use**                                      | • Profiling small‑n performance.                            |                                                                                                        |
| • Hardware‑level tuning.                             | • Proving Θ‑class on exams.                                 |                                                                                                        |
| • Large, nested code where constants are irrelevant. |                                                             |                                                                                                        |
| **Danger points**                                    | Must assume each primitive really is O(1).                  | Must ensure loop body truly is **O(1)** per pass; if it calls an O(n) sub‑routine, the shortcut fails. |

### Example Revisited

`arrayMax` best‑case count = 3n; worst‑case = 4n.  Active‑op count = *c · n*.  Both approaches agree on Θ(n).  The extra effort only exposes the “3” vs “4” constants.

---

## 11  What Does “Asymptotic” Mean?

An **asymptote** is a curve that another curve approaches arbitrarily closely as *n → ∞*.

> In algorithm analysis we say *T(n) is ****asymptotically**** f(n)* if the ratio T(n)/f(n) tends to a constant when n becomes very large.

### 11.1 Formal limit definitions

- **Big‑O:**  *T(n) ∈ O(f(n))*  iff ∃ c, n₀ s.t. 0 ≤ T(n) ≤ c · f(n) for all n ≥ n₀.
- **Big‑Theta:** *T(n) ∈ Θ(f(n))*  iff ∃ c₁, c₂, n₀ s.t. c₁ f(n) ≤ T(n) ≤ c₂ f(n) for all n ≥ n₀.
- **Big‑Omega:** *T(n) ∈ Ω(f(n))*  iff ∃ c, n₀ s.t. T(n) ≥ c · f(n) for n ≥ n₀.

### 11.2 Why we drop constants & lower‑order terms

They matter only for *small* n.  As *n* grows, `0.00001 n³` will eclipse `100 n²`—so asymptotics let us reason about scalability without drowning in details.

### 11.3 Visual intuition

Imagine plotting T(n) and c · f(n).  Beyond some n₀ the two curves are locked in a fixed vertical ratio.  That *tail* behaviour defines the asymptotic class.

---

## 12  Conclusion & Teaching Tips

- **Pick the right tool**: full statement counting when constants *matter*; active‑op when only the class matters.
- **Always confirm O(1) body cost** before using the shortcut.
- **Emphasise the "for large n" caveat** when introducing Big‑O to students; many early mistakes come from ignoring it.

---


