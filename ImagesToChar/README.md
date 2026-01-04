# ImagesToChar

A Java application that converts BMP images into ASCII art by mapping pixels to characters.

## Project Structure

```
ImagesToChar/
├── README.md
├── lib/                                       # External library JARs
│   ├── jcommander-3.0.jar                    # Command-line argument parsing library
│   └── JColor-5.5.1.jar                      # ANSI color output library
├── src/
│   ├── manifest.txt                           # JAR manifest with Main-Class entry point
│   ├── java/
│   │   └── fr/
│   │       └── fortyTwo/
│   │           └── printer/
│   │               ├── app/
│   │               │   └── Main.java          # Application entry point
│   │               └── logic/
│   │                   ├── Args.java          # Command-line argument handling (uses JCommander)
│   │                   └── ImgConvert.java    # Image conversion logic (uses JColor)
│   └── resources/
│       └── it.bmp                             # BMP image file for conversion
└── target/                                     # Build output directory (generated)
    ├── com/                                   # Extracted from jcommander-3.0.jar
    │   └── beust/
    │       └── jcommander/                    # JCommander library classes
    ├── diogonunes/                            # Extracted from JColor-5.5.1.jar
    │   └── jcolor/                            # JColor library classes
    ├── fr/
    │   └── fortyTwo/
    │       └── printer/
    │           ├── app/
    │           │   └── Main.class             # Compiled application classes
    │           └── logic/
    │               ├── Args.class
    │               └── ImgConvert.class
    ├── META-INF/                              # Generated metadata
    │   ├── MANIFEST.MF
    │   └── maven/                             # From extracted libraries
    └── images-to-chars-printer.jar           # Executable JAR with bundled libraries
```

## Package Architecture

- **`fr.fortyTwo.printer`** - Main package namespace
  - **`app`** - Contains application startup logic
    - `Main.java` - Entry point that initializes and runs the image converter using JCommander for argument parsing
  - **`logic`** - Contains business logic for image processing
    - `Args.java` - Command-line argument handling with JCommander annotations for `--white` and `--black` color parameters
    - `ImgConvert.java` - Loads BMP image, converts pixels to ASCII characters, and applies JColor formatting for colored console output

**External Dependencies:**
- `com.beust.jcommander` (from jcommander-3.0.jar) - Provides argument parsing
- `com.diogonunes.jcolor` (from JColor-5.5.1.jar) - Provides ANSI color output

## Compilation Instructions

See the **"Complete Build & Run Workflow"** section below for the full compilation and packaging process with external libraries.

## Execution Instructions

### From JAR Archive

Once the JAR file has been created using the instructions in **"Building the Executable JAR with External Libraries"** section, execute the application using:

```bash
java -jar target/images-to-chars-printer.jar --white <color> --black <color>
```

### Arguments

- `--white <color>` - Color for white/bright pixels (e.g., RED, BLUE, GREEN, YELLOW)
- `--black <color>` - Color for black/dark pixels (e.g., BLACK, CYAN, MAGENTA, WHITE)

**Note:** The image path argument is no longer required. The application loads the BMP image (it.bmp) from the `src/resources/` folder that is included in the JAR archive.

### Examples

```bash
# Red text for bright pixels, green for dark pixels
java -jar target/images-to-chars-printer.jar --white RED --black GREEN

# Blue text for bright pixels, yellow for dark pixels
java -jar target/images-to-chars-printer.jar --white BLUE --black YELLOW

# White text for bright pixels, black for dark pixels
java -jar target/images-to-chars-printer.jar --white WHITE --black BLACK
```

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

## External Libraries

This project uses two external Java libraries to enhance functionality:

### JCommander (jcommander-3.0.jar)

**Purpose:** Command-line argument parsing and validation

**Features:**
- Parses command-line arguments with type conversion
- Provides automatic help message generation
- Validates required arguments at runtime

**Usage in Project:**
- Located in `lib/jcommander-3.0.jar`
- Used in `Args.java` to define command-line parameters with annotations
- Simplifies argument handling without manual string parsing

