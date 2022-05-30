package ru.otus.slepukhin.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.slepukhin.dao.QuestionDao;
import ru.otus.slepukhin.domain.Question;
import ru.otus.slepukhin.domain.QuizResult;
import ru.otus.slepukhin.service.IO.IO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@DisplayName("Class QuizEngine")
class QuizEngineTest {
    static private final int RIGHT_ANSWERS_TO_PASS_QUIZ = 2;

    @DisplayName("should give negative result")
    @Test
    void shouldGiveNegativeResult() {
        List<String> testAnswers = this.arrayToList(new String[]{
                "wrongAnswer1",
                "rightAnswer2",
                });

        QuizEngine quizEngine = new QuizEngine(new TestDao(), new TestIO(testAnswers), RIGHT_ANSWERS_TO_PASS_QUIZ);
        QuizResult quizResult = quizEngine.process();

        Assertions.assertFalse(quizResult.isPassed());
    }

    @DisplayName("should give positive result")
    @Test
    void shouldGivePositiveResult() {
        List<String> testAnswers = this.arrayToList(new String[]{
                "rightAnswer1",
                "rightAnswer2",
                });

        QuizEngine quizEngine = new QuizEngine(new TestDao(), new TestIO(testAnswers), RIGHT_ANSWERS_TO_PASS_QUIZ);
        QuizResult quizResult = quizEngine.process();

        Assertions.assertTrue(quizResult.isPassed());
    }

    private <T> List<T> arrayToList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    static class TestIO implements IO {
        Iterator<String> answerIterator;

        public TestIO(List<String> answers) {
            answers.add(0, "Name request");
            this.answerIterator = answers.iterator();
        }

        @Override
        public void write(String out) {
        }

        @Override
        public String read() {
            return this.answerIterator.next();
        }
    }

    class TestDao implements QuestionDao {

        @Override
        public List<Question> getAll() {
            return arrayToList(new Question[]{
                    new Question("question1", "rightAnswer1"),
                    new Question("question2", "rightAnswer2"),
                    });
        }
    }
}
