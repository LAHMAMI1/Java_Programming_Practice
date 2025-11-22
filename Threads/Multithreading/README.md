# Parallel Array Sum - Data Parallelism with Threads

## Overview
This exercise demonstrates **data parallelism** by splitting a large computational task across multiple threads. It implements parallel array summation, showcasing how to divide work, distribute among threads, and aggregate results.

## Problem Statement
Create a multithreaded application that:
- Generates a large array of random integers
- Divides the array into equal segments
- Calculates partial sums using multiple threads
- Aggregates results to verify correctness
- Compares parallel computation with sequential sum

## Program Architecture

### Class Structure
```
Multithreading/
├── Program.java     # Main orchestration and array management
└── SumThread.java   # Worker thread for partial sum calculation
```

### Component Responsibilities

#### **Program.java** (Main Controller)
- Parses and validates command-line arguments
- Generates random array with configurable size
- Calculates reference sum (sequential)
- Partitions array into equal segments
- Creates and manages worker threads
- Aggregates partial results

#### **SumThread.java** (Worker Thread)
- Extends `Thread` class
- Computes sum for assigned array segment
- Stores result in shared results array
- Reports computation details

## How It Works

### Array Partitioning Algorithm

**Ceiling Division** (ensures all elements are covered):
```java
int sectionSize = (size + count - 1) / count;  // Rounds up

int[][] range = new int[count][2];
for (int j = 0; j < count; j++) {
    range[j][0] = j * sectionSize;              // Start index
    range[j][1] = Math.min((j + 1) * sectionSize, size);  // End index
}
```

**Example** (size=10, count=3):
```
sectionSize = (10 + 3 - 1) / 3 = 12 / 3 = 4

Thread 1: [0, 4)   → elements 0,1,2,3
Thread 2: [4, 8)   → elements 4,5,6,7
Thread 3: [8, 10)  → elements 8,9
```

**Why Min()?**
- Last segment might be smaller
- Prevents array index out of bounds
- Ensures all elements are processed exactly once

## Core Java Concepts Learned

### 1. **Data Parallelism**

**Definition**: Distributing data across multiple processing units

**Pattern**:
```
Large Task → Split into Chunks → Process in Parallel → Merge Results
```

### 2. **Shared Memory Communication**

**Results Array** (shared between threads):
```java
int[] results = new int[count];  // Shared array
SumThread thread = new SumThread(arr, start, end, results, threadIndex);
```

**Why This Works**:
- Each thread writes to unique index (no race condition)
- No synchronization needed for writes
- Main thread reads after all threads complete

### 3. **Thread Join Pattern**

**Waiting for All Threads**:
```java
for (SumThread thread : threads) {
    try {
        thread.join();  // Block until this thread completes
    } catch (InterruptedException e) {
        System.err.println("Error: join() " + e.getMessage());
    }
}
```

**Purpose**:
- Ensures all partial sums are computed
- Prevents reading incomplete results
- Synchronization barrier

### 4. **Random Number Generation**

```java
Random random = new Random();
for (i = 0; i < size; i++)
    arr[i] = random.nextInt(1001);  // Range: 0-1000 inclusive
```

**Configurable Range**:
- Upper bound: 1001 (exclusive) = 0-1000 range
- Predictable for testing

### 5. **Stream API for Verification**

```java
int sum = Arrays.stream(arr).sum();
```

**Modern Java**:
- Functional approach to array operations
- Built-in parallel streams available (`parallel()`)
- Concise verification code

## Usage

### Compilation
```bash
cd Threads/Multithreading
javac *.java
```

### Execution
```bash
java Program --arraySize=<SIZE> --threadsCount=<THREADS>
```

### Constraints
- `arraySize`: 1 to 2,000,000 (memory limit)
- `threadsCount`: 1 to arraySize (must be positive)

### Examples

#### Small Array with 2 Threads
```bash
java Program --arraySize=10 --threadsCount=2
```

**Output**:
```
Sum: 5234
Thread 1: from 0 to 4 sum is 2156
Thread 2: from 5 to 9 sum is 3078
Sum by threads: 5234
```

#### Large Array with 4 Threads
```bash
java Program --arraySize=1000000 --threadsCount=4
```

**Output**:
```
Sum: 500473829
Thread 1: from 0 to 249999 sum is 125118456
Thread 2: from 250000 to 499999 sum is 125089234
Thread 3: from 500000 to 749999 sum is 125132867
Thread 4: from 750000 to 999999 sum is 125133272
Sum by threads: 500473829
```

#### Performance Comparison
```bash
# Sequential (1 thread)
time java Program --arraySize=2000000 --threadsCount=1

# Parallel (4 threads)
time java Program --arraySize=2000000 --threadsCount=4
```

**Expected Results** (approximate):
- 1 thread: 100ms
- 4 threads: 40-60ms (1.7-2.5× speedup)

## Key Learning Outcomes

### ✅ Parallel Algorithm Design
- Identifying parallelizable tasks
- Data partitioning strategies
- Load balancing across threads

### ✅ Thread Management
- Creating multiple threads dynamically
- Managing thread lifecycle (start, join)
- Array-based thread management

### ✅ Synchronization Concepts
- Understanding when synchronization is NOT needed
- Using join() as a synchronization barrier
- Avoiding race conditions through design

### ✅ Performance Analysis
- Sequential vs parallel execution
- Measuring speedup
- Understanding overhead costs

### ✅ Practical Algorithms
- Ceiling division for equal partitioning
- Handling non-divisible array sizes
- Aggregating distributed results

---

This exercise provides essential experience with parallel computing, teaching how to decompose problems, distribute work, and aggregate results - fundamental skills for high-performance computing and big data processing.
