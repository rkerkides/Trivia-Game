import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
    private final Trivia trivia;
    private final Scanner scanner;
    private final PlayersList playersList;
    private int playerNum;
    private boolean isFirstPlay;
    private int categoryIndex;
    private final String[] categories;

    public UserInterface(Trivia trivia) {
        // Initialize variables and objects
        this.trivia = trivia;
        this.scanner = new Scanner(System.in);
        this.playerNum = 1;
        this.playersList = new PlayersList();
        this.isFirstPlay = true;
        this.categoryIndex = 0;
        this.categories = new String[]{"Music", "Sports", "History", "Name the year", "Geography", "Food and drink", "TV/Film", "Biology"
                , "Astronomy", "Acronyms", "Word-based", "Animals", "Art", "Fill in the blanks", "Miscellaneous"};
    }

    public int start() {
        System.out.println("Welcome to the trivia game!\n");
        playerNum = numberOfPlayers();
        playersList.setPlayers(playerNum, scanner);
        ArrayList<Player> players = playersList.getPlayers();

        // Loop through players and ask questions
        while (true) {
            for (Player player : players) {
                System.out.println();
                System.out.println(player.getName() + "'s turn!");
                // If this is not the first turn, players can choose to continue
                // in the same question category
                if (!isFirstPlay) {
                    questionTime(player, getCategory());
                } else {
                    // If not, players will be prompted to choose a category
                    // before being asked a question
                    questionTime(player, categoryTime());
                    isFirstPlay = false;
                }
            }
            // Print current statistics and ask user if the current game should
            // be stopped.
            int roundsEnd = endOfRound(players);
            if (roundsEnd != 0) {
                return roundsEnd;
            }
        }
    }

    public void questionTime(Player player, int categoryIndex) {
        // Get question from Trivia object and display it
        String question = trivia.getQuestion(categoryIndex - 1);
        System.out.println(question);
        System.out.println();
        System.out.println("Type 'a' when you are ready to view the correct answer!");
        while (!scanner.nextLine().equals("a")) {
            System.out.println("If you want to see the answer, please type 'a'!");
        }
        // Display the correct answer and ask for user's response
        System.out.println(trivia.getAnswer(question, categoryIndex - 1));
        System.out.println("Did you get it right? Be honest! (y/n)");
        responseLoop(player);
    }

    public int getCategory() {
        System.out.println("Would you like to continue in the same category? y/n");
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("y")) {
                return categoryIndex;
            } else if (input.equals("n")) {
                return categoryTime();
            } else {
                System.out.println("Invalid input. Please input 'y' or 'n'.");
            }
        }

    }

    public int categoryTime() {
        // Display list of categories and ask user to choose one
        System.out.println("Please select a category:");
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }
        // Get user to choose a category and ensure it is valid
        while (true) {
            try {
                categoryIndex = scanner.nextInt();
                if (categoryIndex < 1 || categoryIndex > 15) {
                    throw new IndexOutOfBoundsException();
                }
                return categoryIndex;

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid input. Please enter a number from 1 to 15.");
                scanner.nextLine(); // consume the remaining newline character
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume the remaining input
            }
        }
    }

    public int numberOfPlayers() {
        // Keep asking for input until a valid number of players is entered
        while (true) {
            try {
                // Ask for the number of players
                System.out.println("Choose number of players (1-6): ");
                int numberOfPlayers = Integer.parseInt(scanner.nextLine());

                // Check if the number of players is within the valid range
                if (numberOfPlayers < 1 || numberOfPlayers > 6) {
                    System.out.println("Number out of range. Please try again.");
                    // If the number is not valid, continue asking for input
                    continue;
                }
                // If the number is valid, return it
                return numberOfPlayers;
            } catch (NumberFormatException e) {
                // If the input is not a number, ask for valid input
                System.out.println("Invalid input. Please enter a valid number.");
            }

        }
    }

    public void responseLoop(Player player) {
        // Keep asking for input until a valid response is entered
        while (true) {
            String response = scanner.nextLine();
            if (response.equals("y")) {
                // If the response is "y", the player is correct
                player.correct();
                System.out.println("Congratulations! " + player);
                break;
            } else if (response.equals("n")) {
                // If the response is "n", the player is wrong
                player.wrong();
                System.out.println("We'll get 'em next time! " + player);
                break;
            } else {
                // If the response is not valid, ask for valid input
                System.out.println("Please type 'y' if you guessed correctly, or 'n' if you did not.");
            }
        }

    }

    public int endOfRound(ArrayList<Player> players) {
        System.out.println("End of round!");
        System.out.println("Type 'finish' if you want to end the current game and display the winner!");
        System.out.println("Any other input will continue the game.");
        // Players can type exit to stop the game and display the winner
        String exit = scanner.nextLine();
        if (exit.equalsIgnoreCase("finish")) {
            playersList.findWinner();
            return finishCalled();
        }
        System.out.println("Player Statistics:");
        for (Player player : players) {
            System.out.printf("%s: %d points, streak %d\n",
                    player.getName(), player.getScore(), player.getStreak());
        }
        return 0;
    }

    public int finishCalled() {
        while (true) { // keep asking until a valid input is given
            System.out.println("Do you want to start a new game (n) or exit (e)?");
            String input = scanner.nextLine().toLowerCase(); // get user input and convert to lowercase
            if (input.equals("n")) { // if user wants to start a new game
                return 1; // return 1 to indicate a new game should be started
            } else if (input.equals("e")) { // if user wants to exit
                return -1; // return -1 to indicate program should exit
            } else { // if user input is invalid
                System.out.println("Invalid input. Please enter 'n' for new game or 'e' for exit.");
            }
        }
    }


}

