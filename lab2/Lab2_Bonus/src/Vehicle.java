public class Vehicle {
    private int seats;
    private int freeSeats;
    private String description;

    public Vehicle(String description, int seats) {
        this.seats = seats;
        this.description=description;
        freeSeats=seats;
    }

    public int getSeats() {
        return seats;
    }

    public String getDescription() {
        return description;
    }
    public void setOccupiedSeat(){
        freeSeats--;
    }

    public int getFreeSeats() {
        return freeSeats;
    }
}
