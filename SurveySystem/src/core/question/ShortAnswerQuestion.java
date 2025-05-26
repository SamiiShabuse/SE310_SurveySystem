package core.question;

import utils.InputValidator;

import java.util.*;

public class ShortAnswerQuestion extends Question {

    public ShortAnswerQuestion(String prompt, boolean multiple) {
        super(prompt, multiple);
    }

    @Override
    public void display() {
        System.out.println(prompt + (multiple ? " (You may enter multiple short answers. Type 'done' to finish.)" : ""));
    }

    @Override
    public String getFormattedQuestion() {
        return prompt + (multiple ? " [Multiple Short Answers]" : " [Short Answer]");
    }

    @Override
    public List<String> takeResponse() {
        Scanner scanner = new Scanner(System.in);
        List<String> responses = new ArrayList<>();

        System.out.println(prompt);
        if (multiple) {
            System.out.println("Enter multiple short answers. Type 'done' to finish.");
            while (true) {
                System.out.print("Answer: ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("done")) break;

                if (InputValidator.validateShortAnswer(input)) {
                    System.out.println("Answer cannot be empty. Please try again.");
                    continue;
                }

                responses.add(input);
            }
        } else {
            String input;
            do {
                System.out.print("Answer: ");
                input = scanner.nextLine().trim();
                if (InputValidator.validateShortAnswer(input)) {
                    System.out.println("Answer cannot be empty.");
                }
            } while (InputValidator.validateShortAnswer(input));

            responses.add(input);
        }

        return responses;
    }

}
