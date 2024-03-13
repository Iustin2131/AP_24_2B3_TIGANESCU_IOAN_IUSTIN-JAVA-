import java.util.List;
public class Trip {
    private String cityName;
    private String periodOfTime;

    private List<Attraction> attractions;

    public Trip(String name,String periodOfTime,  List<Attraction> atractions) {
        this.attractions = atractions;
        cityName=name;
        this.periodOfTime=periodOfTime;
    }

    public List<Attraction> getAtractions() {
        return attractions;
    }

    public String getName() {
        return cityName;
    }

    public void displayInformation() {
        System.out.println("City: " + cityName +" and period of time: " + periodOfTime);
        System.out.println("Attractions:");

        for (Attraction attraction : attractions) {
            System.out.println("- " + attraction.getName() );
        }
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

}
