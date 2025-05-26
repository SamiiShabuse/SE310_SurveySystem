package core.response;

import utils.Serializer;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response implements Serializable {
    private String surveyTitle;
    private List<Answer> answers;

    public Response(String surveyTitle) {
        this.surveyTitle = surveyTitle;
        this.answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void displayResponse() {
        System.out.println("=== Response to Survey: " + surveyTitle + " ===");
        for (Answer answer : answers) {
            System.out.println(answer.getFormattedAnswer());
            System.out.println();
        }
    }

    public void saveToFile(String fileName) {
        // Serialized
        File dir = new File("data/serialized/responses/");
        dir.mkdirs();
        Serializer.serialize(this, "data/serialized/responses/" + fileName + ".ser");

        // Readable
        File readableDir = new File("data/readable/responses/");
        readableDir.mkdirs();
        try (PrintWriter writer = new PrintWriter("data/readable/responses/" + fileName + ".txt")) {
            writer.println(toReadableFormat());
            System.out.println("Readable response saved to /readable/responses/");
        } catch (IOException e) {
            System.out.println("Failed to save readable response: " + e.getMessage());
        }
    }


    public static Response loadFromFile(String filename) {
        return (Response) Serializer.deserialize("data/responses/" + filename + ".ser");
    }

    public void saveAsText(String filename) {
        try (PrintWriter writer = new PrintWriter("data/responses/" + filename + ".txt")) {
            writer.println(toReadableFormat());
            System.out.println("Saved readable response to " + filename + ".txt");
        } catch (IOException e) {
            System.out.println("Failed to save response as text: " + e.getMessage());
        }
    }

    public String toReadableFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Response to Survey: ").append(surveyTitle).append(" ===\n\n");
        for (Answer a : answers) {
            sb.append("- ").append(a.getFormattedAnswer()).append("\n");
        }
        return sb.toString();
    }

    public String getSurveyTitle() {
        // TODO make sure to implement
        return surveyTitle;
    }
}
