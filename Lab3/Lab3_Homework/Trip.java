import java.time.LocalTime;

public class Trip<T extends LocalTime, U extends LocalTime > implements Payable, Visitable{
    private T startHour;
    private U endHour;

    private int cost;
    private String visitable;
    private String activity;

    public Trip(T startHour, U endHour, String activity, String visitable, int cost) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.activity = activity;
        this.cost=cost;
        this.visitable=visitable;
    }

    public T getStartHour() {
        return startHour;
    }

    public U getEndHour() {
        return endHour;
    }

    public String getActivity() {
        return activity;
    }

    @Override
    public int getPayable() {
        return cost;
    }

    @Override
    public String getVisitable() {
        return visitable;
    }

    @Override
    public String showOpeningHour() {
        return "Materia " + activity + " " + " incepe la ora " + startHour + " " + " si se termina la ora " + endHour + " care costa " + cost + " a fost " + visitable;
    }
}
