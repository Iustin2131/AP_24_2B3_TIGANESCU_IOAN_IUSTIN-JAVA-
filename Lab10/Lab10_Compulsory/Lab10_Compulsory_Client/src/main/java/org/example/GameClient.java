package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int PORT = 8000;

        try (Socket socket = new Socket(serverAddress, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                System.out.println("Enter command: ");
                String command = console.readLine();
                if (command.equalsIgnoreCase("exit")) {
                    break;
                }
                out.println(command);
                String response = in.readLine();
                System.out.println(response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
