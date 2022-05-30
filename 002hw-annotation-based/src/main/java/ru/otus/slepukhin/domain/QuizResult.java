package ru.otus.slepukhin.domain;

import java.util.HashMap;
import java.util.Map;

public class QuizResult {
    Map<Question, String> result = new HashMap<>();
    private final int rightAnswersToPass;

    public QuizResult(int rightAnswersToPass) {
        this.rightAnswersToPass = rightAnswersToPass;
    }

    public void answerQuestion(Question question, String answer) {
        this.result.put(question, answer);
    }

    public boolean isPassed() {
        int rightAnswers = this.result.keySet().stream()
                      .mapToInt(question -> {
                          String yourAnswer = this.getAnswer(question);
                          return question.isRightAnswer(yourAnswer) ? 1 : 0;
                      })
                      .sum();
        return rightAnswers >= this.rightAnswersToPass;
    }

    private String getAnswer(Question question) {
        return this.result.get(question);
    }
}
