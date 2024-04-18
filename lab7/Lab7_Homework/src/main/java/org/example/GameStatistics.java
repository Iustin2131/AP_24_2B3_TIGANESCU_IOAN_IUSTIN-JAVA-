package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStatistics {
    private List<Player> players;
    private Map<Player, Integer> scores;

    public GameStatistics(List<Player> players) {
        this.players = players;
        this.scores = new HashMap<>();
    }

    public void calculateScores() {
        for (Player player : players) {
            List<Token> extractedTokens = player.getExtractedTokens();
            int score = calculateScoreFromTokens(extractedTokens);
            scores.put(player, score);
        }
    }

    private int calculateScoreFromTokens(List<Token> extractedTokens) {
        int score = 0;
        int size = extractedTokens.size();

        if (size < 2) {
            return score;
        }

        int currentIndex = 0;

        while (currentIndex < size - 1) {
            Token currentToken = extractedTokens.get(currentIndex);
            Token nextToken = extractedTokens.get(currentIndex + 1);

            if (currentToken.getSecond() == nextToken.getFirst()) {
                int coverIndex = currentIndex + 1;

                while (coverIndex < size - 1 && extractedTokens.get(coverIndex).getSecond() == extractedTokens.get(coverIndex + 1).getFirst()) {
                    if (extractedTokens.get(coverIndex).getSecond() > extractedTokens.get(currentIndex).getFirst()) {
                        score++;
                        coverIndex++;
                        break;
                    }
                    coverIndex++;
                }

                currentIndex = coverIndex;
            } else {
                currentIndex++;
            }
        }

        return score;
    }

    public void displayScores() {
        for (Player player : scores.keySet()) {
            System.out.println(player + " scored: " + scores.get(player) + " points and finished at " + player.getLastTokenExtractionTime());
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
                System.out.println("The winner is: " + winner + " with " + maxScore + " points!");
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
