package core.assessment;

import core.question.*;

import java.util.*;

public class QuestionFactory {

    public static Question createQuestion(Scanner scanner) {
        // TODO CLEAN UP LATER
        //  maybe throw into the specific questions sections themslves
        //  figure out if this was necessary
        System.out.println("Choose question type:");
        System.out.println("1. True/False");
        System.out.println("2. Multiple Choice");
        System.out.println("3. Short Answer");
        System.out.println("4. Essay");
        System.out.println("5. Matching");
        System.out.println("6. Valid Date");
        System.out.print("Enter choice (1–6): ");

        int choice = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter the question prompt: ");
        String prompt = scanner.nextLine();

        switch (choice) {
            case 1: // True/False
                return new TrueFalseQuestion(prompt);

            case 2: // Multiple Choice
                System.out.print("Allow multiple answers? (yes/no): ");
                boolean multi = scanner.nextLine().trim().equalsIgnoreCase("yes");

                List<String> options = new ArrayList<>();
                System.out.println("Enter options (type 'done' to finish):");
                while (true) {
                    String opt = scanner.nextLine();
                    if (opt.equalsIgnoreCase("done")) break;
                    options.add(opt);
                }

                return new MultipleChoiceQuestion(prompt, options, multi);

            case 3: // Short Answer
                System.out.print("Allow multiple short answers? (yes/no): ");
                boolean allowMulti = scanner.nextLine().trim().equalsIgnoreCase("yes");
                return new ShortAnswerQuestion(prompt, allowMulti);

            case 4: // Essay
                return new EssayQuestion(prompt);

            case 5: // Matching
                Map<String, String> pairs = new LinkedHashMap<>();
                System.out.println("Enter match pairs (format: key=value), type 'done' to finish:");
                while (true) {
                    String line = scanner.nextLine();
                    if (line.equalsIgnoreCase("done")) break;
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        pairs.put(parts[0].trim(), parts[1].trim());
                    }
                }
                return new MatchingQuestion(prompt, pairs);

            case 6: // Valid Date
                System.out.print("Enter expected date format (e.g. yyyy-MM-dd): ");
                String format = scanner.nextLine();
                System.out.print("Allow multiple dates? (yes/no): ");
                boolean multipleDates = scanner.nextLine().trim().equalsIgnoreCase("yes");
                return new ValidDateQuestion(prompt, format, multipleDates);

            default:
                System.out.println("Invalid choice.");
                return null;
        }
    }
}
