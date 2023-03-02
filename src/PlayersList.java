import java.util.ArrayList;
import java.util.Scanner;

public class PlayersList {
    private ArrayList<Player> playersList;

    public PlayersList() {
        this.playersList = new ArrayList<>();
    }

    public ArrayList<Player> getPlayers() {
        return playersList;
    }

    public void setPlayers(int playerNum, Scanner scanner) {
        for (int i = 1; i <= playerNum; i++) {
            while (true) {
                System.out.println("Player " + i + "'s name: ");
                String name = scanner.nextLine();
                if (hasPlayer(name)) {
                    System.out.println("Name already used by another player!" +
                            " Please choose a different name.");
                    continue;
                }
                Player player = new Player(name);
                playersList.add(player);
                break;
            }

        }
    }

    public boolean hasPlayer(String name) {
        for (Player player : playersList) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
