package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")

public class Args {
    public Args() {
    }

    @Parameter(names = {"--white", "-w"})
    private String white;

    @Parameter(names = {"--black", "-b"})
    private String black;

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }
}
