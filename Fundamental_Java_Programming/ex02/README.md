# Exercise 02 - Coffee Request Counter

## Overview
This exercise implements a "coffee request" counter that processes a series of numbers, calculates the sum of their digits, checks if this sum is prime, and counts how many numbers have prime digit sums. The program terminates when 42 is entered.

## Algorithm
The program combines multiple algorithms:
1. **Input Loop**: Read numbers until 42 is encountered
2. **Digit Sum Calculation**: Extract and sum all digits of each number
3. **Prime Checking**: Determine if the digit sum is prime
4. **Counter**: Track numbers with prime digit sums

### Detailed Process:
1. Read integer input
2. Calculate number of digits using logarithm
3. Extract each digit and sum them
4. Check if sum is prime (excluding 1)
5. Increment counter if prime
6. Repeat until input is 42

## Code Architecture
```
Program.java
└── main() method
    ├── Variable initialization
    ├── Main loop (until num == 42)
    │   ├── Input validation
    │   ├── Digit sum calculation
    │   │   ├── Calculate number length
    │   │   └── Extract and sum digits
    │   ├── Prime checking algorithm
    │   │   └── Check divisibility up to √sum
    │   └── Counter increment
    └── Output final count
```

## Java Concepts Learned

### 1. **Loop Control**
- `while` loop with termination condition
- Complex loop conditions and nested logic
- Loop continuation and breaking logic

### 2. **Mathematical Functions**
- `Math.log10()` for logarithmic calculations
- `Math.floor()` for rounding down
- `Math.sqrt()` for square root
- Type casting between double and int

### 3. **Number Manipulation**
- Digit extraction using modulo and division
- Dynamic number length calculation
- Working with temporary variables

### 4. **Algorithm Combination**
- Combining multiple algorithms in sequence
- Digit sum → Prime check → Counter increment
- Modular programming approach

### 5. **Input Validation and Error Handling**
- Continuous input validation in loops
- Error handling for invalid inputs
- Graceful program termination

## Algorithms Used

### 1. **Digit Sum Algorithm**
```java
// Calculate number of digits
numLength = (int) Math.floor(Math.log10(num)) + 1;

// Extract and sum digits
for (int i = 0; i < numLength; i++) {
    sum += tempNum % 10;
    tempNum /= 10;
}
```

### 2. **Prime Checking Algorithm**
```java
// Check if sum is prime
for (int i = 2; i <= sqrtSum; i++) {
    if (sum % i == 0 || sum == 1)
        isPrime = false;
}
```

## Algorithm Complexity
- **Time Complexity**: O(n × (log(m) + √s)) 
  - n = number of inputs
  - m = average input value (for digit extraction)
  - s = average digit sum (for prime checking)
- **Space Complexity**: O(1) - constant space usage

## Special Cases Handled
- **Input 42**: Termination condition
- **Numbers < 2**: Invalid input error
- **Sum = 1**: Not considered prime
- **Non-integer input**: Error handling

## Example Execution
```
Input sequence: 123, 456, 42
Process:
- 123: digit sum = 1+2+3 = 6, not prime
- 456: digit sum = 4+5+6 = 15, not prime  
- 42: termination signal
Output: Count of coffee-request : 0

Input sequence: 29, 13, 42
Process:
- 29: digit sum = 2+9 = 11, prime ✓
- 13: digit sum = 1+3 = 4, not prime
- 42: termination signal
Output: Count of coffee-request : 1
```

## Key Programming Principles
- **Sentinel-Controlled Loops**: Using 42 as termination signal
- **Algorithm Composition**: Combining multiple algorithms
- **State Management**: Tracking counters across iterations
- **Input Validation**: Continuous validation in loops
- **Mathematical Problem Solving**: Converting requirements to algorithms

## Learning Outcomes
This exercise teaches:
- Complex loop structures and control flow
- Mathematical computations and number theory
- Algorithm composition and modular thinking
- Input validation in iterative processes
- State management in loops

This exercise demonstrates how to combine multiple algorithms to solve a complex problem involving number theory, input processing, and counting.
