# 🧪 JUnit 5 Testing Practice Project

A hands-on Java project demonstrating **unit testing fundamentals** using **JUnit Jupiter 5** with Maven. This project focuses on learning parameterized testing, custom exceptions, and best practices for test-driven development.

---

## 📚 What We Learned

### 1. **JUnit Jupiter 5 Framework**
JUnit 5 (Jupiter) is the modern testing framework for Java. We learned how to:
- Set up JUnit 5 dependencies using Maven
- Write and organize test classes
- Use assertions to validate expected outcomes

### 2. **Parameterized Tests**
One of the most powerful features of JUnit 5 is **parameterized testing**, which allows running the same test with different inputs:

```java
@ParameterizedTest
@ValueSource(ints = { 11, 29, 17 })
void isPrimeForPrimes(int number) {
    assertTrue(NumberWorker.isPrime(number));
}
```

This eliminates code duplication and makes tests more maintainable.

### 3. **External Data Sources with `@CsvFileSource`**
We learned to load test data from external CSV files, separating test logic from test data:

```java
@ParameterizedTest
@CsvFileSource(resources = "/data.csv", useHeadersInDisplayName = true)
void isDigitSumForAllTheNumbers(int number, int digitsSum) {
    assertEquals(NumberWorker.digitsSum(number), digitsSum);
}
```

### 4. **Custom Exceptions**
Creating domain-specific exceptions improves code clarity and error handling:

```java
public class IllegalNumberException extends IllegalArgumentException {
    public IllegalNumberException(String message) {
        super(message);
    }
}
```

### 5. **Exception Testing**
Using `assertThrowsExactly()` to verify that specific exceptions are thrown:

```java
@ParameterizedTest
@ValueSource(ints = { 0, 1, -2 })
void isPrimeForIncorrectNumbers(int number) {
    assertThrowsExactly(IllegalNumberException.class, () -> {
        NumberWorker.isPrime(number);
    });
}
```

### 6. **Maven Surefire Plugin**
The Surefire Plugin is essential for running JUnit tests during the Maven build lifecycle.

---

## 🔑 Key Concepts

| Concept | Description |
|---------|-------------|
| **`@ParameterizedTest`** | Annotation that enables running a test multiple times with different arguments |
| **`@ValueSource`** | Provides simple inline values (ints, strings, etc.) as test arguments |
| **`@CsvFileSource`** | Loads test data from CSV files in the classpath |
| **`assertTrue()` / `assertFalse()`** | Assertions to verify boolean conditions |
| **`assertEquals()`** | Assertion to compare expected and actual values |
| **`assertThrowsExactly()`** | Verifies that exactly the specified exception type is thrown |
| **Custom Exceptions** | Domain-specific exceptions extending standard Java exceptions |

---

## 📂 Project Structure

```
TESTS/
├── pom.xml                                    # Maven configuration
├── README.md
└── src/
    ├── main/java/fr/fortyTwo/numbers/
    │   ├── NumberWorker.java                  # Main utility class
    │   └── IllegalNumberException.java        # Custom exception
    └── test/
        ├── java/fr/fortyTwo/numbers/
        │   └── NumberWorkerTest.java          # Unit tests
        └── resources/
            └── data.csv                       # Test data file
```

---

## 🚀 How to Use

### Prerequisites
- **Java 21** or higher
- **Maven 3.6+**

### Running the Tests

```bash
# Run all tests
mvn test

# Run tests with verbose output
mvn test -Dtest=NumberWorkerTest

# Clean and run tests
mvn clean test
```

### Understanding the Code

#### `NumberWorker.java`
Contains two utility methods:

1. **`isPrime(int number)`** - Checks if a number is prime
   - Throws `IllegalNumberException` for numbers ≤ 1
   - Uses optimized algorithm checking only odd divisors up to √n

2. **`digitsSum(int number)`** - Calculates the sum of digits
   - Handles negative numbers by using absolute value

#### `NumberWorkerTest.java`
Demonstrates four types of parameterized tests:
- Testing prime numbers return `true`
- Testing non-prime numbers return `false`
- Testing invalid inputs throw exceptions
- Testing digit sum with CSV data

---

## 📦 Dependencies

```xml
<!-- JUnit Jupiter Engine: Executes the tests -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>6.1.0-M1</version>
    <scope>test</scope>
</dependency>

<!-- JUnit Jupiter API: Provides annotations and assertions -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>6.1.0-M1</version>
    <scope>test</scope>
</dependency>

<!-- JUnit Jupiter Params: Enables parameterized tests -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <version>6.1.0-M1</version>
    <scope>test</scope>
</dependency>
```

---

## ✅ Best Practices Demonstrated

1. **Separation of Concerns** - Test data in CSV files, test logic in test classes
2. **Descriptive Test Names** - Method names clearly describe what is being tested
3. **Single Responsibility** - Each test method verifies one specific behavior
4. **Edge Case Coverage** - Tests include boundary cases (0, 1, negative numbers)
5. **Custom Exceptions** - Domain-specific exceptions for better error messages
6. **Efficient Algorithms** - Prime checking optimized with √n complexity


