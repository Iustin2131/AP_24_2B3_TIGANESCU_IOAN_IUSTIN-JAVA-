import java.util.ArrayList;
import java.util.List;
public class Node {
    private int id;
    private String description;
    private List<Vehicle> vehicles;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    private String assignedVehicle;

    public Node(int id , String description) {
        this.id = id;
        this.description = description;

        if (description.substring(0, Math.min(description.length(), 5)).equals("Depot")) {
            vehicles = new ArrayList<>();
        }
    }

    public void add(Vehicle ... vehicles){
        if(this.vehicles==null){
            System.out.println("You can't add any car because your node isn't a depot. ");
        }

        for(Vehicle candidateVehicle: vehicles) {
            boolean existVehicles=false;
            for (Vehicle addedVehicle : this.vehicles) {
                if (candidateVehicle.getDescription().equals(addedVehicle.getDescription()) && candidateVehicle.getSeats() == addedVehicle.getSeats()) {
                     existVehicles=true;
                     break;
                }
            }
            if(existVehicles){
                System.out.println(" The vehicle " + candidateVehicle.getDescription() + " already exists. ");
            } else {
                this.vehicles.add(candidateVehicle);
            }
        }

    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setAssignedVehicle(Vehicle vehicle){
        this.assignedVehicle=vehicle.getDescription();
    }

    public String getAssignedVehicle() {
        return assignedVehicle;
    }
}
