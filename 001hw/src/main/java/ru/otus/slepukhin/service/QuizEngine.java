package ru.otus.slepukhin.service;

import lombok.Getter;
import ru.otus.slepukhin.dao.Dao;
import ru.otus.slepukhin.domain.Question;
import ru.otus.slepukhin.service.QuizIO.QuizIO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizEngine {
    @Getter
    private final Map<Question, String> result = new HashMap<>();
    @Getter
    private final List<Question> questions;
    private final QuizIO io;

    public QuizEngine(Dao<Question> questionDao, QuizIO io) {
        this.questions = questionDao.getAll();
        this.io = io;
    }

    public void process() {
        this.questions.forEach(question -> {
            String answer = this.askQuestion(question);
            result.put(question, answer);
        });
    }

    public void showResult() {
        if (this.questions.size() != this.result.size()) {
            return;
        }

        String preamble = "Result of quiz:";
        Integer rightAnswers = this.questions.stream().mapToInt(question -> {
            String yourAnswer = this.result.get(question);
            return question.isRightAnswer(yourAnswer) ? 1 : 0;
        }).sum();
        this.io.write(preamble + rightAnswers + "/" + this.questions.size());
    }

    private String askQuestion(Question question) {
        this.io.write(question.getQuestion());
        return this.io.read();
    }


}
