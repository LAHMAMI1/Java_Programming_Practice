package fr.fortyTwo.printer.logic;

import java.io.IOException;
import java.io.InputStream;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImgConvert {
    String white;
    String black;

    InputStream imageFile;
    BufferedImage image;

    int height;
    int width;

    int pixels;
    int red;
    int green;
    int blue;
    String[][] printPixels;

    public ImgConvert(Args arguments) {
        this.white = arguments.getWhite();
        this.black = arguments.getBlack();
        this.imageFile = getClass().getClassLoader().getResourceAsStream("it.bmp");
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

            printPixels = new String[height][width];

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

    private Attribute getColor(String colorName) {
        String color = colorName.toUpperCase();

        switch (color) {
            case "RED":
                return Attribute.RED_TEXT();
            case "GREEN":
                return Attribute.GREEN_TEXT();
            case "BLACK":
                return Attribute.BLACK_TEXT();
            case "YELLOW":
                return Attribute.YELLOW_TEXT();
            case "BLUE":
                return Attribute.BLUE_TEXT();
            default:
                return Attribute.WHITE_TEXT();
        }
    }

    private void printImg() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Attribute color = getColor(printPixels[i][j]);
                System.out.print(Ansi.colorize("█", color));
            }
            System.out.println();
        }
    }

    public void run() {
        convertImg();
        printImg();
    }
}
