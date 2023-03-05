import java.io.*;

public class Main {
    public static void main(String[] args) {

        // Create a new Trivia object
        Trivia trivia = new Trivia();

        // remove unwanted parts from the trivia file
        // and create a new, clean file for use within the program
        fixTextArrangement();

        // Read the trivia_questions.txt file and map answers onto questions
        trivia.readTriviaQuestions();

        // Initialize variable newGame
        boolean exitGame = false;

        // As long as the return value of the UI's start method is not -1
        // the program will proceed to start new instances of the trivia game
        // allowing to change the number of player, allowing players to select new names and resetting stats
        while (!exitGame) {
            UserInterface UI = new UserInterface(trivia);
            int userDecision = UI.start();
            //
            if (userDecision == -1) {
                exitGame = true;
            }
        }

        // The program will only ever arrive at this line if the user wishes to exit
        System.exit(0);

    }

    // A method for removing unwanted parts from the trivia file
    // and creating a new, clean file for use within the program
    public static void fixTextArrangement() {
        try {
            // Open the file for reading
            BufferedReader reader = new BufferedReader(new FileReader("resources/trivia_questions.txt"));

            // Create a new file to write the fixed format data
            String fixedFilePath = "resources/trivia_questions.txt".replace(".txt", "_fixed.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(fixedFilePath));

            // Read the file line by line
            int lineNum = 1;
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by the delimiter '", "'
                String[] parts = line.split("\", \"");


                try {
                    // Trim unwanted parts from the questions and answers in the text file
                    String question = parts[0].substring(2);
                    String answer = parts[1].substring(0, parts[1].length() - 2);

                    // Write to file, each line should have the question, followed by a string "splitHere" and then the answer
                    writer.write(question + "splitHere" + answer);
                    writer.newLine();
                } catch (Exception e) {
                    System.out.println("Error occurred at line " + lineNum);
                }
                lineNum++;
            }

            // Close the input and output streams
            reader.close();
            writer.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
