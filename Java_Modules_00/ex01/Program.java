import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        int num = sc.nextInt();
        sc.close();
        if (num < 2) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        int iteration = 1;
        int sqrtNum = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrtNum; i++) {
            if (num % i == 0) {
                System.out.println("false " + iteration);
                System.exit(0);
            }
            iteration++;
        }

        System.out.println("true " + iteration);

        System.exit(0);
    }
}
