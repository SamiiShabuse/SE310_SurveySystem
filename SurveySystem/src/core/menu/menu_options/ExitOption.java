package core.menu.menu_options;
import core.menu.MenuOption;

public class ExitOption implements MenuOption {
    @Override
    public String getLabel() {
        return "Exit";
    }

    @Override
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
