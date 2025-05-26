package core.menu;

import core.assessment.TestManager;

import java.util.Scanner;

public class TestMenu extends MenuManager {
    private final TestManager testManager;
    private final Scanner scanner;

    public TestMenu() {
        this.testManager = new TestManager();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void show() {
        while (true) {
            System.out.println("\n=== Test Menu ===");
            System.out.println("1) Create a new Test");
            System.out.println("2) Display the current Test WITHOUT correct answers");
            System.out.println("3) Display the current Test WITH correct answers");
            System.out.println("4) Load an existing Test");
            System.out.println("5) Save the current Test");
            System.out.println("6) Take the current Test");
            System.out.println("7) Modify the current Test");
            System.out.println("8) Grade a Test");
            System.out.println("9) Return to Main Menu");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    testManager.createTest();
                    break;
                case "2":
                    testManager.displayTest(false);
                    break;
                case "3":
                    testManager.displayTest(true);
                    break;
                case "4":
                    testManager.loadTest();
                    break;
                case "5":
                    testManager.saveTest();
                    break;
                case "6":
                    testManager.takeTest();
                    break;
                case "7":
                    testManager.modifyTest();
                    break;
                case "8":
                    testManager.gradeTest();
                    break;
                case "9":
                    return;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }
}
