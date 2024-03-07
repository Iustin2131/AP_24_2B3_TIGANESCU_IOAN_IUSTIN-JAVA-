/**
 * The Truck class represents a subbclass type of vehicle
 * Contain a capacity
 */
public class Truck extends Vehicle{
    private String capacitate;

    /**
     *  Constructs a new Truck with the given description, index, and capacity
     *  and with super we make the transmission to the parent builder
     * @param n
     * @param index
     * @param cap
     */
 public Truck(String n, int index, String cap){
     super(n, index);
     capacitate=cap;

 }

/**
 * Desplays the capacity of Truck
 */
    public void desplayCapacity(){
     System.out.println("Capacitatea Truck este " + capacitate );
    }
}
