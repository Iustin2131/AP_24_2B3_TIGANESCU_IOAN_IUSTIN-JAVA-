package org.example;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;

public class GameStatistics {
    private List<Player> players;
    private Map<Player, Integer> scores;
    private long gameDuration;
    private DefaultDirectedGraph<Integer, DefaultEdge> directedGraph;
    private int tokenMaxValue;

    public GameStatistics(List<Player> players, long gameDuration, int tokenMaxValue) {
        this.players = players;
        this.scores = new HashMap<>();
        this.gameDuration = gameDuration;
        this.tokenMaxValue = tokenMaxValue;
        this.directedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);
    }

    public void calculateScores() {
        for (Player player : players) {
            List<Token> extractedTokens = player.getExtractedTokens();
            int score = calculateScoreFromTokens(extractedTokens, player);
            scores.put(player, score);
        }
    }

    private int calculateScoreFromTokens(List<Token> extractedTokens, Player player) {
        for (Token token : extractedTokens) {
            directedGraph.addVertex(token.getFirst());
            directedGraph.addVertex(token.getSecond());
            directedGraph.addEdge(token.getFirst(), token.getSecond());
        }

        System.out.println("\n Directed Graph for player " + player + " with extracted tokens:");
        System.out.println(directedGraph);

        boolean satisfiesOresCondition = satisfiesOresCondition(directedGraph);
        System.out.println("\n Satisfies Ore's condition: " + satisfiesOresCondition + "\n");

        int maxLength = findLongestCycle(extractedTokens);
        return maxLength;
    }

    public boolean satisfiesOresCondition(DefaultDirectedGraph<Integer, DefaultEdge> graph) {
        int vertexCount = graph.vertexSet().size();
        for (Integer u : graph.vertexSet()) {
            for (Integer v : graph.vertexSet()) {
                if (!u.equals(v) && !graph.containsEdge(u, v)) {
                    int degreeU = graph.outDegreeOf(u);
                    int degreeV = graph.outDegreeOf(v);
                    if (degreeU + degreeV < vertexCount) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int findLongestCycle(List<Token> tokens) {
        int maxLength = 0;

        for (Token token : tokens) {
            int currentLength = findCycle(token.getFirst(), new HashSet<>(), new HashSet<>(), tokens);
            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }

    private int findCycle(int startToken, Set<Integer> visitedTokens, Set<Integer> currentPath, List<Token> tokens) {
        visitedTokens.add(startToken);
        currentPath.add(startToken);

        int maxLength = 0;

        for (Token token : tokens) {
            if (token.getFirst() == startToken) {
                if (currentPath.contains(token.getSecond())) {
                    // Found a cycle
                    int cycleLength = currentPath.size();
                    maxLength = Math.max(maxLength, cycleLength);
                } else if (!visitedTokens.contains(token.getSecond())) {
                    int cycleLength = findCycle(token.getSecond(), visitedTokens, currentPath, tokens);
                    maxLength = Math.max(maxLength, cycleLength);
                }
            }
        }

        currentPath.remove(startToken);
        return maxLength;
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
                System.out.println("\nThe winner is: " + winner + " with " + maxScore + " points!");
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
