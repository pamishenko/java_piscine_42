package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainClass {
    private static char white_color;
    private static char black_color;
    private static BufferedImage image;

    public static void main(String[] args) {
        parseArgs(args);
        new Logic(white_color, black_color).printImage();
    }

    private static void parseArgs(String[] args) {
        if (args.length < 2 || args[0].length() > 1 || args[1].length() > 1) {
            System.err.println("Incorrect arguments\n" +
                    "0 arg - White\n" +
                    "1 arg - Black");
            System.exit(-1);
        }
        white_color = args[0].charAt(0);
        black_color = args[1].charAt(0);
    }
}
