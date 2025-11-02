import java.util.Arrays;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        // Check argument and valid path
        if (args.length != 2) {
            System.err.println("Error: Invalid arguments");
            System.exit(-1);
        }

        String arraySize = args[0];
        String threadsCount = args[1];

        if (!arraySize.startsWith("--arraySize=") || !threadsCount.startsWith("--threadsCount=")) {
            System.err.println("Error: Argument usage\n--arraySize=<NUMBER> --threadsCount=<NUMBER>");
            System.exit(-1);
        }

        int size = 0;
        int count = 0;
        try {
            size = Integer.parseInt(arraySize.substring(12));
            count = Integer.parseInt(threadsCount.substring(15));

            if ((size <= 0 || size > 2000000) || (count <= 0 || count > size)) {
                System.err.println(
                        "Error: Usage\nNums must be positive\nMax array size = 2.000.000\nMax threads count <= array size");
                System.exit(-1);
            }

        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format");
            System.exit(-1);
        }

        int[] arr = new int[size];
        Random random = new Random();

        int i;
        for (i = 0; i < size; i++)
            arr[i] = random.nextInt(1001); // Fills with random numbers from 0 to 1000
        
        int sum = Arrays.stream(arr).sum();
        System.out.println("Sum: " + sum);

        int sectionSize = (size + count - 1) / count; // Ceiling division

        int[][] range = new int[count][2];

        for (int j = 0; j < count; j++) {
            range[j][0] = j * sectionSize;
            range[j][1] = Math.min((j + 1) * sectionSize, size);
        }

        int[] results = new int[count];
        SumThread[] t = new SumThread[count];
        for (int j = 0; j < count; j++) {
            t[j] = new SumThread(arr, range[j][0], range[j][1], results, j);
            t[j].start();
        }

        for (SumThread thread : t) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Error: join() " + e.getMessage());
            }
        }

        sum = Arrays.stream(results).sum();
        System.out.println("Sum by threads: " + sum);
    }
}
