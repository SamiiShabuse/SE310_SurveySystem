package core.response;

import java.io.Serializable;

public class Answer implements Serializable {
    private int questionNumber;
    private String questionPrompt;
    private String response;

    public Answer(int questionNumber, String questionPrompt, String response) {
        this.questionNumber = questionNumber;
        this.questionPrompt = questionPrompt;
        this.response = response;
    }

    public int getQuestionNumber() {
        return questionNumber;
        //TODO Make sure to implement
    }

    public String getResponse() {
        return response;
        //TODO Make sure to implement
    }

    public String getFormattedAnswer() {
        return questionNumber + ". " + questionPrompt + "\nYour Answer: " + response;
    }
}
