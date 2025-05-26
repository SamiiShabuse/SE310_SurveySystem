package core.assessment;

import core.response.TestResult;
import core.response.Response;
import core.response.ResponseCorrectAnswer;
import core.question.EssayQuestion;
import core.question.Question;

import java.util.*;
import java.io.*;

public class Test extends Survey implements Serializable {
    private List<ResponseCorrectAnswer> correctAnswers = new ArrayList<>();

    public Test(String title) {
        super(title);
    }

    public void addCorrectAnswer(ResponseCorrectAnswer answer) {
        correctAnswers.add(answer);
    }

    public List<ResponseCorrectAnswer> getCorrectAnswers() {
        return correctAnswers;
    }

    public void display(boolean showAnswers) {
        System.out.println("=== Test: " + title + " ===\n");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println((i + 1) + ". " + q.getFormattedQuestion());
            if (showAnswers && i < correctAnswers.size()) {
                System.out.println("Correct Answer: " + String.join(", ", correctAnswers.get(i).getResponses()));
            }
            System.out.println();
        }
    }

    public TestResult grade(Response response) {
        int correct = 0;
        int total = 0;
        int skippedEssays = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            ResponseCorrectAnswer studentAnswer = new ResponseCorrectAnswer(response.getAnswers().get(i).response.getResponses());
            ResponseCorrectAnswer expected = correctAnswers.get(i);

            if (q instanceof EssayQuestion) {
                skippedEssays++;
                continue;
            }

            if (studentAnswer.compare(expected)) {
                correct++;
            }
            total++;
        }

        return new TestResult(total, correct, skippedEssays);
    }

    public static Test loadFromFile(String filename) {
        return (Test) Serializer.deserialize("data/serialized/surveys/" + filename);
    }

    @Override
    public void saveToFile() {
        File serializedDir = new File("data/serialized/surveys/");
        serializedDir.mkdirs();
        Serializer.serialize(this, "data/serialized/surveys/" + title + ".ser");

        File readableDir = new File("data/readable/surveys/");
        readableDir.mkdirs();
        try (PrintWriter writer = new PrintWriter("data/readable/surveys/" + title + ".txt")) {
            writer.println(toReadableFormat());
        } catch (IOException e) {
            System.out.println("Failed to save readable test: " + e.getMessage());
        }
    }

    @Override
    public String toReadableFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Test: ").append(title).append(" ===\n\n");
        for (int i = 0; i < questions.size(); i++) {
            sb.append((i + 1)).append(". ").append(questions.get(i).getFormattedQuestion()).append("\n\n");
        }
        return sb.toString();
    }

    @Override
    public void modify() {
        super.modify(); // reuse the prompt/option updating from Survey

        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to update a correct answer? (yes/no): ");
        String update = scanner.nextLine().trim();
        if (!update.equalsIgnoreCase("yes")) return;

        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).prompt);
        }

        System.out.print("Enter question number to update correct answer: ");
        int index = Integer.parseInt(scanner.nextLine().trim()) - 1;
        if (index < 0 || index >= questions.size()) {
            System.out.println("Invalid question number.");
            return;
        }

        Question q = questions.get(index);
        System.out.println("Enter new correct answer(s):");
        List<String> newAnswer = q.takeResponse();
        correctAnswers.set(index, new ResponseCorrectAnswer(newAnswer));

        System.out.println("Correct answer updated.");
    }

}
