public class Vehicle {
    private  String name;
    private Depot depot;

    public Vehicle(String n, Depot d){
        name=n;
        depot=d;
    }
    public String getName(){
        return name;
    }

    public Depot getDepot(){
        return depot;
    }

    public String toString(){
        return "Vehicle " + name + " " + depot   ;
    }
}
