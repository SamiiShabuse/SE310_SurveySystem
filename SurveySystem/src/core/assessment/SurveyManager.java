package core.assessment;

import core.response.ResponseLibrary;
import core.question.MatchingQuestion;
import core.question.MultipleChoiceQuestion;
import core.question.Question;
import core.question.ValidDateQuestion;
import core.response.Response;
import utils.InputValidator;

import java.util.*;

public class SurveyManager {
    private Scanner scanner;
    private SurveyLibrary surveyLibrary;
    private ResponseLibrary responseLibrary;
    private Survey currentSurvey;


    public SurveyManager() {
        this.scanner = new Scanner(System.in);
        this.surveyLibrary = new SurveyLibrary();
        this.responseLibrary = new ResponseLibrary();
    }

    public Survey createSurvey() {
        System.out.print("Enter a title for your new survey: ");
        String title = scanner.nextLine();
        Survey survey = new Survey(title);

        boolean addMore = true;
        while (addMore) {
            System.out.println("\n=== Add a Question ===");
            Question q = QuestionFactory.createQuestion(scanner);
            if (q != null) {
                survey.addQuestion(q);
                System.out.println("Question added.");
            }

            System.out.print("Add another question? (yes/no): ");
            addMore = scanner.nextLine().trim().equalsIgnoreCase("yes");
        }
        currentSurvey = survey;
        survey.saveToFile();
        return survey;
    }

    public void takeSurvey() {
        Response response = null;

        System.out.print("Enter a filename for this response (without extension): ");
        String filename = scanner.nextLine().trim();

        response.saveToFile(filename);
        System.out.println("Response saved.");
    }

    public Survey loadSurvey() {
        String[] available = surveyLibrary.listSurveys();

        if (available.length == 0) {
            System.out.println("No surveys available to load.");
            return null;
        }

        System.out.println("\nPlease select a survey to load:");
        for (int i = 0; i < available.length; i++) {
            System.out.println((i + 1) + ") " + available[i]);
        }

        System.out.print("Enter your choice: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > available.length) {
                System.out.println("Invalid selection.");
                return null;
            }

            String selected = available[choice - 1];
            Survey s = surveyLibrary.loadSurvey(selected);
            if (s != null) {
                currentSurvey = s;
                System.out.println("Loaded survey: " + selected);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
        return null;
    }


    public Survey getCurrentSurvey() {
        // TODO IMPLEMENT
        return currentSurvey;
    }

    public void modifySurvey() {
        if (currentSurvey == null) {
            System.out.println("No survey/test is loaded.");
            return;
        }

        List<Question> questions = currentSurvey.getQuestions();
        if (questions.isEmpty()) {
            System.out.println("This survey has no questions.");
            return;
        }

        // Show all questions
        System.out.println("\nCurrent Questions:");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).prompt);
        }

        // Choose which question to modify
        System.out.print("Enter question number to modify: ");
        int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
        if (index < 0 || index >= questions.size()) {
            System.out.println("Invalid question number.");
            return;
        }

        Question q = questions.get(index);

        // Modify the prompt
        System.out.println("Current prompt: " + q.prompt);
        System.out.print("Enter new prompt (leave blank to keep current): ");
        String newPrompt = scanner.nextLine().trim();
        if (!newPrompt.isEmpty()) {
            q.prompt = newPrompt;
        }

        // Handle type-specific edits
        if (q instanceof MultipleChoiceQuestion) {
            MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) q;
            System.out.println("Current options: " + mcq.getOptions());
            System.out.print("Do you want to replace all options? (yes/no): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                List<String> newOptions = new ArrayList<>();
                System.out.println("Enter new options (type 'done' to finish):");
                while (true) {
                    String opt = scanner.nextLine().trim();
                    if (opt.equalsIgnoreCase("done")) break;
                    newOptions.add(opt);
                }
                mcq.setOptions(newOptions);
            }
        } else if (q instanceof MatchingQuestion) {
            MatchingQuestion mq = (MatchingQuestion) q;
            System.out.println("Current pairs: " + mq.getPairs());
            System.out.print("Do you want to replace all match pairs? (yes/no): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
                Map<String, String> newPairs = new LinkedHashMap<>();
                System.out.println("Enter new pairs (format: key=value, type 'done' to finish):");
                while (true) {
                    String line = scanner.nextLine().trim();
                    if (line.equalsIgnoreCase("done")) break;
                    if (InputValidator.validateMatching(line)) {
                        String[] parts = line.split("=");
                        newPairs.put(parts[0].trim(), parts[1].trim());
                    } else {
                        System.out.println("Invalid format. Use key=value.");
                    }
                }
                mq.setPairs(newPairs);
            }
        } else if (q instanceof ValidDateQuestion) {
            ValidDateQuestion vq = (ValidDateQuestion) q;
            System.out.println("Current format: " + vq.getFormat());
            System.out.print("Enter new format (leave blank to keep current): ");
            String fmt = scanner.nextLine().trim();
            if (!fmt.isEmpty()) {
                vq.setFormat(fmt);
            }
        }

        // Save updated survey
        currentSurvey.saveToFile();
        System.out.println("Survey updated and saved.");
    }




    public void listSurveys() {
        surveyLibrary.listSurveys();
    }


    public void viewResponse() {
        System.out.print("Enter survey title to list responses for: ");
        String surveyTitle = scanner.nextLine().trim();

        responseLibrary.listResponses(surveyTitle);

        System.out.print("Enter response filename (without .ser): ");
        String filename = scanner.nextLine().trim();

        Response r = responseLibrary.loadResponse(filename);
        if (r != null) {
            r.displayResponse();
        } else {
            System.out.println("Response not found.");
        }
    }


    public void saveSurvey() {
        if (currentSurvey != null) {
            currentSurvey.saveToFile();
            System.out.println("Survey saved.");
        } else {
            System.out.println("No survey loaded.");
        }
    }

    public void displaySurvey() {
        if (currentSurvey != null) {
            currentSurvey.display();
        } else {
            System.out.println("No survey loaded.");
        }
    }
}
