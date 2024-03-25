package org.example;

import org.graph4j.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int nDrivers = 5000;
        int nPassengers = 5000;
        int n = nDrivers + nPassengers;
        Random random = new Random();

        Digraph g = GraphBuilder.numVertices(n)
                .estimatedNumEdges(n)
                .buildDigraph();

        System.out.println("-------------------------------------");
        for (int i = 0; i < nDrivers; i++) {
            if (random.nextDouble()<=0.1) {
                g.addEdge(i, nDrivers + i);
            }
        }

        System.out.println("-------------------------------------");
        System.out.println("Muchiile din digraf:");

        for (int i = 0; i < nDrivers; i++) {
            NeighborIterator it = g.neighborIterator(i);
            while (it.hasNext()) {
                int neighbor = it.next();
                if (neighbor >= nDrivers) {
                    System.out.println(i + " -> " + neighbor);
                }
            }
        }

        Digraph graph = GraphBuilder.numVertices(n)
                .estimatedNumEdges(n)
                .buildDigraph();

        CycleFinder cycleFinder = new CycleFinder(graph);

        for (int i = 0; i < nDrivers; i++) {
            for (int j = i + 1; j < nPassengers; j++) {
                graph.addEdge(i, j);
                if (cycleFinder.containsCycle()) {
                    graph.removeEdge(i, j);
                } else {
                    System.out.println("Adăugarea muchiei (" + i + ", " + j + ") nu formează un circuit.");
                }
            }
        }


        int[][] dp = new int[nDrivers + 1][nPassengers + 1];

        for (int i = 1; i <= nDrivers; i++) {
            for (int j = 1; j <= nPassengers; j++) {

                int driverDestination = i;
                int passengerDestination = j;

                if (driverDestination != passengerDestination) {
                    dp[i][j] = 1 + getMax(dp, i - 1, j - 1);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int maxCardinality = dp[nDrivers][nPassengers];
        System.out.println("Maximum cardinality of the set: " + maxCardinality);
    }

    private static int getMax(int[][] dp, int i, int j) {
        int max = Integer.MIN_VALUE;
        for (int k = 0; k <= i; k++) {
            for (int l = 0; l <= j; l++) {
                max = Math.max(max, dp[k][l]);
            }
        }
        return max;
    }
    }
