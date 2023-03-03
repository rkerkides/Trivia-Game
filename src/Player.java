import java.util.Objects;

public class Player {
    private final String name;
    private int score;
    private int streak;

    // Constructor that takes the player's name as a parameter
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.streak = 0;
    }

    // Getter method to retrieve the player's name
    public String getName() {
        return name;
    }

    // Getter method to retrieve the player's score
    public int getScore() {
        return score;
    }

    // Getter method to retrieve the player's streak
    public int getStreak() {
        return streak;
    }

    // Method to increase the player's score and streak by 1 when they answer correctly
    public void correct() {
        score++;
        streak++;
    }

    // Method to reset the player's streak to 0 when they answer incorrectly
    public void wrong() {
        streak = 0;
    }

    // Overridden toString method to return a formatted string representing the player's score and streak
    @Override
    public String toString() {
        return "Player " + name + "'s score: " + score + ", streak: " + streak;
    }

    // Overridden equals method to compare players based on their names
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    // Overridden hashCode method to generate a hash code based on the player's name
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
