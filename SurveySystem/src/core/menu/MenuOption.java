package core.menu;

/**
 * Interface used for menu options to follow similar pattern
 * Makes it easier to keep adding options in the future
 */
public interface MenuOption {
    String getLabel();
    void execute();
}
