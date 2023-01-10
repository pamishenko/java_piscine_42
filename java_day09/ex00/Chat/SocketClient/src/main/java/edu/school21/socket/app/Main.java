package edu.school21.socket.app;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")

public class Main {
    public Main() {
    }

    public int getServerPort() {
        return serverPort;
    }

    @Parameter(names = {"--server - port", "-p"})
    private int serverPort;
}

