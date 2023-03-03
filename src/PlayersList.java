import java.util.ArrayList;
import java.util.Scanner;

public class PlayersList {
    private final ArrayList<Player> playersList;

    public PlayersList() {
        this.playersList = new ArrayList<>();
    }

    public ArrayList<Player> getPlayers() {
        return playersList;
    }

    // Method to set the players in the player list
    public void setPlayers(int playerNum, Scanner scanner) {
        for (int i = 1; i <= playerNum; i++) {
            // Get player name using a separate method
            String name = getPlayerName(i, scanner);
            // Create a new player object with the obtained name
            Player player = new Player(name);
            // Add the player object to the player list
            playersList.add(player);
        }
    }

    // Method to get player name with input validation
    private String getPlayerName(int playerNum, Scanner scanner) {
        while (true) {
            try {
                // Prompt for player name
                System.out.println("Player " + playerNum + "'s name: ");
                // Read player name input and remove leading/trailing whitespace
                String name = scanner.nextLine().trim();
                // Check if name is empty and throw exception if true
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Name cannot be empty!");
                }
                // Check if name already exists in the player list and throw exception if true
                if (hasPlayer(name)) {
                    throw new IllegalArgumentException("Name already used by another player! Please choose a different name.");
                }
                // Return the obtained name if it passes validation
                return name;
            } catch (IllegalArgumentException e) {
                // Catch and display input validation exceptions
                System.out.println(e.getMessage());
            }
        }
    }

    // Method to check if a player already exists in the player list
    public boolean hasPlayer(String name) {
        // Use stream and anyMatch to check if a player with the given name exists in the player list
        return playersList.stream()
                .anyMatch(player -> player.getName().equalsIgnoreCase(name));
    }

    //Method to find the winner and return a message congratulating them
    public void findWinner() {
        if (playersList.size() == 1) {
            System.out.println("This is a single player game. Good job, " + playersList.get(0).getName() + "! " +
                    "You win by default!");
            System.out.println(playersList.get(0));
            return;
        }

        ArrayList<Player> winners = new ArrayList<>(); // create a list to store winners
        int highestScore = 0;

        // loop through players to find the highest score
        for (Player player : playersList) {
            int score = player.getScore();
            if (score > highestScore) { // found a new highest score

                winners.clear(); // clear previous winners list
                winners.add(player); // add new winner to list
                highestScore = score; // update the highest score

            } else if (score == highestScore) { // found a tie with current highest score
                winners.add(player); // add player to list of winners
            }
        }

        // no winners found
        if (highestScore == 0) {
            System.out.println("No-one wins!");
            return;
        }

        // display winner(s) and other players
        System.out.print("Winner");
        if (winners.size() > 1) { // display "s" if there are multiple winners
            System.out.print("s");
        }
        System.out.print(": ");
        for (Player winner : winners) {
            System.out.printf("%s with %d points and a streak of %d! Congratulations!\n",
                    winner.getName(), winner.getScore(), winner.getStreak());
        }
        System.out.println("Other players:");
        for (Player player : playersList) {
            if (!winners.contains(player)) { // display non-winners
                System.out.printf("%s: %d points, streak %d\n",
                        player.getName(), player.getScore(), player.getStreak());
            }
        }
    }
}
