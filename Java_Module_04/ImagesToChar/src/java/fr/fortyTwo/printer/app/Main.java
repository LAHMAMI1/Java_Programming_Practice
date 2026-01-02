package fr.fortyTwo.printer.app;

import fr.fortyTwo.printer.logic.ImgConvert;

public class Main {
    public static void main(String[] args) {
        ImgConvert image = new ImgConvert(args);

        image.run();

        System.exit(0);
    }
}
