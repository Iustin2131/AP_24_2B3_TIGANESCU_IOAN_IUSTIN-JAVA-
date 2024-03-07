/**
 * The Solution class represents a solution to a problem.
 * It contains a description of the solution.
 */
public class Solution {
    private String description;

    /**
     * Constructs a new Solution with the given description.
     *
     * @param n
     */
    public Solution(String n) {
        description = n;
    }

    /**
     * Gets the description of the solution.
     *
     * @return
     */
    public String getSolution() {
        return description;
    }
}
