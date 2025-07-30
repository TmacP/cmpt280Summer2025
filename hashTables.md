# CMPT 280 Tutorial: Hash Tables

### Collision Resolution using Open Addressing

#### General formula

- **Hash array size**: \$N\$
- **Hash function**: \$h(k)\$
- **Probe increment**: \$p(j)\$
- **Location of j-th alternative array offset**: \$(h(k) + p(j)) \mod N\$

#### Linear Probing

- **Hash function**: `item value mod 10`
- **Probe increment**: \$p(j) = j\$

**Insertions**: 59, 42, 92, 102, 29, 39, 62

```
Index:  0  1  2  3  4  5  6  7  8  9
Value: 42 92 62  -  -  -  - 29 59 39
(102 collides with 2 -> next free slot at 3)
```

#### Quadratic Probing

- **Hash function**: `item value mod 10`
- **Probe increment**: \$p(j) = (-1)^{i-1}\left(\frac{i+1}{2}\right)^2\$

**Insertions**: 59, 42, 92, 102, 29, 39, 62

```
Index:  0  1  2  3  4  5  6  7  8  9
Value:  -  - 62 92  -  - 42 29 59 39
(102 collides at 2 -> probes to 3, 1, then placed at 3)
```

#### Double Hashing

- **Hash function**: `item value mod 10`
- **Probe increment**: \$p(j) = ((k \mod 7) + 1) \times j\$

**Insertions**: 59, 42, 92, 102, 29, 39, 62

```
Index:  0  1  2  3  4  5  6  7  8  9
Value:  -  - 92 62  -  - 42 29 59 39
(102 collides at 2 -> probes by increment 5, then placed at 7)
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

Keys map to buckets; buckets store linked lists of items.
```

#### Implementation in lib280

**KeyedChainedHashTable280 class:**

```java
public class KeyedChainedHashTable280<K extends Comparable<? super K>, I extends Keyed280<K>>
    extends HashTable280<I>
    implements KeyedDict280<K, I>
```

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

### Relevant Java Code Snippet (from provided files)

#### Example implementation of hash function:

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

#### Obtaining an item by key:

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

### Other Features

- Dynamic resizing (automatic increase based on load factor).
- Internal cursor for iterating (`goFirst()`, `itemExists()`, `goForth()`, etc.).
