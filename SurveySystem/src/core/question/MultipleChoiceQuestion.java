package core.question;

import utils.InputValidator;

import java.util.*;

public class MultipleChoiceQuestion extends Question {
    private List<String> options;

    public MultipleChoiceQuestion(String prompt, List<String> options, boolean multiple) {
        super(prompt, multiple);
        this.options = options;
    }

    @Override
    public void display() {
        System.out.println(prompt);
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, options.get(i));
        }
        System.out.print("Choose option number(s): ");
    }

    @Override
    public List<String> takeResponse() {
        Scanner scanner = new Scanner(System.in);
        List<String> selected = new ArrayList<>();

        // Display options
        System.out.println(prompt);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ") " + options.get(i));
        }

        // Loop until valid input
        String input;
        while (true) {
            System.out.print("Enter your choice" + (multiple ? "s (separated by spaces or commas)" : "") + ": ");
            input = scanner.nextLine().trim();

            if (InputValidator.validateMultipleChoice(input, options.size(), multiple)) {
                break; // input is valid
            } else {
                System.out.println("Invalid input. Please enter valid option number(s).");
            }
        }

        // Parse valid input into answers
        String[] tokens = input.split("[,\\s]+");
        for (String token : tokens) {
            int index = Integer.parseInt(token) - 1;
            selected.add(options.get(index));
            if (!multiple) break;
        }

        return selected;
    }


    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String getFormattedQuestion() {
        StringBuilder sb = new StringBuilder(prompt + "\n");
        for (int i = 0; i < options.size(); i++) {
            sb.append("  ").append(i + 1).append(") ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }

}
