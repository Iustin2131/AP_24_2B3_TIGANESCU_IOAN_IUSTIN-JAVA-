/**
 *  The location class represents a location in the problem domain
 *  Each location has a unique index and a descripton
 */
public class Location {
    private int count=0;
    private int index=-1;
    private String description;

    /**
     * Construct a new location with unique index
     * @param description The description of the new location
     */
    public Location(String description){
        index=count++;
        this.description=description;
    }

    /**
     * @return The description of location
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The index of the location
     */
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
