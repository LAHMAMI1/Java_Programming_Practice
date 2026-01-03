# ImagesToChar

A Java application that converts BMP images into ASCII art by mapping pixels to characters.

## Project Structure

```
ImagesToChar/
├── README.md
├── src/
│   ├── manifest.txt                           # JAR manifest with Main-Class entry point
│   ├── java/
│   │   └── fr/
│   │       └── fortyTwo/
│   │           └── printer/
│   │               ├── app/
│   │               │   └── Main.java          # Application entry point
│   │               └── logic/
│   │                   └── ImgConvert.java    # Image conversion logic
│   └── resources/                             # Resource files bundled in JAR
│       └── it.bmp                             # BMP image file for conversion
└── target/                                     # Build output directory (generated)
    ├── fr/
    │   └── fortyTwo/
    │       └── printer/
    │           ├── app/
    │           │   └── Main.class
    │           └── logic/
    │               └── ImgConvert.class
    └── images-to-chars-printer.jar           # Executable JAR archive (generated)
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

### Target Directory Structure

After successful compilation, the `target/` directory will contain:

```
target/
└── fr/
    └── fortyTwo/
        └── printer/
            ├── app/
            │   └── Main.class
            └── logic/
                └── ImgConvert.class
```

## JAR Assembly Instructions

### Manifest Configuration

The manifest file is located at `src/manifest.txt` and specifies the application entry point:

```
Main-Class: fr.fortyTwo.printer.app.Main
```

### Building the JAR Archive

To create an executable JAR file with both compiled classes and resources, run the following command from the project root directory:

```bash
jar -cfm target/images-to-chars-printer.jar src/manifest.txt -C target/ . -C src/resources .
```

This command:
- `c` - Create a new JAR archive
- `f` - Specify the JAR filename
- `m` - Include manifest file from `src/manifest.txt`
- `-C target/ .` - Change to the `target/` directory and include all compiled class files
- `-C src/resources .` - Change to the `src/resources/` directory and include all resource files (BMP images)

### Generated JAR Contents

The resulting `target/images-to-chars-printer.jar` will have the following structure:

```
images-to-chars-printer.jar
├── META-INF/
│   └── MANIFEST.MF          # Contains Main-Class entry point
├── fr/
│   └── fortyTwo/
│       └── printer/
│           ├── app/
│           │   └── Main.class
│           └── logic/
│               └── ImgConvert.class
└── it.bmp                    # BMP image from resources folder
```

## Execution Instructions

### From JAR Archive

Once the JAR file has been created using the JAR assembly instructions above, execute the application using:

```bash
java -jar target/images-to-chars-printer.jar <white_char> <black_char>
```

### Arguments

- `<white_char>` - Character to represent white/bright pixels
- `<black_char>` - Character to represent black/dark pixels

**Note:** The image path argument is no longer required. The application loads the BMP image (it.bmp) from the `src/resources/` folder that is included in the JAR archive.

### Example

```bash
java -jar target/images-to-chars-printer.jar "." "0"
```

This command will convert the `it.bmp` image (located in the resources folder) using "." for white pixels and "0" for black pixels, printing the result to the console.

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
