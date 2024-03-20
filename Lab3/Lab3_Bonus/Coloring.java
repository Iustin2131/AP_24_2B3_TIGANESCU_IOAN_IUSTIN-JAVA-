public class Coloring {
    private int V = 24;
    private int color[];

    boolean isSafe(int v, int graph[][], int color[], int c) {
        for (int i = 0; i < V; i++)
            if (graph[v][i] == 1 && c == color[i])
                return false;
        return true;
    }

    boolean graphColoringUtil(int graph[][], int m, int color[], int v) {

        if (v == V)
            return true;

        for (int c = 1; c <= m; c++) {
            if (isSafe(v, graph, color, c)) {
                color[v] = c;

                if (graphColoringUtil(graph, m, color, v + 1))
                    return true;

                color[v] = 0;
            }
        }
        return false;
    }
    boolean graphColoring(int graph[][], int m) {

        color = new int[V];
        for (int i = 0; i < V; i++)
            color[i] = 0;

        if (!graphColoringUtil(graph, m, color, 0)) {
            System.out.println("Nu există soluție");
            return false;
        }

        // Afișăm soluția
        printSolution(color);
        return true;
    }
    void printSolution(int color[]) {
        System.out.println(" Culorile asignate sunt:");
        for (int i = 0; i < V; i++)
            System.out.println("Nodul "+ i + " " + (color[i]-1));
    }
}
