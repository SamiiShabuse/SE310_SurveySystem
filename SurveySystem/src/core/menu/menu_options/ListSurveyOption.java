package core.menu.menu_options;
import core.assessment.SurveyManager;
import core.menu.MenuOption;

public class ListSurveyOption implements MenuOption {
    private final SurveyManager manager;

    public ListSurveyOption(SurveyManager manager) {
        this.manager = manager;
    }

    @Override
    public String getLabel() {
        return "List available surveys";
    }

    @Override
    public void execute() {
        manager.listSurveys();
    }
}
