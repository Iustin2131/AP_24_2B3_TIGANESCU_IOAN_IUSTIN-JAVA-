package org.example;

import java.io.*;
import java.net.Socket;

public class GameClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public GameClient(String serverAddress, int port) throws IOException {
        this.socket = new Socket(serverAddress, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendCommand(String command) {
        out.println(command);
    }

    public String readResponse() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    public static void main(String[] args) {
        try {
            GameClient client = new GameClient("localhost", 8000);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String command;

            do {
                System.out.println("Enter command: ");
                command = consoleReader.readLine();
                client.sendCommand(command);

                String response = client.readResponse();
                if (response.startsWith("@")) {
                    String[] parts = response.split("@");
                    for (String part : parts) {
                        System.out.println(part);
                    }
                } else {
                    System.out.println(response);
                }
            } while (!command.equalsIgnoreCase("STOP"));

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


