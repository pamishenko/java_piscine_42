package edu.school21.printer.logic;

import java.awt.image.BufferedImage;

public class Logic {
    private final char whiteChar;
    private final char blackChar;

    public Logic(char whiteChar, char blackChar) {
        this.whiteChar = whiteChar;
        this.blackChar = blackChar;
    }

    public void printImage(BufferedImage image) {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                int pixel = image.getRGB(j, i);
                if ((pixel & 0x00FFFFFF) == 0) {
                    System.out.print(blackChar);
                } else {
                    System.out.print(whiteChar);
                }
            }
            System.out.println();
        }
    }
}
