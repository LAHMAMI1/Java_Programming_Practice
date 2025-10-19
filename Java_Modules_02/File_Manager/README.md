# File Manager Program

## Overview
This is a simple command-line file manager implemented in Java that demonstrates the usage of the Java NIO (New I/O) package for file system operations. The program allows users to navigate through directories and perform basic file operations.

## Features
- List directory contents (`ls`)
- Change directory (`cd`)
- Move files and directories (`mv`)

## Learning Objectives
Through this exercise, we learn about:

1. **Java NIO Package**
   - Using `Path` and `Paths` for file system navigation
   - Working with `Files` class for file operations
   - Understanding directory operations

2. **Stream Operations**
   - Using `Files.list()` to get directory contents as a Stream
   - Converting Streams to Lists for iteration
   - Proper resource management with Stream closing

3. **File System Operations**
   - Path resolution and normalization
   - File movement and copying
   - Directory traversal
   - File existence checking

4. **Exception Handling**
   - Managing `Exception` for file operations
   - General error handling practices

## How It Works

### Program Initialization
1. The program accepts a command-line argument in the format: `--current-folder=<PATH>`
2. Validates the provided path and checks if it's a valid directory

### Available Commands

1. **ls (List Directory)**
   ```java
   ls
   ```
   - Lists all files and directories in the current directory
   - Shows file names and sizes in KB

2. **cd (Change Directory)**
   ```java
   cd <PATH>
   ```
   - Changes the current working directory
   - Supports both absolute and relative paths
   - Validates directory existence

3. **mv (Move Files)**
   ```java
   mv <SOURCE> <DESTINATION>
   ```
   - Moves files from source to destination
   - Supports moving to directories
   - Handles file name conflicts

4. **exit**
   ```java
   exit
   ```
   - Exits the program

## Technical Implementation Details

### Path Handling
- Uses `Path.resolve()` for path resolution
- Validates paths using `Files.exists()` and `Files.isDirectory()`
- Handles relative and absolute paths

### File Operations
- Uses `Files.move()` with `REPLACE_EXISTING` option
- Implements directory listing using `Files.list()`
- Proper resource management with try-catch blocks

### Input Processing
- Uses `Scanner` for reading user input
- Command parsing with `String.split()`
- Input validation and error handling

## Usage Example
```bash
java Program --current-folder=/home/user/documents
/home/user/documents
ls
file1.txt 10 KB
folder1 0 KB
cd folder1
mv ../file1.txt ../
ls
file1.txt 10 KB
```

## Error Handling
- Invalid path errors
- File not found errors
- Permission errors
- Invalid command format errors

This project serves as a practical introduction to file system operations in Java, demonstrating both basic and intermediate concepts in file handling, path management, and command-line interface design.