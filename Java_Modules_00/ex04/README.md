# Exercise 04 - Character Frequency Analyzer

## Overview
This exercise implements a character frequency analyzer that processes a text string, counts character occurrences, sorts them by frequency and alphabetical order, and displays the results as a vertical bar chart histogram.

## Algorithm
The program combines multiple complex algorithms:
1. **Character Frequency Counting**: Count occurrences of each character
2. **Data Structure Conversion**: Convert array data to linked list
3. **Sorting Algorithm**: Bubble sort with dual criteria
4. **Data Visualization**: Create vertical ASCII histogram

## Code Architecture
```
Program.java
├── CharList class (Inner class)
│   ├── Fields: char c, int num, CharList next
│   └── Constructor
├── fillNodes() method
│   └── Convert frequency array to linked list
├── bubbleSort() method
│   └── Sort by frequency and alphabetical order
├── printResult() method
│   └── Generate vertical histogram
└── main() method
    ├── Input processing
    ├── Frequency counting
    ├── Data structure operations
    └── Result display
```

## Data Structures Used

### 1. **Frequency Array**
```java
int[] storedChar = new int[65536];  // Unicode character support
```
- **Purpose**: Count character frequencies
- **Size**: 65536 to support full Unicode Basic Multilingual Plane
- **Access**: O(1) character frequency lookup

### 2. **Linked List (CharList)**
```java
static class CharList {
    char c;        // Character
    int num;       // Frequency count
    CharList next; // Next node reference
}
```
- **Purpose**: Store and sort character-frequency pairs
- **Benefits**: Dynamic size, easy sorting, sequential access

## Java Concepts Learned

### 1. **Advanced Data Structures**
- **Array Indexing**: Using character ASCII values as array indices
- **Linked Lists**: Custom implementation with sorting capabilities
- **Data Structure Conversion**: Array to linked list transformation

### 2. **Character and String Processing**
- **String to Character Array**: `toCharArray()` method
- **Character Manipulation**: Working with char data type
- **Unicode Support**: Handling extended character sets

### 3. **Sorting Algorithms**
- **Bubble Sort Implementation**: Custom sorting with multiple criteria
- **Comparative Sorting**: Sorting by two different attributes
- **In-place Sorting**: Modifying linked list nodes during sort

### 4. **Advanced Output Formatting**
- **2D Visualization**: Creating coordinate-based output
- **Proportional Scaling**: Mapping values to visual representation
- **ASCII Art**: Creating charts with text characters

### 5. **Mathematical Calculations**
- **Proportional Scaling**: `oneHash = 10.0 / maxCount`
- **Coordinate Mapping**: Converting values to chart positions
- **Type Casting**: Managing double to int conversions

## Algorithms Used

### 1. **Character Frequency Algorithm**
```java
for (int i = 0; i < inputArr.length; i++) {
    char c = inputArr[i];
    storedChar[c]++;  // Use character as array index
}
```

### 2. **Bubble Sort with Dual Criteria**
```java
if ((currentNode.num < currentNode.next.num) || 
    (currentNode.num == currentNode.next.num && currentNode.c > currentNode.next.c)) {
    // Swap nodes
}
```
**Sorting Priority:**
1. **Primary**: Frequency (descending)
2. **Secondary**: Alphabetical (ascending)

### 3. **Vertical Histogram Algorithm**
```java
double oneHash = 10.0 / maxCount;  // Scale factor
for (int y = 10; y >= 0; y--) {    // Top to bottom
    for (int x = 0; x < 10; x++) { // Left to right
        double numHash = oneHash * (double) temp.num;
        if ((int) numHash == y)
            System.out.print(temp.num);  // Show count
        if ((int) numHash > y)
            System.out.print("# ");     // Show bar
        else
            System.out.print(" ");      // Empty space
    }
}
```

## Visualization Logic

### Chart Structure
- **Height**: 11 rows (0-10)
- **Width**: Up to 10 characters (top 10 most frequent)
- **Scaling**: Proportional to maximum frequency
- **Elements**:
  - `#` for histogram bars
  - Numbers for exact frequency values
  - Characters displayed at bottom

### Scaling Algorithm
```
Scale Factor = 10 / Maximum Frequency
Bar Height = (Character Frequency × Scale Factor)
```

## Algorithm Complexity
- **Character Counting**: O(n) where n is string length
- **List Construction**: O(k) where k is unique characters
- **Bubble Sort**: O(k²) for k unique characters
- **Visualization**: O(10 × 10) = O(100) = O(1)
- **Overall**: O(n + k²) where k ≤ min(n, 65536)

## Memory Usage
- **Frequency Array**: 65536 × 4 bytes = 256KB
- **Linked List**: Variable based on unique characters
- **Input Storage**: String length dependent

## Example Execution
```
Input: "AAABBC"

Character Frequencies:
A: 3, B: 2, C: 1

After Sorting (frequency desc, then alphabetical):
A: 3, B: 2, C: 1

Visual Output:
3      
#      
# 2    
# #    
# # 1  
# # #  
A B C  
```

## Key Programming Principles
- **Data Structure Selection**: Choosing optimal structures for different operations
- **Algorithm Composition**: Combining multiple algorithms effectively
- **Scalable Design**: Supporting Unicode character set
- **Visual Data Representation**: Converting data to meaningful charts
- **Memory Efficiency**: Balancing space and time complexity

## Advanced Features
- **Unicode Support**: Handles international characters
- **Proportional Scaling**: Adapts to different frequency ranges
- **Multi-criteria Sorting**: Sophisticated ordering logic
- **Efficient Counting**: O(1) character frequency updates

## Learning Outcomes
This exercise teaches:
- **Advanced Data Structures**: Linked lists with custom sorting
- **Character Processing**: Working with character data and Unicode
- **Complex Sorting**: Multi-criteria sorting algorithms
- **Data Visualization**: Creating ASCII-based charts and graphs
- **Algorithm Integration**: Combining multiple algorithms seamlessly
- **Memory Management**: Handling large arrays and dynamic structures

This exercise demonstrates sophisticated programming techniques including custom data structures, complex sorting algorithms, and advanced visualization methods.
