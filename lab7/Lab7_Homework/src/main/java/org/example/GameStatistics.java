package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStatistics {
    private List<Player> players;
    private Map<Player, Integer> scores;
    private long gameDuration;

    public GameStatistics(List<Player> players, long gameDuration) {
        this.players = players;
        this.scores = new HashMap<>();
        this.gameDuration = gameDuration;
    }

    public void calculateScores() {
        for (Player player : players) {
            List<Token> extractedTokens = player.getExtractedTokens();
            int score = calculateScoreFromTokens(extractedTokens);
            scores.put(player, score);
        }
    }

    private int calculateScoreFromTokens(List<Token> extractedTokens) {
        int maxLength = 0;
        int currentLength = 1;

        for (int i = 0; i < extractedTokens.size() - 1; i++) {
            Token currentToken = extractedTokens.get(i);
            Token nextToken = extractedTokens.get(i + 1);

            if (currentToken.getSecond() == nextToken.getFirst() && currentToken.getFirst() == nextToken.getSecond()) {
                currentLength++;
            } else {
                if (currentLength > maxLength && currentToken.getFirst() == extractedTokens.get(i - currentLength + 1).getSecond()) {
                    maxLength = currentLength;
                }
                currentLength = 1;
            }
        }

        // Verificăm și ultimul element din listă pentru a actualiza lungimea maximă
        Token lastToken = extractedTokens.get(extractedTokens.size() - 1);
        Token secondLastToken = extractedTokens.get(extractedTokens.size() - 2);
        if (lastToken.getSecond() == secondLastToken.getFirst() && lastToken.getFirst() == secondLastToken.getSecond()) {
            currentLength++;
        }

        // Actualizăm lungimea maximă dacă este necesar
        if (currentLength > maxLength && lastToken.getFirst() == secondLastToken.getSecond()) {
            maxLength = currentLength-1;
        }

        return maxLength > 1 ? maxLength : 0; // Returnăm lungimea maximă a secvenței (sau 0 dacă nu există secvență validă)
    }


    public void displayScores() {
        for (Player player : scores.keySet()) {
            System.out.println(player + " scored: " + scores.get(player) + " points and finished at " + player.getLastTokenExtractionTime() + player.getExtractedTokens());
        }
    }

    public void determineWinner() {
        List<Player> winners = new ArrayList<>();
        int maxScore = Integer.MIN_VALUE;

        for (Player player : scores.keySet()) {
            int playerScore = scores.get(player);
            if (playerScore > maxScore) {
                maxScore = playerScore;
            }
        }

        for (Player player : scores.keySet()) {
            int playerScore = scores.get(player);
            if (playerScore == maxScore) {
                winners.add(player);
            }
        }

        if (!winners.isEmpty() && maxScore > 0) {
            if (winners.size() == 1) {
                Player winner = winners.get(0);
                System.out.println("\n The winner is: " + winner + " with " + maxScore + " points!");
            } else {
                System.out.println("There are multiple winners with " + maxScore + " points:");
                for (Player winner : winners) {
                    System.out.println("- " + winner);
                }
            }
        } else {
            System.out.println("No valid winner found!");
        }
    }
}
