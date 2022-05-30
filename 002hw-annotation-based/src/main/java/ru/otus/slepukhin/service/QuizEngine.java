package ru.otus.slepukhin.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.slepukhin.dao.NotFoundQuestionsException;
import ru.otus.slepukhin.dao.QuestionDao;
import ru.otus.slepukhin.domain.Question;
import ru.otus.slepukhin.domain.QuizResult;
import ru.otus.slepukhin.service.IO.IO;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizEngine {

    private final IO io;
    private final QuestionDao questionDao;
    private final int rightAnswersToPassQuiz;

    public QuizEngine(QuestionDao questionDao, IO io, @Value("${rightAnswersToPassQuiz}") int rightAnswersToPassQuiz) {
        this.questionDao = questionDao;
        this.io = io;
        this.rightAnswersToPassQuiz = rightAnswersToPassQuiz;
    }

    public QuizResult process() {
        QuizResult result = new QuizResult(this.rightAnswersToPassQuiz);

        this.requestName();

        this.getQuestions()
            .forEach(question -> {
                String answer = this.askQuestion(question);
                result.answerQuestion(question, answer);
            });

        this.showResult(result);

        return result;
    }

    private void requestName() {
        this.request("What is your name?");
    }

    private void showResult(QuizResult result) {
        String preamble = "Result of quiz:";
        String conclusion = result.isPassed() ? "PASSED" : "FAILED";
        this.io.write(preamble + " " + conclusion);
    }

    private String askQuestion(Question question) {
        return this.request(question.getQuestion());
    }

    private String request(String query) {
        this.io.write(query);
        return this.io.read();
    }

    private List<Question> getQuestions() {
        try {
            return this.questionDao.getAll();
        } catch (NotFoundQuestionsException e) {
            return new ArrayList<>();
        }
    }
}
