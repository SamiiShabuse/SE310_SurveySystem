package core.response;

import java.io.File;

public class ResponseLibrary {
    private final String responseDirectory = "data/responses/";

    public void listResponses(String surveyTitle) {
        File folder = new File(responseDirectory);
        String[] files = folder.list((dir, name) -> name.startsWith(surveyTitle) && name.endsWith(".ser"));

        System.out.println("\n=== Responses for: " + surveyTitle + " ===");
        if (files == null || files.length == 0) {
            System.out.println("No responses found.");
        } else {
            for (String file : files) {
                System.out.println("- " + file.replace(".ser", ""));
            }
        }
    }

    public Response loadResponse(String filename) {
        return Response.loadFromFile(filename);
    }

    public boolean deleteResponse(String filename) {
        //TODO implement in the future
        File file = new File(responseDirectory + filename + ".ser");
        return file.exists() && file.delete();
    }

    public int countTotalResponses(String surveyTitle) {
        //TODO don't forget to implement
        File folder = new File(responseDirectory);
        String[] files = folder.list((dir, name) -> name.startsWith(surveyTitle) && name.endsWith(".ser"));
        return (files == null) ? 0 : files.length;
    }
}
