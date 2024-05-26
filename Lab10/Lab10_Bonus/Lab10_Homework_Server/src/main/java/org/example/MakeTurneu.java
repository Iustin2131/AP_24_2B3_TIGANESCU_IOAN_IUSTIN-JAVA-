package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.graph4j.Digraph;
import org.graph4j.GraphBuilder;
import org.graph4j.support.Tournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
public class MakeTurneu {

    private int nrJucatori;
    private Digraph digraph;
    private Tournament tournament;

    public MakeTurneu(int nrJucatori) {
        this.nrJucatori = nrJucatori;
        this.digraph = GraphBuilder.numVertices(nrJucatori).estimatedNumEdges((nrJucatori*(nrJucatori-1)/2)).buildDigraph();
        this.tournament = new Tournament(digraph);
    }

    public Digraph turneu() {
        Random random = new Random();
        int sansa;
        for(int i=0; i<nrJucatori-1; i++) {
            for(int j=i+1; j<nrJucatori; j++) {
                sansa = random.nextInt(2);
                if(sansa == 1) {
                    digraph.addEdge(i, j);
                } else {
                    digraph.addEdge(j, i);
                }
            }
        }
        return digraph;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti numarul de jucatori: ");
        int nrJ = scanner.nextInt();
        System.out.print("numarul de zile: ");
        int nrZ = scanner.nextInt();
        System.out.print("numarul de jocuri pe zi pentru un jucator: ");
        int nrG = scanner.nextInt();
        scanner.close();

        MakeTurneu turneu = new MakeTurneu(nrJ);
        Digraph digraph = turneu.turneu();

        int numarJocuriTotal = nrJ * (nrJ - 1) / 2;   //nr muchii
        int numarJocuriPeZi = numarJocuriTotal / nrZ;
        List<int[]> jocuri = new ArrayList<>();

        for (int i = 0; i < nrJ; i++) {
            for (int j = i + 1; j < nrJ; j++) {
                if (digraph.containsEdge(i, j)) {
                    jocuri.add(new int[]{i, j});
                } else if (digraph.containsEdge(j, i)) {
                    jocuri.add(new int[]{j, i});
                }
            }
        }

        Collections.shuffle(jocuri, new Random());

        List<List<int[]>> jocuriPeZi = new ArrayList<>();
        for (int i = 0; i < nrZ; i++) {
            jocuriPeZi.add(new ArrayList<>());
        }

        for (int i = 0; i < jocuri.size(); i++) {
            jocuriPeZi.get(i % nrZ).add(jocuri.get(i));
        }

        for (int i = 0; i < jocuriPeZi.size(); i++) {
            System.out.println("Ziua " + (i + 1) + ":");
            for (int[] joc : jocuriPeZi.get(i)) {
                System.out.println("Jucatorul " + joc[0] + " vs Jucatorul " + joc[1]);
            }
            System.out.println();
        }
    }
}
