package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logic {

    private final String PATH_IMAGE = "/Users/pavel/42school_projects/Java_piscine_2/day04/ex02/ImagesToChar/src/resources/image.bmp";

    private final String WHITECOLOR;
    private final String BLACKCOLOR;


    public static final String ANSI_RESET = "\u001B[0m";

    public Logic(String whiteColor, String blackColor) {
        this.WHITECOLOR = whiteColor;
        this.BLACKCOLOR = blackColor;
    }

    public void printImage() {
        ColoredPrinter coloredPrinter = new ColoredPrinter(new ColoredPrinter.Builder(0,false));
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(PATH_IMAGE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int pixel = image.getRGB(j, i);
                if ((pixel & 0x00FFFFFF) == 0) {
                    coloredPrinter.print(" ", Ansi.Attribute.BOLD, Ansi.FColor.valueOf(BLACKCOLOR) , Ansi.BColor.valueOf(BLACKCOLOR));
                    coloredPrinter.clear();
                } else {
                    coloredPrinter.print(" ", Ansi.Attribute.BOLD, Ansi.FColor.valueOf(WHITECOLOR) , Ansi.BColor.valueOf(WHITECOLOR));
                    coloredPrinter.clear();
                }
            }
            System.out.println();
        }
    }
}
