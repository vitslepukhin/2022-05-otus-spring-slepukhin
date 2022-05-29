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
import java.util.stream.Collectors;

@DisplayName("Class QuizEngine")
class QuizEngineTest {

    @DisplayName("should give correct result")
    @Test
    void shouldGiveCorrectResult() {
        List<String> testAnswers = this.arrayToList(new String[]{
                "testAnswer1",
                "testAnswer2",
                });

        QuizResult expectedResult = new QuizResult();
        List<Question> questions = new TestDao().getAll();
        expectedResult.answerQuestion(questions.get(0), testAnswers.get(0));
        expectedResult.answerQuestion(questions.get(1), testAnswers.get(1));

        List<String> expectedResultIterable = questions.stream().map(expectedResult::getAnswer).collect(Collectors.toList());

        QuizEngine quizEngine = new QuizEngine(new TestDao(), new TestIO(expectedResultIterable));
        QuizResult quizResult = quizEngine.process(true);

        List<String> resultIterable = questions.stream().map(quizResult::getAnswer).collect(Collectors.toList());
        Assertions.assertIterableEquals(expectedResultIterable, resultIterable);
    }

    private <T> List<T> arrayToList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    static class TestIO implements IO {
        Iterator<String> answerIterator;

        public TestIO(List<String> answers) {
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
                    new Question("question1", "answer1"),
                    new Question("question2", "answer2"),
                    });
        }
    }
}
