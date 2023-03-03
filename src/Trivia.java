import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Trivia {
    private Random rand;
    private HashMap<String, String> wordQuestions;

    private HashMap<String, String> fillBlankQuestions;
    private HashMap<String, String> musicQuestions;
    private HashMap<String, String> acronymQuestions;
    private HashMap<String, String> animalQuestions;
    private HashMap<String, String> artQuestions;
    private HashMap<String, String> astronomyQuestions;
    private HashMap<String, String> biologyQuestions;
    private HashMap<String, String> foodAndDrinkQuestions;
    private HashMap<String, String> geographyQuestions;
    private HashMap<String, String> historyQuestions;
    private HashMap<String, String> nameTheYearQuestions;
    private HashMap<String, String> sportsQuestions;
    private HashMap<String, String> tvFilmQuestions;
    private HashMap<String, String> miscellaneousQuestions;
    private ArrayList<HashMap<String, String>> triviaList;

    // Constructor initializes the different HashMaps and ArrayList
    public Trivia() {
        this.wordQuestions = new HashMap<>();
        this.miscellaneousQuestions = new HashMap<>();
        this.acronymQuestions = new HashMap<>();
        this.animalQuestions = new HashMap<>();
        this.artQuestions = new HashMap<>();
        this.rand = new Random();
        this.musicQuestions = new HashMap<>();
        this.fillBlankQuestions = new HashMap<>();
        this.astronomyQuestions = new HashMap<>();
        this.biologyQuestions = new HashMap<>();
        this.sportsQuestions = new HashMap<>();
        this.tvFilmQuestions = new HashMap<>();
        this.foodAndDrinkQuestions = new HashMap<>();
        this.geographyQuestions = new HashMap<>();
        this.historyQuestions = new HashMap<>();
        this.nameTheYearQuestions = new HashMap<>();
        this.triviaList = new ArrayList<>();
    }

    // Reads the trivia questions from a text file and puts them in their respective HashMaps
    public void readTriviaQuestions() {
        try {
            // Open the file for reading
            BufferedReader reader = new BufferedReader(new FileReader("./resources/trivia_questions.txt"));


            // Read the file line by line
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by the delimiter '", "'
                String[] parts = line.split("\", \"");

                // Trim unwanted parts from the questions and answers in the text file
                String question = parts[0].substring(2);
                String answer = parts[1].substring(0, parts[1].length() - 1);

                // Add the first part as the key and the second part as the value to the HashMaps
                putTriviaQuestions(question, answer);

            }
            // Close the file
            reader.close();

            // Add the different HashMaps to the ArrayList
            formList();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Adds the different HashMaps to the ArrayList
    public void formList() {
        triviaList.add(musicQuestions);
        triviaList.add(sportsQuestions);
        triviaList.add(historyQuestions);
        triviaList.add(nameTheYearQuestions);
        triviaList.add(geographyQuestions);
        triviaList.add(foodAndDrinkQuestions);
        triviaList.add(tvFilmQuestions);
        triviaList.add(biologyQuestions);
        triviaList.add(astronomyQuestions);
        triviaList.add(acronymQuestions);
        triviaList.add(wordQuestions);
        triviaList.add(animalQuestions);
        triviaList.add(artQuestions);
        triviaList.add(fillBlankQuestions);
        triviaList.add(miscellaneousQuestions);

    }

    public void putTriviaQuestions(String question, String answer) {
        try {
            // Categorize the question based on its starting word(s)
            if (question.startsWith("A ")) {
                fillBlankQuestions.put(question, answer);
            } else if (question.startsWith("Art")) {
                artQuestions.put(question, answer);
            } else if (question.startsWith("Words")) {
                wordQuestions.put(question, answer);
            } else if (question.startsWith("Music")) {
                musicQuestions.put(question, answer);
            } else if (question.startsWith("Sports")) {
                sportsQuestions.put(question, answer);
            } else if (question.startsWith("TV/Film")) {
                tvFilmQuestions.put(question, answer);
            } else if (question.startsWith("History")) {
                historyQuestions.put(question, answer);
            } else if (question.startsWith("Acronym")) {
                acronymQuestions.put(question, answer);
            } else if (question.startsWith("Biology")) {
                biologyQuestions.put(question, answer);
            } else if (question.startsWith("Animals")) {
                animalQuestions.put(question, answer);
            } else if (question.startsWith("Name the")) {
                nameTheYearQuestions.put(question, answer);
            } else if (question.startsWith("Astronomy")) {
                astronomyQuestions.put(question, answer);
            } else if (question.startsWith("Geography")) {
                geographyQuestions.put(question, answer);
            } else if (question.startsWith("Food & Dr")) {
                foodAndDrinkQuestions.put(question, answer);
            } else {
                miscellaneousQuestions.put(question, answer);
            }
        } catch (StringIndexOutOfBoundsException e) {
            // If there is a StringIndexOutOfBoundsException, do nothing
        }
    }

    public String getQuestion(int index) {
        // Generate a random index between 0 and the size of the list at the given index
        int randomIndex = rand.nextInt(triviaList.get(index).size());

        // Iterate through the map at the given index to get the question at the random index
        Iterator<String> iterator = triviaList.get(index).keySet().iterator();
        String randomQuestion = null;
        for (int i = 0; i <= randomIndex; i++) {
            randomQuestion = iterator.next();
        }

        return randomQuestion;
    }


    public String getAnswer(String question, int index) {
        // Get the answer from the map at the given index of the list
        String answer = triviaList.get(index).get(question).substring(0, 1).
                toUpperCase() + triviaList.get(index).get(question).substring(1);

        // Remove the last character (if it is a double-quote)
        // All the answers in the text file would end with a double-quote
        // Hence why the double-quotes were trimmed earlier
        // BUT some answers have a further double-quote
        if (answer.charAt(answer.length() - 1) == '\"') {
            answer = answer.substring(0, answer.length() - 1);
        }

        return answer;
    }


}