**Key Classes:**
- `com.beust.jcommander.JCommander` - Main argument parser
- `com.beust.jcommander.Parameter` - Annotation for marking command-line parameters

### JColor (JColor-5.5.1.jar)

**Purpose:** ANSI color output for terminal/console

**Features:**
- Adds colored text output to terminal applications
- Supports various color palettes (red, green, blue, yellow, etc.)
- Platform-independent color formatting

**Usage in Project:**
- Located in `lib/JColor-5.5.1.jar`
- Used in `ImgConvert.java` to colorize ASCII art output
- Applies foreground colors to characters before printing

**Key Classes:**
- `com.diogonunes.jcolor.Ansi` - Color formatting methods
- `com.diogonunes.jcolor.AnsiColor` - Color definitions

## Library Integration & Compilation

When compiling with external library dependencies, include all required JARs in the classpath using the `-cp` flag:

```bash
javac -cp "lib/jcommander-3.0.jar:lib/JColor-5.5.1.jar" -d target src/java/fr/fortyTwo/printer/app/*.java src/java/fr/fortyTwo/printer/logic/*.java
```

**Explanation:**
- `-cp "lib/jcommander-3.0.jar:lib/JColor-5.5.1.jar"` - Adds library JARs to the compile-time classpath (separated by `:` on Linux/Mac)
- Multiple JARs are separated by colons (`:`) on Unix-like systems or semicolons (`;`) on Windows
- Without `-cp`, the compiler cannot find the library classes referenced in your source code
- The compiler produces `.class` files in the `target/` directory that reference library classes

## Building the Executable JAR with External Libraries

When creating an executable JAR that depends on external libraries, those libraries must be bundled into the final JAR package. This is done by extracting the library JAR contents and including them in the final JAR.

### Step 1: Extract Library Classes into Target Directory

Extract each external library JAR into the `target/` directory so its classes are available for bundling:

```bash
cd target
jar -xf ../lib/jcommander-3.0.jar
jar -xf ../lib/JColor-5.5.1.jar
cd ..
```

**What this does:**
- `jar -xf` - Extracts the contents of a JAR file
- Both library JARs are extracted into `target/`, placing all library classes and resources alongside compiled application classes
- After extraction, `target/` contains both your application classes (`fr/fortyTwo/...`) and library classes (`com/beust/...`, `com/diogonunes/...`)

### Step 2: Create the Final JAR Package

Create the executable JAR that includes both your application code and all external library classes:

```bash
jar -cfm target/images-to-chars-printer.jar src/manifest.txt -C target/ . -C src/resources .
```

**Result:**
The final JAR now contains:
- Your compiled application classes (`fr/fortyTwo/printer/...`)
- All required library classes (`com/beust/jcommander/...`, `com/diogonunes/jcolor/...`)
- Resource files (BMP images)
- manifest.txt with Main-Class entry point

This bundling approach ensures the JAR is "fat" (self-contained) and can run without requiring external library JARs on the classpath.

## Complete Build & Run Workflow

Here's the complete sequence of commands to compile, package, and execute the application:

```bash
# Step 1: Compile with external libraries in classpath
javac -cp "lib/jcommander-3.0.jar:lib/JColor-5.5.1.jar" -d target src/java/fr/fortyTwo/printer/app/*.java src/java/fr/fortyTwo/printer/logic/*.java

# Step 2: Extract external library classes into target directory
cd target
jar -xf ../lib/jcommander-3.0.jar
jar -xf ../lib/JColor-5.5.1.jar
cd ..

# Step 3: Create executable JAR with all libraries bundled
jar -cfm target/images-to-chars-printer.jar src/manifest.txt -C target/ . -C src/resources .

# Step 4: Execute the application with color arguments
java -jar target/images-to-chars-printer.jar --white RED --black GREEN
```

**Color Examples:**
```bash
# Red and green
java -jar target/images-to-chars-printer.jar --white RED --black GREEN

# Blue and yellow
java -jar target/images-to-chars-printer.jar --white BLUE --black YELLOW

# White and black
java -jar target/images-to-chars-printer.jar --white WHITE --black BLACK
```
