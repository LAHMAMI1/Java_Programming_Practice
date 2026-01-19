package fr.fortyTwo.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {

    @ParameterizedTest
    @ValueSource(ints = { 11, 29, 17 })
    void isPrimeForPrimes(int number) {
        assertTrue(NumberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = { 4, 9, 24 })
    void isPrimeForNotPrimes(int number) {
        assertFalse(NumberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, -2 })
    void isPrimeForIncorrectNumbers(int number) {
        assertThrowsExactly(IllegalNumberException.class, () -> {
            NumberWorker.isPrime(number);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", useHeadersInDisplayName = true)
    void isDigitSumForAllTheNumbers(int number, int digitsSum) {
        assertEquals(NumberWorker.digitsSum(number), digitsSum);
    }
    
}
