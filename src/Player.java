import java.util.Objects;

public class Player {
    private String name;
    private int score;
    private int streak;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.streak = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void correct() {
        score++;
        streak++;
    }

    public void wrong() {
        streak = 0;
    }

    public int getStreak() {
        return streak;
    }

    @Override
    public String toString() {
        return "Player " + name + "'s score: " + score + ", streak: " + streak;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
