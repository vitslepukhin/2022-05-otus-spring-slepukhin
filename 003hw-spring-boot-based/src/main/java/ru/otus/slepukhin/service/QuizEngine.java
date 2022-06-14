package ru.otus.slepukhin.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.otus.slepukhin.config.QuizProperties;
import ru.otus.slepukhin.dao.QuestionDao;
import ru.otus.slepukhin.dao.QuestionsLoadingException;
import ru.otus.slepukhin.domain.Question;
import ru.otus.slepukhin.domain.QuizResult;
import ru.otus.slepukhin.domain.Student;
import ru.otus.slepukhin.service.IO.IO;
import ru.otus.slepukhin.service.translation.Translation;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizEngine implements CommandLineRunner {
    private final IO io;
    private final QuestionDao questionDao;
    private final int rightAnswersToPassQuiz;
    private final Translation translation;


    public QuizEngine(
            QuestionDao questionDao, IO io, QuizProperties quizConfig, Translation translation
    ) {
        this.questionDao = questionDao;
        this.io = io;
        this.translation = translation;
        this.rightAnswersToPassQuiz = quizConfig.getRightAnswersToPassQuiz();
    }

    @Override
    public void run(String... args) {
        process();
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
        return io.request(translation.translate("quiz-engine.name-request"));
    }

    private void showResult(QuizResult quizResult) {
        String preamble = quizResult.getStudent()
                                    .getName() + " " + translation.translate("quiz-engine.result-preamble");
        String conclusion = quizResult.isPassed() ? translation.translate("quiz-engine.passed")
                                                  : translation.translate("quiz-engine.failed");
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
