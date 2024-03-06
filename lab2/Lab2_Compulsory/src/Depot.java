enum DepotType{
    regular,
    premium
}
public class Depot {
    private String name;
    private DepotType type;

    public Depot(String name, DepotType type){
        this.name=name;
        this.type=type;
    }

    public String getName(){
        return name;
    }

    public DepotType getType(){
        return type;
    }

    public String toString(){
        return "Vehicle " + name + " " + type  ;
    }

}
