package core.menu;

import core.assessment.SurveyManager;
import core.menu.menu_options.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class MenuManager {
    private Scanner scanner;
    private SurveyManager surveyManager;
    private Map<Integer, MenuOption> options;

    public MenuManager() {
        this.scanner = new Scanner(System.in);
        this.surveyManager = new SurveyManager();
        this.options = new LinkedHashMap<>();
        registerOptions();
    }

    private void registerOptions() {
        // Implemented this way for cleaner scale and making it easier to manage
        options.put(1, new CreateSurveyOption(surveyManager));
        options.put(2, new TakeSurveyOption(surveyManager));
        options.put(3, new ListSurveyOption(surveyManager));
        options.put(4, new ViewResponseOption(surveyManager));
        options.put(5, new ModifySurveyOption(surveyManager));
        options.put(6, new ExitOption());
    }

    public void displayMainMenu() {

        // Did this so it would be cleaner to read
        boolean running = true;

        while (running) {
            System.out.println("\n===== Survey System Menu =====");
            for (Map.Entry<Integer, MenuOption> entry : options.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue().getLabel());
            }

            System.out.print("Choose an option: ");
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                MenuOption option = options.get(choice);
                if (option != null) {
                    option.execute();
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public abstract void show();
}
