package core.menu.menu_options;

import core.assessment.SurveyManager;
import core.menu.MenuOption;

public class ModifySurveyOption implements MenuOption {
    private final SurveyManager manager;

    public ModifySurveyOption(SurveyManager manager) {
        this.manager = manager;
    }

    @Override
    public String getLabel() {
        return "Modify an existing survey";
    }

    @Override
    public void execute() {
        manager.modifySurvey();
    }
}
