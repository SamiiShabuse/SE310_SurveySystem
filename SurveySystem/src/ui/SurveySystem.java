package ui;

import core.menu.SurveyMenu;
import core.menu.TestMenu;

import java.util.Scanner;

public class SurveySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1) Survey Menu");
            System.out.println("2) Test Menu");
            System.out.println("3) Exit");
            System.out.print("Enter choice: ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    new SurveyMenu().show();
                    break;
                case "2":
                    new TestMenu().show(); // ✅ Your working TestMenu
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}

