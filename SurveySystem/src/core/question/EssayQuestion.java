package core.question;

import java.util.*;

public class EssayQuestion extends Question {

    public EssayQuestion(String prompt) {
        super(prompt, false); // Essays are long-form
    }

    @Override
    public void display() {
        System.out.println(prompt + " (Essay): ");
    }

    @Override
    public List<String> takeResponse() {
        Scanner scanner = new Scanner(System.in);
        List<String> answer = new ArrayList<>();
        System.out.println("Type your response. Type 'END' on a new line when finished:");

        StringBuilder essay = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("END")) break;
            essay.append(line).append("\n");
        }

        answer.add(essay.toString().trim());
        return answer;
    }

    @Override
    public String getFormattedQuestion() {
        return prompt + " [Essay]";
    }

}
