package ru.otus.slepukhin.service;

import org.springframework.stereotype.Service;
import ru.otus.slepukhin.config.QuizConfigProvider;
import ru.otus.slepukhin.dao.QuestionDao;
import ru.otus.slepukhin.dao.QuestionsLoadingException;
import ru.otus.slepukhin.domain.Question;
import ru.otus.slepukhin.domain.QuizResult;
import ru.otus.slepukhin.domain.Student;
import ru.otus.slepukhin.service.IO.IO;
import ru.otus.slepukhin.service.translator.Translator;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizEngine {
    private final IO io;
    private final QuestionDao questionDao;
    private final int rightAnswersToPassQuiz;
    private final Translator translator;


    public QuizEngine(
            QuestionDao questionDao, IO io, QuizConfigProvider quizConfig, Translator translator
    ) {
        this.questionDao = questionDao;
        this.io = io;
        this.translator = translator;
        this.rightAnswersToPassQuiz = quizConfig.getRightAnswersToPassQuiz();
    }

    public void process() {
        var studentName = requestName();
        var student = new Student(studentName);

        QuizResult result = new QuizResult(student, rightAnswersToPassQuiz);

        getQuestions().forEach(question -> {
            String answer = askQuestion(question);
            result.applyQuestionAnswer(question, answer);
        });

        showResult(result);
    }

    private String requestName() {
        return io.request(translator.translate("quiz-engine.name-request"));
    }

    private void showResult(QuizResult quizResult) {
        String preamble = quizResult.getStudent()
                                    .getName() + " " + translator.translate("quiz-engine.result-preamble");
        String conclusion = quizResult.isPassed() ? translator.translate("quiz-engine.passed")
                                                  : translator.translate("quiz-engine.failed");
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
