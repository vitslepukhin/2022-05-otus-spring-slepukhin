package ru.otus.slepukhin.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.slepukhin.dao.QuestionDao;
import ru.otus.slepukhin.dao.QuestionsLoadingException;
import ru.otus.slepukhin.domain.Question;
import ru.otus.slepukhin.domain.QuizResult;
import ru.otus.slepukhin.domain.Student;
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

    public void process() {
        var student = new Student(requestName());

        QuizResult result = new QuizResult(student, rightAnswersToPassQuiz);

        getQuestions().forEach(question -> {
            String answer = askQuestion(question);
            result.applyQuestionAnswer(question, answer);
        });

        showResult(result);
    }

    private String requestName() {
        return io.request("What is your name?");
    }

    private void showResult(QuizResult quizResult) {
        String preamble = quizResult.getStudent().getName() + " your result:";
        String conclusion = quizResult.isPassed() ? "PASSED" : "FAILED";
        io.write(preamble + " " + conclusion);
    }

    private String askQuestion(Question question) {
        return io.request(question.getQuestion());
    }

    private List<Question> getQuestions() {
        try {
            return questionDao.getAll();
        } catch (QuestionsLoadingException e) {
            io.write(e.getMessage());
            return new ArrayList<>();
        }
    }
}
