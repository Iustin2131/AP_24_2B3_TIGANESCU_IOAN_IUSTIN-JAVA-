import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Trip<T extends LocalTime, U extends LocalTime > implements Payable, Visitable{
    private T startHour;
    private U endHour;
    private int cost;
    private int color;

    private String visitable;
    private String activity;
    private List<String> schedule= new ArrayList<>();

    public List<String> getSchedule() {
        return schedule;
    }

    public int getColor() {
        return color;
    }

    public Trip(T startHour, U endHour, String activity, String visitable, int cost, List<String> schedule) {
        this.schedule = schedule;
        this.startHour = startHour;
        this.endHour = endHour;
        this.activity = activity;
        this.cost=cost;
        this.visitable=visitable;
        color=-1;
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
        return "Opening hours:  " + startHour + " " + " -> " + endHour + " care costa " + cost + " credite";
    }
}
