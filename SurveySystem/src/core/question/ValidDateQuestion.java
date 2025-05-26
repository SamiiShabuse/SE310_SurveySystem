package core.question;

import utils.InputValidator;

import java.util.*;

public class ValidDateQuestion extends Question {
    private String format;

    public ValidDateQuestion(String prompt, String format, boolean multiple) {
        super(prompt, multiple);
        this.format = format;
    }

    @Override
    public void display() {
        System.out.println(prompt + " (Enter date in format: " + format + ")");
    }

    @Override
    public List<String> takeResponse() {
        Scanner scanner = new Scanner(System.in);
        List<String> responses = new ArrayList<>();

        do {
            System.out.print("Date: ");
            String input = scanner.nextLine().trim();

            if (InputValidator.validateDate(input, format)) {
                responses.add(input);
                if (!multiple) break;
            } else {
                System.out.println("Invalid format. Please enter date in format: " + format);
            }
        } while (multiple);

        return responses;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public String getFormattedQuestion() {
        return prompt + " [Enter date in format: " + format + "]";
    }

}
