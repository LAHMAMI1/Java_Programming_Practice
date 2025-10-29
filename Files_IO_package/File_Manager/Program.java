import java.nio.file.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Program {

    private static void ls(Path path) {
        try {
            Stream<Path> listDir = Files.list(path);

            for (Path currentContent : listDir.toList())
                System.out.println(
                        currentContent.getFileName() + " " + (Files.size(currentContent) / 1024) + " KB");

            listDir.close();

        } catch (Exception e) {
            System.err.println("Error: (ls)" + e.getMessage());
        }
    }

    private static Path cd(String cmd, Path path) {
        try {
            String[] splitCmd = cmd.split(" ");
            if (splitCmd.length != 2) {
                System.err.println("Error: cd\nUsage: cd <PATH>");
                return path;
            }

            Path tmpPath = path.resolve(splitCmd[1]);
            if (Files.notExists(tmpPath) || !Files.isDirectory(tmpPath))
                System.out.println("Invalid Path: (cd) " + tmpPath);
            else
                path = tmpPath;
            
            System.out.println(path);

        } catch (Exception e) {
            System.err.println("Error: (cd) " + e.getMessage());
        }

        return path;
    }

    private static void mv(String cmd, Path path) {
        try {
            String[] splitCmd = cmd.split(" ");
            if (splitCmd.length != 3) {
                System.err.println("Error: mv\nUsage: mv <WHAT> <WHERE>");
                return;
            }

            Path source = path.resolve(splitCmd[1]);
            Path target = path.resolve(splitCmd[2]);

            if (Files.exists(target) && Files.isDirectory(target))
                Files.move(source, target.resolve(source.getFileName()), REPLACE_EXISTING);
            else
                Files.move(source, source.resolveSibling(splitCmd[2]));

        } catch (Exception e) {
            System.err.println("Error: (mv) " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Check argument and valid path
        if (args.length != 1) {
            System.err.println("Error: Invalid arguments");
            System.exit(-1);
        }

        String argument = args[0];
        String splitArgs[] = argument.split("/");

        if (!splitArgs[0].equals("--current-folder=")) {
            System.err.println("Error: Argument usage\n --current-folder=<PATH>");
            System.exit(-1);
        }

        Path path = Paths.get("/" + splitArgs[1], Arrays.copyOfRange(splitArgs, 2, splitArgs.length));
        if (Files.notExists(path) || !Files.isDirectory(path))
            System.out.println("Invalid Path: " + path);

        System.out.println(path);

        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine().trim();

        while (!cmd.equals("exit")) {
            // ls
            if (cmd.equals("ls"))
                ls(path);
            // cd
            else if (cmd.startsWith("cd "))
                path = cd(cmd, path);
            // mv
            else if (cmd.startsWith("mv "))
                mv(cmd, path);
            
            cmd = sc.nextLine();
        }
        sc.close();
    }
}
