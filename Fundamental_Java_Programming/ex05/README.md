# Exercise 05 - Student Attendance Management System

## Overview
This exercise implements a comprehensive student attendance management system that processes student information, class schedules, and attendance records to generate a detailed attendance report. The system demonstrates complex data management, input validation, and report generation.

## System Architecture
The program manages three interconnected data entities:
1. **Students**: Name and index management
2. **Class Schedules**: Time and day scheduling
3. **Attendance Records**: Individual attendance tracking

## Code Architecture
```
Program.java
├── Data Classes (Inner classes)
│   ├── StudentList: Student information
│   ├── CLassList: Class schedule information
│   └── AttendanceList: Attendance records
├── Input Processing Methods
│   ├── getStudents(): Process student data
│   ├── getSchedules(): Process class schedules
│   └── getAttendances(): Process attendance records
├── Utility Methods
│   └── getDayFromNumber(): Convert day numbers to day names
└── main(): Orchestrate system and generate report
```

## Data Structures

### 1. **StudentList (Linked List)**
```java
static class StudentList {
    String name;     // Student name (max 10 chars)
    int index;       // Student index
    StudentList next;// Next student reference
}
```

### 2. **CLassList (Linked List)**
```java
static class CLassList {
    String hour;     // Class hour (1-6)
    String day;      // Day of week (MO, TU, WE, TH, FR, SA, SU)
    CLassList next;  // Next class reference
}
```

### 3. **AttendanceList (Linked List)**
```java
static class AttendanceList {
    String studentName; // Student identifier
    String hour;        // Class hour
    String numDay;      // Day number (1-30)
    String presence;    // HERE or NOT_HERE
    AttendanceList next;// Next attendance record
}
```

## Java Concepts Learned

### 1. **Complex Data Management**
- **Multiple Linked Lists**: Managing three interconnected data structures
- **Data Relationships**: Cross-referencing between different entities
- **Data Integrity**: Ensuring consistency across data structures

### 2. **Advanced Input Validation**
- **Multi-level Validation**: Students, schedules, and attendance validation
- **Format Validation**: Checking input formats and constraints
- **Cross-reference Validation**: Ensuring data consistency between structures

### 3. **String Processing and Parsing**
- **String Manipulation**: Parsing complex input formats
- **Character Array Processing**: Manual string parsing
- **Format Validation**: Checking string patterns and formats

### 4. **Date and Time Handling**
- **Day Calculation**: Converting day numbers to day names
- **Calendar Logic**: Implementing day-of-week calculations
- **Modular Arithmetic**: Using modulo for cyclic day calculations

### 5. **Report Generation**
- **Formatted Output**: Creating structured reports
- **Data Correlation**: Matching attendance with students and schedules
- **Table Generation**: Creating tabular output with proper alignment

## Algorithms Used

### 1. **Day Conversion Algorithm**
```java
// September 1, 2020 was a Tuesday (index 1)
int dayOfWeek = d % 7;
String[] days = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
return days[dayOfWeek];
```

### 2. **Input Parsing Algorithm**
```java
// Parse space-separated attendance input
String[] collectedInput = new String[4];
int count = 0;
for (int i = 0; i < inputChar.length; i++) {
    if (inputChar[i] != ' ')
        collectedInput[count] += inputChar[i];
    else
        count++;
}
```

### 3. **Cross-Reference Validation**
```java
// Validate student exists
boolean exist = false;
StudentList currentStudent = students;
while (currentStudent != null) {
    if (currentStudent.name.equals(studentName)) {
        exist = true;
        break;
    }
    currentStudent = currentStudent.next;
}
```

### 4. **Report Generation Algorithm**
```java
// Generate attendance matrix
for each student:
    for each class:
        for each day (1-30):
            if (day matches class schedule):
                search attendance records
                display attendance status
```

## Input Validation Rules

### Student Input
- **Format**: Simple string (max 10 characters)
- **Termination**: "." to end input
- **Limit**: Maximum 10 students

### Schedule Input
- **Format**: "H DD" (Hour + space + Day)
- **Hour Range**: 1-6
- **Valid Days**: MO, TU, WE, TH, FR, SA, SU
- **Termination**: "." to end input

### Attendance Input
- **Format**: "Name Hour Day Status"
- **Validation**:
  - Student must exist in student list
  - Hour and day must match existing schedule
  - Status must be "HERE" or "NOT_HERE"
  - Day must be valid (1-30)

## Report Output Format

### Header Row
```
1:00 MO 1|1:00 TU 2|1:00 WE 3|...
```
Shows class schedule with time, day, and date.

### Student Rows
```
Student1   1|      -1|   |1|...
Student2   |       1|   |-1|...
```
Shows attendance status:
- `1`: Present (HERE)
- `-1`: Absent (NOT_HERE)
- ` `: No record

## Algorithm Complexity
- **Input Processing**: O(n) for each data type
- **Validation**: O(n × m) for cross-references
- **Report Generation**: O(s × c × d) where:
  - s = students, c = classes, d = days (30)
- **Overall**: O(s × c × d) for report generation

## Memory Complexity
- **Students**: O(s) where s ≤ 10
- **Classes**: O(c) where c ≤ 10  
- **Attendance**: O(a) where a = number of records
- **Total**: O(s + c + a)

## Example Execution
```
Input:
Alice
Bob
.
1 MO
2 TU
.
Alice 1 1 HERE
Bob 1 1 NOT_HERE
Alice 2 2 HERE
.

Output:
1:00 MO 1|2:00 TU 2|
Alice      1|        1|
Bob       -1|         |
```

## Error Handling
- **Invalid Student Names**: Length validation
- **Invalid Schedule Format**: Pattern matching
- **Invalid Attendance**: Cross-reference validation
- **Input Type Validation**: Scanner validation
- **Graceful Error Messages**: Informative error output

## Key Programming Principles
- **Data Normalization**: Organizing data into logical structures
- **Input Validation**: Comprehensive validation at multiple levels
- **Cross-Reference Integrity**: Maintaining data consistency
- **Modular Design**: Separating concerns into different methods
- **Report Generation**: Converting data into meaningful output
- **Error Handling**: Robust error detection and reporting

## Learning Outcomes
This exercise teaches:
- **Complex Data Management**: Handling multiple interconnected data structures
- **Advanced Input Processing**: Multi-format input validation and parsing
- **Cross-Reference Validation**: Ensuring data integrity across structures
- **Report Generation**: Creating formatted tabular output
- **Date/Time Calculations**: Working with calendar logic
- **System Integration**: Combining multiple components into cohesive system
- **Real-world Application**: Implementing practical business logic

## Practical Applications
This system demonstrates concepts used in:
- **Academic Management Systems**: Student and course tracking
- **Employee Attendance Systems**: Workforce management
- **Event Management**: Participant tracking
- **Database Design**: Relational data modeling
- **Business Intelligence**: Report generation and data analysis

This exercise represents a comprehensive application that combines data structures, algorithms, input validation, and report generation into a practical business application.
