package core.assessment;

import java.io.File;

public class SurveyLibrary {
    private final String surveyDirectory = "data/surveys/";

    public String[] listSurveys() {
        File folder = new File(surveyDirectory);
        String[] files = folder.list((dir, name) -> name.endsWith(".ser"));

        System.out.println("\n=== Available Surveys ===");
        if (files == null || files.length == 0) {
            System.out.println("No surveys found.");
        } else {
            for (String file : files) {
                System.out.println("- " + file.replace(".ser", ""));
            }
        }
        return files;
    }

    public Survey loadSurvey(String name) {
        return Survey.loadFromFile(name);
    }

    public boolean deleteSurvey(String name) {
        // TODO implement later
        File file = new File(surveyDirectory + name + ".ser");
        return file.exists() && file.delete();
    }

    public boolean surveyExists(String name) {
        // TODO Implement later
        File file = new File(surveyDirectory + name + ".ser");
        return file.exists();
    }
}
