import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * The Depot class represents a depot in the problem domain.
 * It contains a name and a list of vehicles associated with it.
 */
public class Depot {
    private String name;
    private List<Vehicle> vehicles;

    /**
     * Constructs a new Depot with the given name.
     *
     * @param n The name of the depot.
     */
    public Depot(String n) {
        name = n;
        vehicles = new ArrayList<>();
    }

    /**
     * Gets the name of the depot.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public int getVehicleSize() {
        return vehicles.size();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The reference object with which to compare.
     * @return True if this depot is the same as the obj argument; false otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
            return false;

        Depot depot = (Depot) obj;
        return Objects.equals(name, depot.name);
    }

    /**
     * Returns a hash code value for the depot.
     *
     * @return
     */
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Gets the list of vehicles associated with the depot.
     *
     * @return
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Adds a vehicle to the list of vehicles associated with the depot.
     * @param newVehicles
     */
    public void addVehicle(Vehicle ... newVehicles) {

        for(Vehicle vehicle : newVehicles) {

            boolean vehicleExists=false;

            for(Vehicle existingVehicle : vehicles){
                if(existingVehicle.getDescription().equals(vehicle.getDescription())){
                    vehicleExists= true;
                    break;
                }
            }

            if(vehicleExists){
                System.out.println("This description " + vehicle.getDescription()+" is already exists." );
            } else {
                vehicles.add(vehicle);
            }
        }
    }


}
