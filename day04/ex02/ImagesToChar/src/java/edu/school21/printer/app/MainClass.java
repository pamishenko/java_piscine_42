package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.Args;
import edu.school21.printer.logic.Logic;

public class MainClass {

    public static void main(String[] args) {
        Args arguments = new Args();
        JCommander jCommander = new JCommander();
        jCommander.addObject(arguments);
        jCommander.parse(args);
        jCommander.getParameters();

        new Logic(arguments.getWhite(), arguments.getBlack()).printImage();
    }
}
