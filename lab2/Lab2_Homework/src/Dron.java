import java.util.ArrayList;
import java.util.List;

/**
 * The Dron class represents a subbclass type of vehicle
 * Contain a capacity
 */
public class Dron extends Vehicle{
    private String capacitate;

    /**
     *  Constructs a new Dron with the given description, index, and capacity
     *  and with super we make the transmission to the parent builder
     * @param description
     * @param index
     * @param capacitate
     */
    public Dron(String description, int index, String capacitate){
        super(description,index);
        this.capacitate=capacitate;
    }
    /**
     * Desplys the capacity of the Dron
     */
    public void desplayCapacity(){
        System.out.println("Capacitatea Dron este " + capacitate );
    }

}
