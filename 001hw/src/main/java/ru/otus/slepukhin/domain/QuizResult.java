package ru.otus.slepukhin.domain;

import java.util.HashMap;
import java.util.Map;

public class QuizResult {
    Map<Question, String> result = new HashMap<>();

    public void answerQuestion(Question question, String answer) {
        this.result.put(question, answer);
    }

    public String getAnswer(Question question) {
        return this.result.get(question);
    }
}
