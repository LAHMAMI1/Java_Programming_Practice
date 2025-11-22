import java.io.*;
import java.util.*;

public class Program {
    private static int parseArgument(String[] args) {
        if (args.length != 1) {
            System.err.println("Error: Invalid arguments");
            System.exit(-1);
        }

        String threadsCount = args[0];

        if (!threadsCount.startsWith("--threadsCount=")) {
            System.err.println("Error: Argument usage\n--threadsCount=<NUMBER>");
            System.exit(-1);
        }

        int count = 0;
        try {
            count = Integer.parseInt(threadsCount.substring(15));

            if (count <= 0) {
                System.err.println("Error: Usage\nNums must be positive");
                System.exit(-1);
            }

        } catch (NumberFormatException e) {
            System.err.println("Error: Invalid number format");
            System.exit(-1);
        }

        return count;
    }

    private static List<String> readFile() {
        List<String> urls = new ArrayList<String>();

        try {
            File fileUrls = new File("files_urls.txt");
            BufferedReader reader = new BufferedReader(new FileReader(fileUrls));
            String line;

            while ((line = reader.readLine()) != null)
                urls.add(line);

            reader.close();

        } catch (FileNotFoundException e) {
            System.err.println("Error: file not found" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return urls;
    }

    public static void main(String[] args) {
        // Check argument and return threadCount
        int count = parseArgument(args);

        // Read file urls
        List<String> urls = readFile();
        if (urls.isEmpty()) {
            System.err.println("No URLs found in files_urls.txt");
            System.exit(-1);
        }
        
        // Create the shared download queue
        DownloadQueue queue = new DownloadQueue(urls.size());

        // Create and start worker threads
        DownloadThread[] threads = new DownloadThread[count];

        for (int i = 0; i < count; i++) {
            threads[i] = new DownloadThread(queue, urls);
            threads[i].setName("Thread-" + (i + 1));
            threads[i].start();
        }

        // Wait for all threads to complete
        for (DownloadThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
    }

}
