# Exercise 03 - Student Performance Tracker

## Overview
This exercise implements a student performance tracking system that processes weekly academic data, stores minimum grades for each week using a linked list, and displays a visual chart representation of the performance over time.

## Algorithm
The program uses a linked list-based data structure to manage weekly student performance:
1. **Input Processing**: Read week identifiers and validate sequence
2. **Grade Processing**: Process 5 grades per week and find minimum
3. **Data Storage**: Store week number and minimum grade in linked list nodes
4. **Visualization**: Display performance chart using ASCII characters

## Code Architecture
```
Program.java
├── WeekNode class (Inner class)
│   ├── Fields: weekNumber, minGrade, next
│   └── Constructor
├── printResult() method
│   └── Chart visualization logic
└── main() method
    ├── Input validation loop
    ├── Grade processing
    ├── Linked list construction
    └── Result display
```

## Data Structure: Linked List

### WeekNode Structure
```java
static class WeekNode {
    int weekNumber;    // Week identifier (1-18)
    int minGrade;      // Minimum grade for the week
    WeekNode next;     // Reference to next node
}
```

### Linked List Operations
- **Insertion**: Add new nodes at the tail
- **Traversal**: Iterate through all nodes for display
- **Construction**: Build list dynamically during input processing

## Java Concepts Learned

### 1. **Object-Oriented Programming**
- **Inner Classes**: `WeekNode` as a static nested class
- **Encapsulation**: Class fields and methods
- **Constructor**: Parameterized constructor for node creation

### 2. **Data Structures**
- **Linked Lists**: Dynamic data structure implementation
- **Node-based Storage**: Creating and linking nodes
- **Reference Management**: Using `next` pointers
- **Head and Tail Pointers**: Efficient list construction

### 3. **Input Processing and Validation**
- **String Comparison**: Validating week format
- **Sequential Validation**: Ensuring proper week order
- **Multiple Scanner Objects**: Different scanners for different input types
- **Input Parsing**: Processing structured input data

### 4. **Algorithm Implementation**
- **Minimum Finding**: Finding minimum among 5 grades
- **List Construction**: Building linked list incrementally
- **Visualization Algorithm**: Converting data to ASCII art

### 5. **Memory Management**
- **Dynamic Allocation**: Creating nodes as needed
- **Reference Handling**: Managing object references
- **Resource Cleanup**: Proper scanner closure

## Algorithms Used

### 1. **Minimum Grade Algorithm**
```java
int min = 9;  // Start with maximum possible grade
for (int j = 0; j < 5; j++) {
    int grade = scGrades.nextInt();
    if (grade < min)
        min = grade;
}
```

### 2. **Linked List Construction Algorithm**
```java
WeekNode node = new WeekNode(i, min);
if (head == null) {
    head = node;
    tail = node;
} else {
    tail.next = node;
    tail = node;
}
```

### 3. **Visualization Algorithm**
```java
// For each node, print week info and visual representation
System.out.print("Week " + currentNode.weekNumber + " ");
for (int i = 0; i < currentNode.minGrade; i++)
    System.out.print("=");
System.out.println(">");
```

## Input Format and Validation
- **Week Format**: "Week X" where X is sequential (1-18)
- **Termination**: Input "42" to stop processing
- **Grade Range**: Assuming grades 0-9 based on min initialization
- **Error Handling**: Validates week order and format

## Visualization Logic
The program creates a horizontal bar chart where:
- Each week is displayed on a separate line
- Week number is shown as "Week X"
- Number of equals signs (=) represents the minimum grade
- Arrow (>) terminates each bar

## Algorithm Complexity
- **Time Complexity**: O(n) for list construction and O(n) for display, where n is number of weeks
- **Space Complexity**: O(n) for storing n week nodes
- **Input Processing**: O(w × g) where w is weeks and g is grades per week (5)

## Example Execution
```
Input:
Week 1
5 7 3 8 6
Week 2  
8 9 4 7 6
42

Process:
- Week 1: min(5,7,3,8,6) = 3
- Week 2: min(8,9,4,7,6) = 4

Output:
Week 1 ===>
Week 2 ====>
```

## Key Programming Principles
- **Data Structure Design**: Choosing appropriate data structure for the problem
- **Modular Programming**: Separating concerns into different methods
- **Input Validation**: Robust input checking and error handling
- **Dynamic Memory**: Creating objects as needed
- **Visualization**: Converting data to human-readable format

## Learning Outcomes
This exercise teaches:
- **Linked List Implementation**: Understanding pointer-based data structures
- **Object-Oriented Design**: Using classes and constructors effectively
- **Input Processing**: Handling complex input formats with validation
- **Data Visualization**: Converting numerical data to visual representation
- **Memory Management**: Dynamic object creation and reference handling

This exercise demonstrates practical application of linked lists for data storage and retrieval, combined with input validation and data visualization techniques.
