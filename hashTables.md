# CMPT 280 Tutorial: Hash Tables

### Collision Resolution using Open Addressing

#### General formula

- **Hash array size**: \$N\$
- **Hash function**: \$h(k)\$
- **Probe increment**: \$p(j)\$
- **Location of j-th alternative array offset**: \$(h(k) + p(j)) \mod N\$

---

### Linear Probing

- **Hash function**: `item value mod 10`
- **Probe increment**: \$p(j) = j\$

**Insertions**: 59, 42, 92, 102, 29, 39, 62

#### Step-by-step Insertions:

| Item | Hash         | Initial Index | Steps Taken                     | Final Index |
| ---- | ------------ | ------------- | ------------------------------- | ----------- |
| 59   | 59 % 10 = 9  | 9             | 9 (empty)                       | 9           |
| 42   | 42 % 10 = 2  | 2             | 2 (empty)                       | 2           |
| 92   | 92 % 10 = 2  | 2             | 2 (taken), 3 (empty)            | 3           |
| 102  | 102 % 10 = 2 | 2             | 2 (taken), 3 (taken), 4 (empty) | 4           |
| 29   | 29 % 10 = 9  | 9             | 9 (taken), 0 (empty)            | 0           |
| 39   | 39 % 10 = 9  | 9             | 9 (taken), 0 (taken), 1 (empty) | 1           |
| 62   | 62 % 10 = 2  | 2             | 2,3,4 taken, 5 (empty)          | 5           |

**Final Array:**

```
Index:  0   1   2   3   4   5   6   7   8   9
Value: 29  39  42  92 102  62   -   -   -  59
```

---

### Quadratic Probing

- **Hash function**: `item value mod 10`
- **Probe increment**: \$p(j) = (-1)^{i-1}\left(\frac{i+1}{2}\right)^2\$

**Insertions**: 59, 42, 92, 102, 29, 39, 62

#### Step-by-step Insertions with Calculations:

| Item | Hash | Initial Index | Calculations for Probes                                                                                        | Steps Taken                                | Final Index |
| ---- | ---- | ------------- | -------------------------------------------------------------------------------------------------------------- | ------------------------------------------ | ----------- |
| 59   | 9    | 9             | \$9\$                                                                                                          | 9 (empty)                                  | 9           |
| 42   | 2    | 2             | \$2\$                                                                                                          | 2 (empty)                                  | 2           |
| 92   | 2    | 2             | \$2\$, \$2 + (-1)^0(\frac{2}{2})^2 = 3\$                                                                       | 2 (taken), 3 (empty)                       | 3           |
| 102  | 2    | 2             | \$2\$, \$2 + (-1)^0(\frac{2}{2})^2 = 3\$, \$2 + (-1)^1(\frac{3}{2})^2 = 1\$                                    | 2 (taken), 3 (taken), 1 (empty)            | 1           |
| 29   | 9    | 9             | \$9\$, \$9 + (-1)^0(\frac{2}{2})^2 = 0\$                                                                       | 9 (taken), 0 (empty)                       | 0           |
| 39   | 9    | 9             | \$9\$, \$9 + (-1)^0(\frac{2}{2})^2 = 0\$, \$9 + (-1)^1(\frac{3}{2})^2 = 8\$                                    | 9 (taken), 0 (taken), 8 (empty)            | 8           |
| 62   | 2    | 2             | \$2\$, \$2 + (-1)^0(\frac{2}{2})^2 = 3\$, \$2 + (-1)^1(\frac{3}{2})^2 = 1\$, \$2 + (-1)^2(\frac{4}{2})^2 = 6\$ | 2 (taken), 3 (taken), 1 (taken), 6 (empty) | 6           |

**Final Array:**

```
Index:  0   1   2   3   4   5   6   7   8   9
Value: 29 102  42  92   -   -  62   -  39  59
```

---

### Chained Hash Tables

#### Conceptual View

```
Buckets:    Keys/Values
0
1
2           x Finn
3
4           x Ice King
5
6           x Beemo
7
8           x Jake
9           x Prismo

(Keys map to buckets; buckets store linked lists of items.)
```

#### Implementation in lib280

**KeyedChainedHashTable280 class:**

```java
public class KeyedChainedHashTable280<K extends Comparable<? super K>, I extends Keyed280<K>>
    extends HashTable280<I>
    implements KeyedDict280<K, I>
```

- `hashPos(K key)` calculates positions.
- Collision handled by chaining with linked lists.
- Two type parameters: `K` (key type) and `I` (item type).
- Extends abstract class `HashTable280`.
- Implements `KeyedDict280<K, I>`.

#### Keyed Dictionaries

- Allow querying, inserting, and deleting using keys.
- Items must have unique keys.

#### KeyedChainedHashTable280 operations

Keyed operations:

- `obtain(k)` - Get item by key `k`.
- `delete(k)` - Delete item by key `k`.

Non-keyed operations (inherited):

- `obtain(i)` - Get item matching `i`.
- `delete(i)` - Delete item matching `i`.
- `insert(i)` - Insert item `i`.

#### Type parameters

- `K`: Comparable keys.
- `I`: Items must implement `Keyed280<K>` (enforces `key()` method).

#### Internal Structure

```java
protected LinkedList280<I>[] hashArray;
```

- Array of linked lists (`hashArray`), each bucket holding a linked list.
- Items mapped using a hash function (using Java's `hashCode()` method).

---

#### Hash function:

```java
protected int hashPos(K key) {
    return Math.abs(key.hashCode()) % hashArray.length;
}
```

#### Inserting an item:

```java
public void insert(I item) {
    int pos = hashPos(item.key());
    if (hashArray[pos] == null) {
        hashArray[pos] = new LinkedList280<I>();
    }
    hashArray[pos].insertFirst(item);
    itemCount++;
}
```

#### Obtaining an item:

```java
public I obtain(K key) throws ItemNotFound280Exception {
    int pos = hashPos(key);
    if (hashArray[pos] != null) {
        hashArray[pos].goFirst();
        while (hashArray[pos].itemExists()) {
            if (hashArray[pos].item().key().equals(key)) {
                return hashArray[pos].item();
            }
            hashArray[pos].goForth();
        }
    }
    throw new ItemNotFound280Exception("Item with key " + key + " not found.");
}
```

---

### Additional Features

- Dynamic resizing based on load factor.
- Internal cursor for iteration (`goFirst()`, `itemExists()`, etc.).
