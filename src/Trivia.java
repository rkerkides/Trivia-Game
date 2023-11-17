import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Trivia {
    private Random rand;
    private HashMap<TriviaCategory, List<String>> triviaQuestions;
    private HashMap<TriviaCategory, List<String>> triviaAnswers;

    public Trivia() {
        this.rand = new Random();
        this.triviaQuestions = new HashMap<>();
        this.triviaAnswers = new HashMap<>();
        for (TriviaCategory category : TriviaCategory.values()) {
            triviaQuestions.put(category, new ArrayList<>());
            triviaAnswers.put(category, new ArrayList<>());
        }
    }

    // Reads the trivia questions from a text file and puts them in their respective HashMaps
    public void readTriviaQuestions() {
        try {
            // Open the file for reading
            BufferedReader reader = new BufferedReader(new FileReader("resources/trivia_questions_fixed.txt"));

            // Read the file line by line
            int lineNum = 1;
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    // Split the line by the delimiter "splitHere"
                    String[] parts = line.split("splitHere");

                    // Check if the parts array has the expected two elements
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Line format incorrect.");
                    }

                    // Separate each line into question and answer
                    String question = parts[0].trim();
                    String answer = parts[1].trim();

                    // Add the question and answer to the trivia data
                    putTriviaQuestions(question, answer);
                } catch (Exception e) {
                    // Catch exception if a line in the trivia file is improperly formatted
                    System.out.println("Error in line " + lineNum + ": " + e.getMessage());
                }
                lineNum++;
            }
            // Close the file
            reader.close();

            // Shuffle the questions in each category
            shuffleQuestions();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    public void putTriviaQuestions(String question, String answer) {
        TriviaCategory category = categorizeQuestion(question);
        triviaQuestions.get(category).add(question);
        triviaAnswers.get(category).add(answer);
    }

    public void shuffleQuestions() {
        for (List<String> questions : triviaQuestions.values()) {
            Collections.shuffle(questions);
        }
    }

    private TriviaCategory categorizeQuestion(String question) {
        if (question.startsWith("Words")) {
            return TriviaCategory.WORDS;
        } else if (question.startsWith("Music")) {
            return TriviaCategory.MUSIC;
        } else if (question.startsWith("Sports")) {
            return TriviaCategory.SPORTS;
        } else if (question.startsWith("TV/Film")) {
            return TriviaCategory.TV_FILM;
        } else if (question.startsWith("History")) {
            return TriviaCategory.HISTORY;
        } else if (question.startsWith("Acronym")) {
            return TriviaCategory.ACRONYM;
        } else if (question.startsWith("Biology")) {
            return TriviaCategory.BIOLOGY;
        } else if (question.startsWith("Animals")) {
            return TriviaCategory.ANIMALS;
        } else if (question.startsWith("Name the")) {
            return TriviaCategory.NAME_THE_YEAR;
        } else if (question.startsWith("Astronomy")) {
            return TriviaCategory.ASTRONOMY;
        } else if (question.startsWith("Geography")) {
            return TriviaCategory.GEOGRAPHY;
        } else if (question.startsWith("Food & Dr")) {
            return TriviaCategory.FOOD_AND_DRINK;
        } else if (question.startsWith("Art")) {
            return TriviaCategory.ART;
        } else if (question.startsWith("A ")) {
            return TriviaCategory.FILL_IN_THE_BLANK;
        } else {
            return TriviaCategory.MISCELLANEOUS;
        }
    }


    public String getQuestion(TriviaCategory category) {
        List<String> questions = triviaQuestions.get(category);
        if (questions.isEmpty()) {
            return "No questions available for this category.";
        }
        int randomIndex = rand.nextInt(questions.size());
        return questions.get(randomIndex);
    }

    public String getAnswer(String question, TriviaCategory category) {
        List<String> questions = triviaQuestions.get(category);
        List<String> answers = triviaAnswers.get(category);
        int index = questions.indexOf(question);
        if (index != -1) {
            return answers.get(index);
        }
        return "Answer not found.";
    }


}
