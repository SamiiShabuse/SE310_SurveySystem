package core.menu.menu_options;

import core.assessment.Survey;
import core.assessment.SurveyManager;
import core.menu.MenuOption;

public class TakeSurveyOption implements MenuOption {
    private final SurveyManager manager;

    public TakeSurveyOption(SurveyManager manager) {
        this.manager = manager;
    }

    @Override
    public String getLabel() {
        return "Load and take a survey";
    }

    @Override
    public void execute() {
        Survey s = manager.loadSurvey();
        if (s != null) {
            manager.takeSurvey(s);
        } else {
            System.out.println("Failed to load survey.");
        }
    }
}
