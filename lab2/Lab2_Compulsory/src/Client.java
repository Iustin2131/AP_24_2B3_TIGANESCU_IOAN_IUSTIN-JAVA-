public class Client {
    private String name;

    public Client(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "Vehicle " + name + " " ;
    }

}
