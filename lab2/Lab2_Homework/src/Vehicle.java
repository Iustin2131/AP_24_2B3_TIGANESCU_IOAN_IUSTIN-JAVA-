import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * The Vehicle class represents a vehicle in the problem Depot.
 * It is an abstract class that defines common properties and behavior for all vehicles.
 */
public abstract class Vehicle {
     private String description;
     private int index;

    /**
     * Constructs a new Vehicle with the given description and index.
     *
     * @param n
     * @param index
     */
     public Vehicle(String n, int index){
         description=n;
         this.index=index;
     }

    /**
     * Indicates whether some other object are equal to this class
     * @param obj
     * @return
     */
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj==null || getClass()!=obj.getClass())
            return false;

        Vehicle vehicle = (Vehicle) obj;
        return Objects.equals(description, vehicle.description);
    }

    /**
     *   Returns a hash code value for the vehicle.
     * @return
     */
    public int hashCode(){
        return Objects.hash(description);
    }

    /**
     * Displays the capacity of the vehicle.
     */
    public abstract void desplayCapacity();

    /**
     * Gets the description of the vehicle.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the index of the vehicle.
     *
     * @return
     */
    public int getIndex(){
        return index;
    }


}