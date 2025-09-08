# Exercise 00 - Sum of Digits

## Overview
This exercise demonstrates basic Java programming concepts by calculating the sum of digits of a specific number (479598).

## Algorithm
The program uses a simple digit extraction algorithm:
1. Extract the last digit using modulo operation (`num % 10`)
2. Add the digit to the running sum
3. Remove the last digit by integer division (`num /= 10`)
4. Repeat for all 6 digits

## Code Architecture
```
Program.java
└── main() method
    ├── Variable initialization (num, sum)
    ├── For loop (6 iterations)
    │   ├── Extract digit with modulo
    │   ├── Add to sum
    │   └── Remove digit with division
    └── Output result
```

## Java Concepts Learned

### 1. **Basic Class Structure**
- Public class declaration
- Static main method as entry point

### 2. **Variables and Data Types**
- `int` primitive data type for integers
- Variable initialization and assignment

### 3. **Arithmetic Operations**
- Modulo operator (`%`) for remainder calculation
- Integer division (`/=`) for digit removal
- Addition assignment (`+=`)

### 4. **Control Structures**
- `for` loop with counter variable
- Loop initialization, condition, and increment

### 5. **Input/Output**
- `System.out.println()` for console output
- `System.exit(0)` for program termination

## Algorithm Complexity
- **Time Complexity**: O(n) where n is the number of digits (6 in this case)
- **Space Complexity**: O(1) - constant space usage

## Key Programming Principles
- **Sequential Processing**: Operations performed in order
- **Mathematical Operations**: Using arithmetic to manipulate numbers
- **Fixed Iteration**: Known number of loop iterations

## Example Execution
```
Input: 479598 (hardcoded)
Process: 8 + 9 + 5 + 9 + 7 + 4 = 42
Output: 42
```

This exercise serves as an introduction to Java syntax, basic arithmetic operations, and simple loop structures.
