package edu.school21.socket.client;

import java.net.*;
import java.io.*;

public class Client {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8000);
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(clientSocket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(bufferedReader.readLine());
        System.out.print(bufferedReader.readLine());
        String send = reader.readLine() + '\n';
        outputStreamWriter.write(send);
        outputStreamWriter.flush();
        String response = bufferedReader.readLine();
        if (response.equals("Enter username:")){
            System.out.println(response);
            System.out.print(bufferedReader.readLine());
            send = reader.readLine() + '\n';
            outputStreamWriter.write(send);
            outputStreamWriter.flush();
            System.out.println(bufferedReader.readLine());
            System.out.print(bufferedReader.readLine());
            send = reader.readLine() + '\n';
            outputStreamWriter.write(send);
            outputStreamWriter.flush();
            String result = bufferedReader.readLine();
            if (result.equals("Successful!"))
                System.out.println(ANSI_GREEN + result + ANSI_RESET);
            else
                System.out.println(ANSI_RED + result + ANSI_RESET);
        }
        reader.close();
        bufferedReader.close();
        outputStreamWriter.close();
    }
}
