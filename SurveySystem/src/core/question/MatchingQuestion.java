package core.question;

import utils.InputValidator;

import java.util.*;

public class MatchingQuestion extends Question {
    private Map<String, String> pairs;

    public MatchingQuestion(String prompt, Map<String, String> pairs) {
        super(prompt, false);
        this.pairs = pairs;
    }

    @Override
    public void display() {
        System.out.println(prompt + " (Match the left items to the right values)");

        // Show keys
        System.out.println("Match the following:");
        int i = 1;
        for (String key : pairs.keySet()) {
            System.out.println(i++ + ". " + key);
        }

        // Show values
        System.out.println("\nOptions to match:");
        Set<String> values = new HashSet<>(pairs.values());
        for (String value : values) {
            System.out.println("- " + value);
        }

        System.out.println("\nEnter your matches in the format: key=value (e.g. dog=bark)");
    }


    @Override
    public List<String> takeResponse() {
        Scanner scanner = new Scanner(System.in);
        List<String> responses = new ArrayList<>();

        System.out.println(prompt);
        System.out.println("\nMatch the following:");
        int i = 1;
        for (String key : pairs.keySet()) {
            System.out.println(i++ + ". " + key);
        }

        System.out.println("\nOptions to match:");
        Set<String> values = new HashSet<>(pairs.values());
        for (String value : values) {
            System.out.println("- " + value);
        }

        System.out.println("\nEnter your matches in the format key=value (e.g. dog=bark)");
        System.out.println("Type 'done' when finished.");

        while (true) {
            System.out.print("Match: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) break;

            if (!InputValidator.validateMatching(input)) {
                System.out.println("Invalid format. Please use key=value (e.g. cat=meow)");
                continue;
            }

            responses.add(input);
        }

        return responses;
    }



    public Map<String, String> getPairs() {
        return pairs;
    }

    @Override
    public String getFormattedQuestion() {
        StringBuilder sb = new StringBuilder(prompt + "\nMatch the following:\n");
        int i = 1;
        for (String key : pairs.keySet()) {
            sb.append("  ").append(i++).append(". ").append(key).append("\n");
        }

        sb.append("\nOptions:\n");
        Set<String> values = new HashSet<>(pairs.values());
        for (String val : values) {
            sb.append("  - ").append(val).append("\n");
        }

        return sb.toString();
    }


    public void setPairs(Map<String, String> newPairs) {
        this.pairs = new LinkedHashMap<>(newPairs);
    }

}
