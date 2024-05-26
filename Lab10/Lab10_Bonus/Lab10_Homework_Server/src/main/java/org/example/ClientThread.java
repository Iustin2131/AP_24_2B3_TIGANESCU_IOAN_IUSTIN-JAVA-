package org.example;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

@Getter
public class ClientThread extends Thread {
    private Socket clientSocket;
    private String playerName;
    private String gameId;
    private BufferedReader in;
    private PrintWriter out;
    private GameServer server;
    private boolean running = true;

    private Timer timer;
    private static final long TIME_LIMIT = 2 * 60 * 1000;

    public ClientThread(Socket clientSocket, GameServer server) {
        this.clientSocket = clientSocket;
        this.server = server;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error creating input/output streams for client socket: " + e.getMessage());
        }
    }

    public void startGame() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String message = "Time's up! Game over.";
                server.notifyAllPlayers(message);
                stopClient();
            }
        }, TIME_LIMIT);
    }

    public void stopGame() {
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void run() {
        try {
            String inputLine;
            while (running && (inputLine = in.readLine()) != null) {
                System.out.println("Received command from client: " + inputLine);
                String[] parts = inputLine.split(" ");
                String command = parts[0];

                switch (command) {
                    case "create":
                        if (parts.length == 2) {
                            String gameId = parts[1];
                            server.createGame(gameId);
                            out.println("Game created with ID: " + gameId);
                        } else {
                            out.println("Invalid command format for create. Usage: create <gameId>");
                        }
                        startGame();
                        break;
                    case "join":
                        if (parts.length == 3) {
                            gameId = parts[1];
                            playerName = parts[2];
                            String result = server.joinGame(gameId, playerName);
                            out.println(result);
                        } else {
                            out.println("Invalid command format for join. Usage: join <gameId> <player>");
                        }
                        break;
                    case "submit":
                        if (parts.length == 2) {
                            String move = parts[1];
                            String result = server.processMove(gameId,playerName, move);
                            out.println(result);
                        } else {
                            out.println("Invalid command format for submit. Usage: submit <player target>");
                        }
                        break;
                    case "leave":
                        if (parts.length == 2) {
                            String gameId = parts[1];
                            String result = server.leaveGame(gameId, playerName);
                            out.println(result);
                        } else {
                            out.println("Invalid command format for leave. Usage: leave <gameId>");
                        }
                        stopGame();
                        break;
                    case "stop":
                        stopClient();
                        out.println("Client thread stopped");
                        break;
                    case "stopServer":
                        server.stop();
                        out.println("Server stopped");
                        break;
                    default:
                        out.println("Unknown command: " + inputLine);
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            stopClient();
        }
    }

    public void stopClient() {
        running = false;
        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing client socket: " + e.getMessage());
        }
        stopGame();
    }
    public void sendMessage(String message) {
        out.println(message);
    }
}
