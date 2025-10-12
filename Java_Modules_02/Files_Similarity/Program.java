import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

class Program {

    private static String[] readFile(String file) {
        String[] words = new String[0];

        try {
            File fileObj = new File(file);
            long fileSize = fileObj.length();
            long maxSize = 10L * 1024 * 1024; // 10MB in bytes

            if (fileSize > maxSize) {
                System.err.println("Error: File size exceeds 10MB limit");
                System.exit(-1);
            }

            BufferedReader reader = new BufferedReader(new FileReader(fileObj));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
                content.append(line).append(" ");
            reader.close();

            words = content.toString().split("\\s+");

        } catch (FileNotFoundException e) {
            System.err.println("Error: file not found" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return words;
    }

    private static Vector<Integer> getFrequency(Set<String> dictionary, String[] file) {
        Vector<Integer> frequency = new Vector<Integer>(dictionary.size());
        int count = 0;

        for (String word : dictionary) {
            for (String match : file) {
                if (word.equals(match))
                    count++;
            }
            frequency.add(count);
            count = 0;
        }

        return frequency;
    }

    private static void createDictionary(Set<String> dictionary) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"));

            for (String word : dictionary) {
                writer.write(word);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            System.err.println("Error wiritng file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Error: Invalid arguments");
            System.exit(-1);
        }

        // reading the 2 files
        String[] file1 = readFile(args[0]);
        String[] file2 = readFile(args[1]);
        String[] files = new String[file1.length + file2.length];
        System.arraycopy(file1, 0, files, 0, file1.length);
        System.arraycopy(file2, 0, files, file1.length, file2.length);

        // create dictionary of unique words
        Set<String> dictionary = new TreeSet<String>();
        for (String word : files)
            dictionary.add(word);

        // the frequency of occurrence in the 2 vectors
        Vector<Integer> frequency1 = getFrequency(dictionary, file1);
        Vector<Integer> frequency2 = getFrequency(dictionary, file2);

        // Cosine Similarity Formula
        double numeratorAxB = 0;
        int A = 0;
        int B = 0;
        for (int i = 0; i < frequency1.size(); i++) {
            numeratorAxB += frequency1.get(i) * frequency2.get(i);
            A += frequency1.get(i) * frequency1.get(i);
            B += frequency2.get(i) * frequency2.get(i);
        }
        double denominator = Math.sqrt(A) * Math.sqrt(B);
        double similarity = numeratorAxB / denominator;

        // print the result
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println("Similarity = " + df.format(similarity));

        createDictionary(dictionary);
    }
}