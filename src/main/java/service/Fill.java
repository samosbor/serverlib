package service;

/**
 * Created by samosbor on 5/18/18.
 */

public class Fill {
    /**
     * The username passed from the URL
     */
    String username;
    /**
     * The number of generations passed from the URL
     */
    int generations;

    /**
     * The constructor for the fill service object
     * @param username
     * @param generations
     */
    public Fill(String username, int generations) {
        this.username = username;
        this.generations = generations;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }
}
