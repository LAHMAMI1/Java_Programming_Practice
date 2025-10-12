# Text Similarity Calculator

## Overview
This program implements a text similarity analyzer that compares two text files and calculates their similarity using the Cosine Similarity algorithm. The similarity score ranges from 0 to 1, where 1 indicates identical texts and 0 indicates completely different texts.

## Implementation Details

### Program Components
1. **File Reading (`readFile` method)**
   - Reads text files and converts them into arrays of words
   - Uses `BufferedReader` for efficient file reading
   - Splits text on whitespace to get individual words

2. **Dictionary Creation**
   - Creates a unique set of words from both files using `TreeSet`
   - Stores the dictionary in a separate file (`dictionary.txt`)

3. **Frequency Analysis (`getFrequency` method)**
   - Calculates how often each dictionary word appears in each file
   - Returns a vector of frequencies for each file

4. **Similarity Calculation**
   - Implements the Cosine Similarity formula:
     ```
     similarity = (A·B) / (||A|| * ||B||)
     ```
   - Where A and B are frequency vectors of the two files

## How it Works
1. Takes two text files as command-line arguments
2. Reads and tokenizes both files into words
3. Creates a dictionary of unique words
4. Calculates word frequencies for each file
5. Computes similarity using cosine similarity
6. Outputs a similarity score between 0 and 1 (truncated to 2 decimal places)

## Learning Outcomes

### Java Programming Concepts
1. **File I/O Operations**
   - Working with `BufferedReader` and `BufferedWriter`
   - Exception handling for file operations

2. **Data Structures**
   - Using `Vector` for dynamic arrays
   - Implementing `Set` and `TreeSet` for unique elements
   - Array manipulation and copying

3. **String Processing**
   - String splitting and tokenization
   - Regular expressions (`\\s+`)

4. **Number Formatting**
   - Using `DecimalFormat` for precise decimal output
   - Controlling number rounding with `RoundingMode`

### Mathematical Concepts
1. **Vector Operations**
   - Vector dot product
   - Vector magnitude calculation
   - Cosine similarity algorithm

### Best Practices
1. **Code Organization**
   - Method modularization
   - Separation of concerns
   - Error handling and input validation

2. **Memory Management**
   - Efficient file reading with buffers
   - Proper resource closing

## Usage
```bash
java Program <file1> <file2>
```
The program will output a similarity score and create a dictionary.txt file containing all unique words from both files.

## Example Output
```
Similarity = 0.54
```

This exercise provides practical experience in text processing, file handling, and implementing mathematical algorithms in Java, while also teaching important concepts about vector spaces and text similarity measurements.