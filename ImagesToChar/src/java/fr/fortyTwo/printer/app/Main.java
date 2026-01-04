package fr.fortyTwo.printer.app;

import fr.fortyTwo.printer.logic.Args;
import fr.fortyTwo.printer.logic.ImgConvert;
import com.beust.jcommander.JCommander;

public class Main {
    public static void main(String[] args) {
        // Parse arguments using JCommander
        Args arguments = new Args();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        // Convert the image
        ImgConvert image = new ImgConvert(arguments);
        image.run();

        System.exit(0);
    }
}
