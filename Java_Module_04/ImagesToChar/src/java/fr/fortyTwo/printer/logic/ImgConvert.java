package fr.fortyTwo.printer.logic;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImgConvert {
    char white;
    char black;

    File imageFile;
    BufferedImage image;

    int height;
    int width;

    int pixels;
    int red;
    int green;
    int blue;
    char[][] printPixels;

    public ImgConvert(String[] args) {
        // Check argument and valid path
        if (args.length != 3) {
            System.err.println("Error: Invalid arguments");
            System.exit(-1);
        }

        if (args[0].length() != 1 || args[1].length() != 1) {
            System.err.println("Error:\nUsage: Main <white> <black> <path_of_BMP>");
            System.exit(-1);
        }

        this.white = args[0].charAt(0);
        this.black = args[1].charAt(0);
        this.imageFile = new File(args[2]);
        this.image = null;
        this.height = 0;
        this.width = 0;
        this.pixels = 0;
        this.red = 0;
        this.green = 0;
        this.blue = 0;
        this.printPixels = null;
    };

    private void convertImg() {
        try {
            image = ImageIO.read(imageFile);

            height = image.getHeight();
            width = image.getWidth();

            printPixels = new char[height][width];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pixels = image.getRGB(x, y);
                    red = (pixels >> 16) & 0xff;
                    green = (pixels >> 8) & 0xff;
                    blue = pixels & 0xff;

                    if (red > 128 && green > 128 && blue > 128)
                        printPixels[y][x] = white;
                    else
                        printPixels[y][x] = black;
                }
            }

        } catch (IOException e) {
            System.err.println("Error: " + e);
        }
    }

    private void printImg() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                System.out.print(printPixels[i][j]);
            System.out.println();
        }
    }

    public void run() {
        convertImg();
        printImg();
    }
}
