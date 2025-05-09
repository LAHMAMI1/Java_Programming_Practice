import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int sum, numLength, tempNum;
        int num = 0;
        int count = 0;
        boolean isPrime;
        Scanner sc = new Scanner(System.in);
        
        while (num != 42) {
            isPrime = true;

            if (!sc.hasNextInt()) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
    
            num = sc.nextInt();
            if (num < 2) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            
            numLength = (int) Math.floor(Math.log10(num)) + 1;
            tempNum = num;
            sum = 0;
            for (int i = 0; i < numLength; i++) {
                sum += tempNum % 10;
                tempNum /= 10;
            }
            
            int sqrtSum = (int) Math.sqrt(sum);
            for (int i = 2; i <= sqrtSum; i++) {
                if (sum % i == 0 || sum == 1)
                    isPrime = false;
            }
            
            if (isPrime)
                count++;
        }
        sc.close();

        System.out.println("Count of coffee-request : " + count);

        System.exit(0);
    }
}