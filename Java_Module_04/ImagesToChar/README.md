# ImagesToChar

A Java application that converts BMP images into ASCII art by mapping pixels to characters.

## Project Structure

```
ImagesToChar/
├── README.md
├── src/
│   └── java/
│       └── fr/
│           └── fortyTwo/
│               └── printer/
│                   ├── app/
│                   │   └── Main.java          # Application entry point
│                   └── logic/
│                       └── ImgConvert.java    # Image conversion logic
├── target/                                     # Compiled .class files (generated)
│   └── fr/
│       └── fortyTwo/
│           └── printer/
│               ├── app/
│               │   └── Main.class
│               └── logic/
│                   └── ImgConvert.class
└── it.bmp                                      # Example BMP image file
```

## Package Architecture

- **`fr.fortyTwo.printer`** - Main package namespace
  - **`app`** - Contains application startup logic
    - `Main.java` - Entry point that initializes and runs the image converter
  - **`logic`** - Contains business logic for image processing
    - `ImgConvert.java` - Handles image loading and conversion to character arrays

## Compilation Instructions

Run the following command from the project root directory to compile all source files:

```bash
javac -d target src/java/fr/fortyTwo/printer/app/Main.java src/java/fr/fortyTwo/printer/logic/ImgConvert.java
```

This command:
- Compiles both source files
- Outputs compiled `.class` files to the `target/` directory
- Automatically creates the package directory structure

## Execution Instructions

Run the following command from the project root directory to execute the application:

```bash
java -cp target fr.fortyTwo.printer.app.Main <white_char> <black_char> <path_to_bmp_image>
```

### Arguments

- `<white_char>` - Character to represent white/bright pixels
- `<black_char>` - Character to represent black/dark pixels
- `<path_to_bmp_image>` - Full or relative path to the BMP image file

### Example

```bash
java -cp target fr.fortyTwo.printer.app.Main "." "0" "it.bmp"
```

This will convert the `it.bmp` image using "." for white pixels and "0" for black pixels, printing the result to the console.

## How It Works

1. **Main.java** - Accepts command-line arguments and instantiates the `ImgConvert` class
2. **ImgConvert.java** - Performs the following operations:
   - Reads the BMP image file using Java's ImageIO
   - Iterates through each pixel and extracts RGB values
   - Determines if each pixel is "bright" (RGB > 128) or "dark"
   - Maps bright pixels to the white character and dark pixels to the black character
   - Prints the resulting ASCII art to the console

## Requirements

- Java 8 or higher
- BMP image file for conversion
