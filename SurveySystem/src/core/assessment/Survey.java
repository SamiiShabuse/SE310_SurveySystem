package core.assessment;

import core.response.Answer;
import core.response.Response;
import core.question.Question;
import utils.Serializer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Survey implements Serializable {
    private String title;
    private List<Question> questions;

    public Survey(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public void displaySurvey() {
        System.out.println("=== Survey: " + title + " ===");
        for (int i = 0; i < questions.size(); i++) {
            System.out.print((i + 1) + ". ");
            questions.get(i).display();
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void saveToFile() {
        // Serialized
        File dir = new File("data/serialized/surveys/");
        dir.mkdirs();
        Serializer.serialize(this, "data/serialized/surveys/" + title + ".ser");

        // Readable
        File readableDir = new File("data/readable/surveys/");
        readableDir.mkdirs();
        try (PrintWriter writer = new PrintWriter("data/readable/surveys/" + title + ".txt")) {
            writer.println(toReadableFormat());
            System.out.println("Readable survey saved to /readable/surveys/");
        } catch (IOException e) {
            System.out.println("Failed to save readable survey: " + e.getMessage());
        }
    }

    public String toReadableFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Survey: ").append(title).append(" ===\n\n");
        for (int i = 0; i < questions.size(); i++) {
            sb.append((i + 1)).append(". ").append(questions.get(i).getFormattedQuestion()).append("\n\n");
        }
        return sb.toString();
    }


    public static Survey loadFromFile(String filename) {
        return (Survey) Serializer.deserialize("data/surveys/" + filename);
    }

    public Response takeSurvey() {
        Scanner scanner = new Scanner(System.in);
        Response response = new Response(this.title);

        System.out.println("=== Taking Survey: " + title + " ===");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ":");
            q.display();

            List<String> userAnswers = q.takeResponse();
            String joinedResponse = String.join(", ", userAnswers);

            Answer ans = new Answer(i + 1, q.prompt, joinedResponse);
            response.addAnswer(ans);
        }

        return response;
    }

    public void display() {
        System.out.println("=== Survey: " + title + " ===\n");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).getFormattedQuestion());
        }
    }

    public void modify() {
        if (questions.isEmpty()) {
            System.out.println("No questions in this survey.");
            return;
        }

        System.out.println("\nCurrent Questions:");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).prompt);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter question number to modify: ");
        int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
        if (index < 0 || index >= questions.size()) {
            System.out.println("Invalid question number.");
            return;
        }

        Question q = questions.get(index);
        System.out.println("Current prompt: " + q.prompt);
        System.out.print("Enter new prompt (leave blank to keep current): ");
        String newPrompt = scanner.nextLine().trim();
        if (!newPrompt.isEmpty()) {
            q.prompt = newPrompt;
        }

    }

}
