package ru.otus.slepukhin.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class QuizResult {
    @Getter
    private final String studentName;
    private final Map<Question, String> result = new HashMap<>();
    private final int rightAnswersToPass;

    public QuizResult(String studentName, int rightAnswersToPass) {
        this.studentName = studentName;
        this.rightAnswersToPass = rightAnswersToPass;
    }

    public void applyQuestionAnswer(Question question, String answer) {
        result.put(question, answer);
    }

    public boolean isPassed() {
        int rightAnswers = result.keySet()
                                 .stream()
                                 .mapToInt(question -> {
                                     String yourAnswer = getAnswer(question);
                                     return question.isRightAnswer(yourAnswer) ? 1 : 0;
                                 })
                                 .sum();
        return rightAnswers >= rightAnswersToPass;
    }

    private String getAnswer(Question question) {
        return result.get(question);
    }
}
