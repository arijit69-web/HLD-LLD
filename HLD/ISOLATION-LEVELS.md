# Database Isolation Levels — Complete Notes

## Story Setup: Food Delivery App 🍗

Throughout these notes, we use one running example:
- **App**: Food Delivery App (like Zomato/Swiggy)
- **Item**: Butter Chicken — 10 plates in stock, priced at ₹300
- **Characters**: Ram (customer, placing an order) and Shyam (kitchen/admin, updating stock & price)

---

## Part 1: The 3 Core Problems

### 🔴 Dirty Read
Reading data that hasn't been **committed (saved)** yet — it might get rolled back.

**Story**: Ram checks stock and sees "3 plates left" while Shyam is *in the middle* of an update (not saved). Shyam's update then **fails and rolls back** — real stock is still 10. Ram already acted on the wrong number.

**Timestamp view:**
```
10:00:00 → Ram's transaction starts
10:00:03 → Shyam starts updating stock (not committed yet)
10:00:04 → Ram reads stock → sees Shyam's UNCOMMITTED value ❌
10:00:05 → Shyam's update FAILS/rolls back
10:00:10 → Ram's transaction ends (acted on wrong data)
```

**One-liner**: "Believing a rumor before it's confirmed."

---

### 🟡 Non-Repeatable Read
Reading the **same row** twice in one transaction gives **different values** — because someone else UPDATED and committed it in between.

**Story**: Ram opens the item page → price = ₹300. While Ram is still deciding, Shyam updates and saves the price to ₹350. Ram refreshes → now sees ₹350.

**Timestamp view:**
```
10:00:00 → Ram reads price = ₹300
10:00:03 → Shyam updates price → ₹350, COMMITS
10:00:06 → Ram reads AGAIN → sees ₹350
```

**Key point**: Same row, value changed → caused by **UPDATE**.

**One-liner**: "Same person, new face."

---

### 🟢 Phantom Read
Running the **same query** twice gives a **different number of rows** — because someone INSERTED or DELETED rows in between.

**Story**: Ram searches "items under ₹300" → gets 5 dishes. Shyam adds a discount, so 2 more dishes now qualify. Ram runs the same search again → gets 7 dishes.

**Timestamp view:**
```
10:00:00 → Ram's query "items < ₹300" → 5 rows
10:00:04 → Shyam adds discount → 2 more dishes qualify, COMMITS
10:00:08 → Ram runs SAME query again → 7 rows
```

**Key point**: Row count changed → caused by **INSERT/DELETE**.

**One-liner**: "New people walked into the room."

---

### Non-Repeatable Read vs Phantom Read — The Key Difference

| | What you're tracking | Caused by |
|---|---|---|
| **Non-Repeatable Read** | ONE row's value | UPDATE |
| **Phantom Read** | COUNT/SET of rows | INSERT or DELETE |

---

## Part 2: The 4 Isolation Levels

Low isolation = fast but risky. High isolation = safe but slow.

### 1. Read Uncommitted (Lowest — Fastest)
Sees everything, even unsaved changes. All 3 problems can happen.

```
10:00:00 → Ram's transaction starts
10:00:03 → Shyam starts updating stock (not committed yet)
10:00:04 → Ram reads stock → sees Shyam's UNCOMMITTED value ❌
10:00:05 → Shyam's update FAILS/rollback
10:00:10 → Ram's transaction ends (acted on wrong data)
```
**Analogy**: "I'll believe anything, even rumors." 🙈

---

### 2. Read Committed (Most common default — MySQL, PostgreSQL)
Only sees confirmed/saved data. Dirty Read ✅ solved. Non-Repeatable & Phantom Read still possible.

```
10:00:00 → Ram's transaction starts, reads stock = 10
10:00:03 → Shyam updates stock to 8 and COMMITS
10:00:04 → Ram reads AGAIN → now sees 8 (changed mid-transaction) ⚠️
10:00:05 → Ram places order for 2 plates → deducts from 8 → stock = 6
10:00:06 → Ram's transaction ends, stock = 6 (committed)
```

**Important**: Ram's transaction always works on top of the **latest committed value**. If Shyam commits again before Ram finishes, Ram sees yet another value on his next read:
```
10:00:05 → Shyam updates AGAIN → 5 (committed)
10:00:06 → Ram reads AGAIN → sees 5 ⚠️ (changed again!)
```
This is exactly why it's called "non-repeatable."

**Analogy**: "I only believe confirmed news, but news can change." 📰

---

### 3. Repeatable Read
Once your transaction starts, your **reads** stay frozen (snapshot) — same values every time, no matter what others commit. Dirty Read ✅ + Non-Repeatable Read ✅ solved. Phantom Read mostly (DB-dependent) prevented.

```
10:00:00 → Ram's transaction starts, reads stock = 10 (SNAPSHOT locked)
10:00:03 → Shyam updates stock to 8 and COMMITS
10:00:04 → Ram reads AGAIN → still sees 10 (frozen snapshot) ✅
10:00:10 → Ram's transaction ends
10:00:11 → NOW Ram sees updated stock = 8 (only after his transaction ends)
```

**Analogy**: "Once I start reading today's newspaper, it won't change mid-read." 📖

---

### 4. Serializable (Highest — Slowest)
Full lock — transactions happen one at a time, like a queue. All 3 problems ✅ solved.

