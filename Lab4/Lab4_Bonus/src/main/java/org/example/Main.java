package org.example;

import org.graph4j.*;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int nDrivers = 5000;
        int nPassengers = 5000;
        int n = nDrivers + nPassengers;
        Random random = new Random();

        Graph<Integer, DefaultEdge> graph = new DefaultUndirectedGraph<>(DefaultEdge.class);

        System.out.println("-------------------------------------");
        for (int i = 0; i < nDrivers; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i < nPassengers; i++) {
            graph.addVertex(nDrivers + i);
        }

        for (int i = 0; i < nDrivers; i++) {
            if (random.nextDouble() <= 0.1) {
                graph.addEdge(i, nDrivers + i);
                System.out.println("Adăugarea muchiei (" + i + ", " + (nDrivers + i) + ") .");
            }
        }

        Set<Integer> partition1 = IntStream.range(0, nDrivers).boxed().collect(Collectors.toSet());
        Set<Integer> partition2 = IntStream.range(nDrivers, n).boxed().collect(Collectors.toSet());

        HopcroftKarpMaximumCardinalityBipartiteMatching<Integer, DefaultEdge> hk =
                new HopcroftKarpMaximumCardinalityBipartiteMatching<>(graph, partition1, partition2);

        int maxCardinality = hk.getMatching().getEdges().size();
        System.out.println("Maximum cardinality of the set: " + maxCardinality);
    }
}
