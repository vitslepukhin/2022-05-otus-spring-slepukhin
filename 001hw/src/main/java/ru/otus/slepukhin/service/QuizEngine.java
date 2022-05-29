package ru.otus.slepukhin.service;

import lombok.Getter;
import ru.otus.slepukhin.dao.QuestionDao;
import ru.otus.slepukhin.domain.Question;
import ru.otus.slepukhin.domain.QuizResult;
import ru.otus.slepukhin.service.IO.IO;

public class QuizEngine {
    @Getter
    private final QuizResult result = new QuizResult();

    private final IO io;
    private final QuestionDao questionDao;

    public QuizEngine(QuestionDao questionDao, IO io) {
        this.questionDao = questionDao;
        this.io = io;
    }

    public QuizResult process(boolean showResult) {
        this.questionDao.getAll().forEach(question -> {
            String answer = this.askQuestion(question);
            result.answerQuestion(question, answer);
        });

        if (showResult) {
            this.showResult();
        }

        return this.result;
    }

    private void showResult() {
        String preamble = "Result of quiz:";
        int rightAnswers = questionDao.getAll().stream().mapToInt(question -> {
            String yourAnswer = this.result.getAnswer(question);
            return question.isRightAnswer(yourAnswer) ? 1 : 0;
        }).sum();
        this.io.write(preamble + rightAnswers + "/" + questionDao.getAll().size());
    }

    private String askQuestion(Question question) {
        this.io.write(question.getQuestion());
        return this.io.read();
    }


}
