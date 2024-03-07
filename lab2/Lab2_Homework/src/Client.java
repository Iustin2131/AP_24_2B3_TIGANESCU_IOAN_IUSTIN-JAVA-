import java.util.Objects;

/**
 * The Client class represents a client in the problem domain.
 * Each client has a description.
 */
public class Client {
    private String description;

    /**
     * Constructs a new Client with the given description.
     *
     * @param n The description of the client.
     */
    public Client(String n){
        description = n;
    }

    /**
     * Gets the description of the client.
     *
     * @return
     */
    public String getSolution(){
        return description;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The reference object with which to compare.
     * @return True if this client is the same as the obj argument; false otherwise.
     */
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass())
            return false;

        Client client = (Client) obj;
        return Objects.equals(description, client.description);
    }

    /**
     * Returns a hash code value for the client.
     *
     * @return
     */
    public int hashCode(){
        return Objects.hash(description);
    }
}
