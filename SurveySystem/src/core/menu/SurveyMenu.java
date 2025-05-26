package core.menu;

import core.assessment.SurveyManager;

import java.util.Scanner;

public class SurveyMenu extends MenuManager {
    private final SurveyManager surveyManager;
    private final Scanner scanner;

    public SurveyMenu() {
        this.surveyManager = new SurveyManager();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void show() {
        while (true) {
            System.out.println("\n=== Survey Menu ===");
            System.out.println("1) Create a new Survey");
            System.out.println("2) Load an existing Survey");
            System.out.println("3) Save the current Survey");
            System.out.println("4) Display the current Survey");
            System.out.println("5) Take the current Survey");
            System.out.println("6) Modify the current Survey");
            System.out.println("7) Return to Main Menu");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    surveyManager.createSurvey();
                    break;
                case "2":
                    surveyManager.loadSurvey();
                    break;
                case "3":
                    surveyManager.saveSurvey();
                    break;
                case "4":
                    surveyManager.displaySurvey();
                    break;
                case "5":
                    surveyManager.takeSurvey();
                    break;
                case "6":
                    surveyManager.modifySurvey();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }
}
