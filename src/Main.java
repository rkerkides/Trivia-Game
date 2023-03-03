
public class Main {
    public static void main(String[] args) {

        Trivia trivia = new Trivia();
        // Read the trivia_questions.txt file and map answers onto questions
        trivia.readTriviaQuestions();


        int newGame = 0;

        // As long as the return value of the UI's start method is not -1
        // the program will proceed to start new instances of the trivia game
        // essentially acting as though the program was restarted
        // but without having to read the txt. file again
        while (newGame != -1) {
            UserInterface UI = new UserInterface(trivia);
            newGame = UI.start();
        }

        // The program will only ever arrive at this line if the user wishes to exit
        System.exit(0);

    }
}
