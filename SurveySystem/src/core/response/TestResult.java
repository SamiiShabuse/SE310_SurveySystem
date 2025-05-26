package core.response;

public class TestResult {
    private int totalQuestions;
    private int correctAnswers;
    private int essaySkipped;

    public TestResult(int totalQuestions, int correctAnswers, int essaySkipped) {
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.essaySkipped = essaySkipped;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getEssaySkipped() {
        return essaySkipped;
    }

    public double getScorePercentage() {
        return totalQuestions == 0 ? 0 : ((double) correctAnswers / totalQuestions) * 100.0;
    }

    public void display() {
        System.out.println("=== Test Result ===");
        System.out.println("Correct Answers: " + correctAnswers + "/" + totalQuestions);
        System.out.printf("Score: %.2f%%\n", getScorePercentage());
        System.out.println("Essay Questions Skipped: " + essaySkipped);
    }
}
