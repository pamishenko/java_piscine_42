package edu.school21.sockets.server;

import com.beust.jcommander.JCommander;
import edu.school21.sockets.app.Main;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        Main arguments = new Main();
        JCommander jCommander = new JCommander();
        jCommander.addObject(arguments);
        jCommander.parse(args);
        jCommander.getParameters();

        ServerSocket serverSocket = new ServerSocket(arguments.getPort());
        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(clientSocket.getOutputStream());

            outputStreamWriter.write("Hello from Server!\n> \n");
            outputStreamWriter.flush();
            String str = bufferedReader.readLine();

            if (getIdOperation(str) == 1)
                runSignUp(bufferedReader, outputStreamWriter);
            else {
                outputStreamWriter.write("Command not found\n\n");
                outputStreamWriter.flush();
            }

            bufferedReader.close();
            outputStreamWriter.close();
        }
    }

    static int getIdOperation(String scanStr){
        switch (scanStr){
            case ("exit"):
                return -1;
            case ("signUp"):
                return 1;
            default:
                return 0;
        }
    }

    static boolean runSignUp(BufferedReader bufferedReader, OutputStreamWriter outputStreamWriter) throws IOException {
        UsersService usersService = new UsersServiceImpl();
        outputStreamWriter.write("Enter username:\n> \n");
        outputStreamWriter.flush();
        String username = bufferedReader.readLine();
        outputStreamWriter.write("Enter password:\n> \n");
        outputStreamWriter.flush();
        String password = bufferedReader.readLine();
        boolean result = usersService.signUp(username, password);
        if (result)
            outputStreamWriter.write("Successful!\n");
        else
            outputStreamWriter.write("Not successful!\n");
        outputStreamWriter.flush();
        return result;
    }


}
