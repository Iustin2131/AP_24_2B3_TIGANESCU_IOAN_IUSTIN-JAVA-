import java.util.ArrayList;
import java.util.List;

public class Graph {
    List<Node> nodes;
    List<Edge> edges;

    public Graph(){
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void  addNode(Node ... nodes){
        for(Node candidateNode: nodes){
            boolean existNode=false;
            for(Node addedNode: this.nodes){
                if(addedNode.getDescription().equals(candidateNode.getDescription()) && addedNode.getId()== candidateNode.getId()){
                    existNode=true;
                    break;
                }
            }
            if(existNode){
                System.out.println("The node " + candidateNode.getDescription()+ " already exists. ");
            } else {
                this.nodes.add(candidateNode);
            }
        }
    }
    public void  addEdge(Edge ... edges) {
        for (Edge candidateEdge: edges){
            boolean existEdge=false;
            for (Edge addesEdge: this.edges){
                if(candidateEdge.getID()!=null)
                if(addesEdge.getID().equals(candidateEdge.getID())){
                    existEdge=true;
                    break;
                }
            }
            if(existEdge){
                System.out.println("The edge " +  candidateEdge.getID() + " already exists. ");
            } else {
                this.edges.add(candidateEdge);
            }
        }
    }

    public List<Node> getClients(){
        List<Node> clients = new ArrayList<>();
        for(Node node: nodes){
            if(!node.getDescription().substring(0,5).equals("Depot")){
                clients.add(node);
            }
        }
        return clients;
    }
    public List<Node> getDepots(){
        List<Node> depots = new ArrayList<>();
        for(Node node: nodes){
            if(node.getDescription().substring(0,5).equals("Depot")){
                depots.add(node);
            }
        }
        return depots;
    }

}
