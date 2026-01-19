package fr.fortyTwo.numbers;

public class NumberWorker {

    public static boolean isPrime(int number) {
        if (number <= 1)
            throw new IllegalNumberException("Number should be > 1");
        if (number == 2)
            return true;
        if (number % 2 == 0)
            return false;

        // check odd divisors up to √number
        // i < √number -> i * i < number
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0)
                return false;
        }

        return true;
    }

    public static int digitsSum(int number) {
        // Convert to positive
        number = Math.abs(number);
        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        return sum;
    }
}