```
10:00:00 → Ram's transaction starts, LOCKS the Butter Chicken row
10:00:03 → Shyam tries to update stock → BLOCKED, must wait ⏳
10:00:10 → Ram's transaction ends, lock released
10:00:11 → Shyam's update NOW goes through
```

**Analogy**: "Only one person can touch the newspaper at a time." 🔒

---

## Part 3: Isolation Level Comparison Table

| Level | Dirty Read | Non-Repeatable Read | Phantom Read | Speed |
|---|---|---|---|---|
| Read Uncommitted | ❌ Possible | ❌ Possible | ❌ Possible | Fastest |
| Read Committed | ✅ Prevented | ❌ Possible | ❌ Possible | Fast |
| Repeatable Read | ✅ Prevented | ✅ Prevented | ❌ Mostly possible | Slower |
| Serializable | ✅ Prevented | ✅ Prevented | ✅ Prevented | Slowest |

(✅ = prevented, ❌ = can still happen)

---

## Part 4: The Lost Update Problem (Important Gotcha!)

Even Repeatable Read isn't 100% foolproof for **writes** — it only freezes what you *read*. What actually gets saved depends on **how the write is coded**.

### Case 1: Relative update → SAFE ✅
```sql
stock = stock - 2
```
```
10:00:00 → Ram reads stock = 10 (frozen for reading)
10:00:03 → Shyam updates stock to 8, COMMITS
10:00:05 → Ram places order: DB uses REAL current value (8) - 2 = 6
10:00:10 → Ram's transaction ends, final stock = 6 ✅
```
The database engine applies the deduction on the actual current value at write-time, not Ram's stale read.

### Case 2: Math done on stale read → DANGEROUS ❌
```sql
stock = 10 - 2   -- using Ram's own frozen read of "10"
```
```
10:00:00 → Ram reads stock = 10
10:00:03 → Shyam updates stock to 8, COMMITS
10:00:05 → Ram calculates 10 - 2 = 8, writes stock = 8 ❌
10:00:10 → Ram's transaction ends, stock = 8
```
Shyam's update (8) gets **completely overwritten** — this is the **Lost Update Problem**. Real stock should've been 6, but shows 8.

### Takeaway Table

| Deduction Type | Final Stock | Safe? |
|---|---|---|
| `stock = stock - 2` (relative, DB-handled) | 6 | ✅ Safe |
| `stock = 10 - 2` (based on stale app-level read) | 8 | ❌ Lost Update |

**Lesson**: Use relative updates (`UPDATE ... SET stock = stock - 2`) or explicit locking (`SELECT ... FOR UPDATE`) instead of doing math in application code on a possibly-stale value.

---

## Part 5: Does the Reader "Wait" in Read Committed?

**The one guarantee Read Committed always gives**: it will NEVER read uncommitted (dirty) data. But whether the reader *waits* for the writer to commit, or just *reads the old value instantly*, depends on the database's underlying mechanism.

### Mechanism 1: Lock-Based DBs (e.g., SQL Server default)
Reader **WAITS** until the writer commits (or rolls back), then reads the new value.

```
10:00:00 → Shyam starts updating stock (not committed)
10:00:01 → Ram tries to read → WAITS ⏳ (blocked)
10:00:05 → Shyam COMMITS
10:00:05 → Ram's read unblocks → reads NEW value ✅
```

### Mechanism 2: MVCC — Multi-Version Concurrency Control (PostgreSQL, MySQL InnoDB — most common today)
Reader does **NOT wait** — it immediately reads the **last committed (old) value**, completely ignoring the in-progress uncommitted change.

```
10:00:00 → Shyam starts updating stock (not committed)
10:00:01 → Ram reads IMMEDIATELY → gets OLD value (no wait) ✅
10:00:05 → Shyam COMMITS
10:00:06 → If Ram reads again NOW → gets NEW value
```

### Comparison

| | Lock-Based | MVCC |
|---|---|---|
| Does reader wait? | ✅ Yes, waits for commit | ❌ No, doesn't wait |
| Does reader see uncommitted data? | ❌ Never | ❌ Never |
| What does reader get? | New value (after waiting) | Old committed value (instantly) |

**Bottom line**: In BOTH mechanisms, the reader never sees dirty/uncommitted data — that part is guaranteed by definition of "Read Committed." The only difference is *how* it's achieved. Since most modern databases (PostgreSQL, MySQL) use MVCC, in practice: **your reader usually does NOT wait** — it reads the last committed value immediately, and only picks up the new value on its *next* read (after the writer commits).

---

## Part 6: Quick Memory Tricks

```
Read Uncommitted  →  🙈 believes everything (rumors)
Read Committed    →  📰 believes only confirmed news
Repeatable Read   →  📖 newspaper frozen once you start reading
Serializable      →  🔒 one person touches the newspaper at a time
```

```
Non-Repeatable Read = "Same person, new face"     (UPDATE)
Phantom Read        = "New people walked in"       (INSERT/DELETE)
```

## Part 7: Golden Rule

```
Low Isolation   →  Fast, but risky (more problems can happen)
High Isolation  →  Safe, but slow (transactions wait for each other)
```

**Real-world practice**: Most apps use **Read Committed** as default for everyday browsing (fast). For critical steps like final payment/stock deduction (e.g., last plate of Butter Chicken), apps use **Serializable** or explicit row-level locking to avoid overselling.
