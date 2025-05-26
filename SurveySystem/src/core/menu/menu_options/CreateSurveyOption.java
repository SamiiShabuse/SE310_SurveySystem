package core.menu.menu_options;
import core.assessment.SurveyManager;
import core.menu.MenuOption;

public class CreateSurveyOption implements MenuOption {
    private final SurveyManager manager;

    public CreateSurveyOption(SurveyManager manager) {
        this.manager = manager;
    }

    @Override
    public String getLabel() {
        return "Create a new survey";
    }

    @Override
    public void execute() {
        manager.createSurvey();
    }
}
