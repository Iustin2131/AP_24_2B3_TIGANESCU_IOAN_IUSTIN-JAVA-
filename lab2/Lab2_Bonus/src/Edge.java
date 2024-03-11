public class Edge {
    private String ID;
    private Node source;
    private Node destination;
    private int cost;

    public String getID() {
        return ID;
    }

    public Edge(Node source, Node destination, int cost) {
        this.destination=destination;
        this.cost=cost;
        this.source = source;
        ID=source.getDescription()+ "->"+destination.getDescription();
    }

    public Node getDestination() {
        return destination;
    }

    public Node getSource() {
        return source;
    }

    public int getCost() {
        return cost;
    }
}
