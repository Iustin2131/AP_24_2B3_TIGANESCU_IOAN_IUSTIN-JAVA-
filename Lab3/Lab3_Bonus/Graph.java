import java.util.List;
import java.util.ArrayList;
public class Graph {
    private int V;
    List<List<Integer>> edges;
    public int getSize() {
        return V;
    }

    public List<List<Integer>> getEdges() {
        return edges;
    }

    public List<Integer> getEdges(int i) {
        return edges.get(i);
    }


    public Graph(int V) {
        this.V = V;
        edges = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            edges.add(new ArrayList<>());
        }
    }
    public void addEdge(int u, int v) {
        edges.get(u).add(v);
    }

    public String getDayName(int dayIndex) {
        String[] days = {"Luni", "Marti", "Miercuri", "Joi", "Vineri", "Sambata", "Duminica"};
        return days[dayIndex];
    }

}
