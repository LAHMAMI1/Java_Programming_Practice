import java.io.*;
import java.util.*;

public class Program {

    public static HashMap<String, List<String>> readSignatures() {
        HashMap<String, List<String>> signatures = new HashMap<>();

        try {
            FileInputStream signaturesFile = new FileInputStream("signatures.txt");
            int charactere;

            String line = "";
            while ((charactere = signaturesFile.read()) != -1) {
                if (charactere == '\n') {
                    String[] splitInput = line.split(", ", 2);
                    String fileType = splitInput[0];
                    String signature = splitInput[1];

                    if (!signatures.containsKey(fileType))
                        signatures.put(fileType, new ArrayList<>());

                    signatures.get(fileType).add(signature);

                    line = "";
                } else
                    line += (char) charactere;
            }
            signaturesFile.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: signatures.txt");
        } catch (IOException e) {
            System.err.println("Error reading file: signatures.txt: " + e.getMessage());
        }

        return signatures;
    }

    public static String readInputFile(String input) {
        String fileSignature = "";
        try {
            String fullPath = input;
            FileInputStream fileInput = new FileInputStream(fullPath);

            byte[] buffer = new byte[8]; // Read first 8 bytes to capture most signatures
            int bytesRead = fileInput.read(buffer);
            fileInput.close();

            // Convert bytes to hexadecimal signature
            StringBuilder hexSignature = new StringBuilder();
            for (int i = 0; i < bytesRead; i++)
                hexSignature.append(String.format("%02X ", buffer[i] & 0xFF));
            fileSignature = hexSignature.toString().trim();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: InputFile");
        } catch (IOException e) {
            System.err.println("Error reading file: InputFile: " + e.getMessage());
        }

        return fileSignature;
    }

    public static void writeResult(HashMap<String, List<String>> signatures, String fileSignature) {
        try {
            boolean processed = false;
            outerLoop: // we use this label that marks the outer for loop to break it after.
            for (Map.Entry<String, List<String>> entry : signatures.entrySet()) {
                String fileType = entry.getKey();
                List<String> signatureList = entry.getValue();

                for (String signature : signatureList) {
                    if (fileSignature.startsWith(signature)) {
                        processed = true;
                        FileOutputStream fileOutput = new FileOutputStream("result.txt", true);
                        byte[] fileTypeByte = fileType.getBytes();
                        fileOutput.write(fileTypeByte);
                        fileOutput.write('\n');
                        fileOutput.close();
                        System.out.println("PROCESSED");
                        break outerLoop;
                    }
                }
            }

            if (!processed)
                System.err.println("UNDEFINED");

        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        HashMap<String, List<String>> signatures = readSignatures();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine().trim();
            if (input.equals("42"))
                break;

            String fileSignature = readInputFile(input);

            writeResult(signatures, fileSignature);
        }
        sc.close();
    }
}
