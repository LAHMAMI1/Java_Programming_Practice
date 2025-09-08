# Exercise 01 - Prime Number Checker

## Overview
This exercise implements a prime number checker that determines if a user-input number is prime and counts the number of iterations needed to make this determination.

## Algorithm
The program uses an optimized prime checking algorithm:
1. Read input number from user
2. Validate input (must be ≥ 2)
3. Check divisibility from 2 to √n
4. If any divisor found, number is composite
5. If no divisors found, number is prime
6. Count and return the number of iterations performed

## Code Architecture
```
Program.java
└── main() method
    ├── Input handling with Scanner
    ├── Input validation
    ├── Prime checking algorithm
    │   ├── Calculate square root limit
    │   ├── Loop through potential divisors
    │   └── Count iterations
    └── Output result with iteration count
```

## Java Concepts Learned

### 1. **Input Handling**
- `Scanner` class for user input
- `hasNextInt()` for input validation
- `nextInt()` for integer reading
- Resource management with `close()`

### 2. **Error Handling**
- Input validation and error messages
- `System.err.println()` for error output
- `System.exit(-1)` for error termination

### 3. **Mathematical Operations**
- `Math.sqrt()` for square root calculation
- Type casting `(int)` for double to integer conversion
- Modulo operator for divisibility testing

### 4. **Optimization Techniques**
- Only checking up to √n for efficiency
- Early termination when divisor is found

### 5. **Control Flow**
- Conditional statements (`if-else`)
- For loops with early exit
- Boolean logic for decision making

## Algorithm Complexity
- **Time Complexity**: O(√n) - only check divisors up to square root
- **Space Complexity**: O(1) - constant space usage

## Prime Checking Logic
The algorithm is based on the mathematical principle that if a number n has a divisor greater than √n, it must also have a corresponding divisor less than √n. Therefore, we only need to check up to √n.

## Input Validation
- **Type Check**: Ensures input is an integer
- **Range Check**: Number must be ≥ 2 (smallest prime)
- **Error Handling**: Graceful exit with error messages

## Example Executions
```
Input: 7
Process: Check divisors 2, 3 (up to √7 ≈ 2.6)
No divisors found
Output: true 2

Input: 9
Process: Check divisor 2 (no), check divisor 3 (yes, 9%3=0)
Divisor found at iteration 2
Output: false 2
```

## Key Programming Principles
- **Input Validation**: Always validate user input
- **Algorithm Optimization**: Use mathematical properties to reduce complexity
- **Resource Management**: Properly close Scanner objects
- **Error Handling**: Provide meaningful error messages
- **Early Termination**: Exit loops when result is determined

This exercise teaches fundamental concepts of input handling, mathematical algorithms, and optimization techniques in Java.
