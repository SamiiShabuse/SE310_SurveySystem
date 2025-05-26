package core.response;

import java.io.Serializable;
import java.util.*;

public class ResponseCorrectAnswer implements Serializable {
    private List<String> responses;

    public ResponseCorrectAnswer(List<String> responses) {
        this.responses = new ArrayList<>(responses);
    }

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = new ArrayList<>(responses);
    }

    public boolean compare(ResponseCorrectAnswer other) {
        if (other == null) return false;
        List<String> otherResponses = new ArrayList<>(other.getResponses());
        List<String> thisCopy = new ArrayList<>(this.responses);

        Collections.sort(thisCopy);
        Collections.sort(otherResponses);

        return thisCopy.equals(otherResponses);
    }

    @Override
    public String toString() {
        return String.join(", ", responses);
    }
}
