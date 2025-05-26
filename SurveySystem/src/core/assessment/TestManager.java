package core.assessment;

import core.response.TestResult;
import core.response.Response;
import core.response.ResponseCorrectAnswer;
import core.question.Question;

import java.util.*;

public class TestManager extends SurveyManager {
    private Test currentTest;

    public void createTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter test title: ");
        String title = scanner.nextLine().trim();
        currentTest = new Test(title);

        boolean adding = true;
        while (adding) {
            Question q = QuestionFactory.createQuestion(scanner);
            if (q != null) {
                currentTest.addQuestion(q);
                currentTest.addCorrectAnswer(promptForCorrectAnswer(q));
            }
            System.out.print("Add another question? (yes/no): ");
            adding = scanner.nextLine().trim().equalsIgnoreCase("yes");
        }

        currentTest.saveToFile();
    }

    private ResponseCorrectAnswer promptForCorrectAnswer(Question q) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the correct answer(s) for this question:");
        List<String> correct = q.takeResponse();
        return new ResponseCorrectAnswer(correct);
    }

    public void displayTest(boolean showAnswers) {
        if (currentTest == null) {
            System.out.println("No test loaded.");
            return;
        }
        currentTest.display(showAnswers);
    }

    public void loadTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter test filename (without extension): ");
        String filename = scanner.nextLine().trim();
        currentTest = Test.loadFromFile(filename);
        if (currentTest != null) {
            System.out.println("Test loaded successfully.");
        }
    }

    public void saveTest() {
        if (currentTest != null) {
            currentTest.saveToFile();
            System.out.println("Test saved.");
        } else {
            System.out.println("No test loaded.");
        }
    }

    public void takeTest() {
        if (currentTest == null) {
            System.out.println("No test loaded.");
            return;
        }
        Response r = currentTest.takeSurvey();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter filename to save response: ");
        String name = scanner.nextLine().trim();
        r.saveToFile(name);
    }

    public void modifyTest() {
        if (currentTest == null) {
            System.out.println("No test loaded.");
            return;
        }
        currentTest.modify();
    }

    public void gradeTest() {
        if (currentTest == null) {
            System.out.println("No test loaded.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter response filename (without extension): ");
        String filename = scanner.nextLine().trim();
        Response response = Response.loadFromFile(filename);
        if (response == null) {
            System.out.println("Response not found.");
            return;
        }
        TestResult result = currentTest.grade(response);
        result.display();
    }

    public Test getCurrentTest() {
        return currentTest;
    }

    public boolean isTestLoaded() {
        return currentTest != null;
    }
}
