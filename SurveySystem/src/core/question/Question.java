package core.question;

import java.io.Serializable;
import java.util.List;

/**
 * This class is abstract questions class that each question
 * type will be implemented with.
 */
public abstract class Question implements Serializable {

    public String prompt;
    protected boolean multiple;

    public Question(String prompt, boolean multiple) {
        this.prompt = prompt;
        this.multiple = multiple;
    }

    public abstract String getFormattedQuestion();


    public abstract void display();
    public abstract List<String> takeResponse();
}
