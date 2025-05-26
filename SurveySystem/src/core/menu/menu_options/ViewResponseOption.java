package core.menu.menu_options;

import core.assessment.SurveyManager;
import core.menu.MenuOption;

public class ViewResponseOption implements MenuOption {
    private final SurveyManager manager;

    public ViewResponseOption(SurveyManager manager) {
        this.manager = manager;
    }

    @Override
    public String getLabel() {
        return "View a saved response";
    }

    @Override
    public void execute() {
        manager.viewResponse();
    }
}
