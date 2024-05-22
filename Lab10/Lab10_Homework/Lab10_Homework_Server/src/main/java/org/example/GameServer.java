package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class GameServer {
    private int port;
    private ServerSocket serverSocket;
    private boolean running;
    private Map<String, Game> games;
    private List<ClientThread> clientThreads;

    public GameServer(int port) {
        this.port = port;
        this.running = true;
        this.games = new HashMap<>();
        this.clientThreads = new ArrayList<>();
    }

    public static void main(String[] args) {
        GameServer gameServer = new GameServer(8000);
        gameServer.start();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Game server started on port " + port);
            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThreads.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            if (running) {
                System.err.println("Error starting server on port " + port + ": " + e.getMessage());
            }
        } finally {
            stop();
            System.out.println("Server has been closed.");
        }
    }

    public void stop() {
        running = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            for (ClientThread clientThread : clientThreads) {
                clientThread.stopClient();
            }
        } catch (IOException e) {
            System.err.println("Error stopping server on port " + port + ": " + e.getMessage());
        }
    }

    public void createGame(String gameId) {
        games.put(gameId, new Game(gameId));
    }

    public String joinGame(String gameId, String player) {
        Game game = games.get(gameId);
        if (game == null) {
            return "Game with ID " + gameId + " does not exist.";
        } else {
            boolean added = game.addPlayer(player);
            if (added) {
                String grid = game.getGridForPlayer(player);
                return "@Joined game with ID: " + gameId + " . Your grid:@" + grid;
            } else {
                return "Game with ID " + gameId + " is full.";
            }
        }
    }

    public String leaveGame(String gameId, String player) {
        Game game = games.get(gameId);
        if (game != null) {
            if (game.isInProgress()) {
                String otherPlayer = game.getOtherPlayer(player);
                String endGameMessage = game.endGame(otherPlayer);
                return "Player " + player + " has left the game. " + endGameMessage;
            } else {
                game.removePlayer(player);
                return "Player " + player + " has left the game.";
            }
        } else {
            return "Game with ID " + gameId + " not found.";
        }
    }

    public Game getGame(String gameId) {
        return games.get(gameId);
    }

    public String processMove(String gameId,String playerName, String playerMove) {
        Game game = games.get(gameId);
        if (game == null) {
            return "Game with ID " + gameId + " does not exist.";
        }

        if (!playerMove.matches("[A-K][0-9]")) {
            return "Invalid target format. Use a letter from A to K followed by a digit from 0 to 9.";
        }

        String grid = game.getGridForPlayer(playerName);

        return "@" + game.submitMove(playerName, playerMove)+ "@" + grid;
    }
    public void notifyAllPlayers(String message) {
        for (ClientThread clientThread : clientThreads) {
            clientThread.sendMessage(message);
        }
    }

}
