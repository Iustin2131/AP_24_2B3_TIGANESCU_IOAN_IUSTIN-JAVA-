public class mColoringProblem {
    private int V = 24; // Numărul de noduri în graf
    private int color[]; // Un tablou pentru a stoca culoarea fiecărui nod

    // Verifică dacă o anumită culoare poate fi asignată unui nod fără a crea conflicte cu vecinii săi
    boolean isSafe(int v, int graph[][], int color[], int c) {
        for (int i = 0; i < V; i++)
            if (graph[v][i] == 1 && c == color[i])
                return false;
        return true;
    }

    // Funcția auxiliară recursivă pentru a rezolva problema de colorare a grafului
    boolean graphColoringUtil(int graph[][], int m, int color[], int v) {
        // Cazul de bază: Dacă toate nodurile au primit deja o culoare, returnăm true
        if (v == V)
            return true;

        // Considerăm acest nod și încercăm diferite culori
        for (int c = 1; c <= m; c++) {
            // Verificăm dacă asignarea culorii c la nodul v este validă
            if (isSafe(v, graph, color, c)) {
                color[v] = c; // Asignăm culoarea c nodului v

                // Recursiv asignăm culori restului nodurilor
                if (graphColoringUtil(graph, m, color, v + 1))
                    return true;

                // Dacă asignarea culorii c nu conduce la o soluție, o eliminăm
                color[v] = 0;
            }
        }

        // Dacă nu putem asigna nicio culoare acestui nod, returnăm false
        return false;
    }

    // Funcția principală care rezolvă problema de colorare a grafului folosind backtracking
    boolean graphColoring(int graph[][], int m) {
        // Inițializăm toate culorile cu 0, indicând că nu au fost asignate încă
        color = new int[V];
        for (int i = 0; i < V; i++)
            color[i] = 0;

        // Apelăm funcția auxiliară pentru a colora graful
        if (!graphColoringUtil(graph, m, color, 0)) {
            System.out.println("Nu există soluție");
            return false;
        }

        // Afișăm soluția
        printSolution(color);
        return true;
    }

    // Funcție utilitară pentru a afișa soluția
    void printSolution(int color[]) {
        System.out.println(" Culorile asignate sunt:");
        for (int i = 0; i < V; i++)
            System.out.println("Nodul "+ i + " " + (color[i]-1));
    }
}
