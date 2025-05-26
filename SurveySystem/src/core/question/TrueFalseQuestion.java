package core.question;

import utils.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrueFalseQuestion extends Question {

    public TrueFalseQuestion(String prompt) {
        super(prompt, false);
    }

    @Override
    public String getFormattedQuestion() {
        return prompt + " [True/False]";
    }

    @Override
    public void display() {
        System.out.println(prompt + " (True/False): ");
    }

    @Override
    public List<String> takeResponse() {
        Scanner scanner = new Scanner(System.in);
        List<String> response = new ArrayList<>();

        System.out.println(prompt);
        System.out.println("Enter 'True' or 'False'");

        String input;
        do {
            System.out.print("Answer: ");
            input = scanner.nextLine().trim();
            if (InputValidator.validateTF(input)) {
                System.out.println("Invalid input. Please enter 'True' or 'False'.");
            }
        } while (InputValidator.validateTF(input));

        response.add(input.toLowerCase()); // Store normalized input
        return response;
    }

}
