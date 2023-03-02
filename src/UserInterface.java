import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface {
    private final Trivia trivia;
    private final Scanner scanner;
    private final PlayersList playersList;
    private int playerNum;
    private boolean firstPlay;
    private int categoryIndex;
    private final String[] categories;

    public UserInterface() {
        this.trivia = new Trivia();
        this.scanner = new Scanner(System.in);
        this.playerNum = 1;
        this.playersList = new PlayersList();
        this.firstPlay = true;
        this.categoryIndex = 0;
        this.categories = new String[]{"Music", "Sports", "History", "Name the year", "Geography", "Food and drink", "TV/Film", "Biology"
                , "Astronomy", "Acronyms", "Word-based", "Animals", "Art", "Fill in the blanks", "Miscellaneous"};
    }

    public void start() {
        // Read the trivia_questions.txt file and extract Q/As into HashMaps
        // that will be inserted into an ArrayList
        trivia.readTriviaQuestions();

        System.out.println("Welcome to the trivia game!\n");
        playerNum = numberOfPlayers();
        playersList.setPlayers(playerNum, scanner);
        ArrayList<Player> players = playersList.getPlayers();


        while (true) {
            for (Player player : players) {
                System.out.println();
                System.out.println(player.getName() + "'s turn!");
                if (!firstPlay) {
                    questionTime(player, getCategory());
                } else {
                    questionTime(player, categoryTime());
                }
                firstPlay = false;
            }

        }
    }

    public void questionTime(Player player, int categoryIndex) {
        String question = trivia.getQuestion(categoryIndex - 1);
        System.out.println(question);
        System.out.println("Press enter when you are ready to view the correct answer!");
        scanner.nextLine();
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
        System.out.println("Please select a category:");
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }
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
        while (true) {
            try {
                System.out.println("Choose number of players (1-6): ");
                int numberOfPlayers = Integer.parseInt(scanner.nextLine());

                if (numberOfPlayers < 1 || numberOfPlayers > 6) {
                    System.out.println("Number out of range. Please try again.");
                    continue;
                }
                return numberOfPlayers;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }

        }
    }

    public void responseLoop(Player player) {
        while (true) {
            String response = scanner.nextLine();
            if (response.equals("y")) {
                player.correct();
                System.out.println("Congratulations! " + player);
                break;
            } else if (response.equals("n")) {
                player.wrong();
                System.out.println("We'll get 'em next time! " + player);
                break;
            } else {
                System.out.println("Please type 'y' if you guessed correctly, or 'n' if you did not.");
            }
        }

    }

}

