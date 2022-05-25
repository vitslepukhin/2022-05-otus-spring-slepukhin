package ru.otus.slepukhin.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.slepukhin.dao.Dao;
import ru.otus.slepukhin.domain.Question;
import ru.otus.slepukhin.service.QuizIO.QuizIO;

import java.util.*;

@DisplayName("Class QuizEngine")
class QuizEngineTest {
    List questions;

    public QuizEngineTest() {
        this.questions = this.arrayToList(new Question[]{
                new Question("question1", "answer1"),
                new Question("question2", "answer2")
        });
    }

    @DisplayName("should give correct result")
    @Test
    void shouldGiveCorrectResult() {
        List<String> testAnswers = this.arrayToList(new String[]{
                "testAnswer1",
                "testAnswer2",
        });

        Map<Question, String> expectedResult = new HashMap<>();
        expectedResult.put((Question) this.questions.get(0), testAnswers.get(0));
        expectedResult.put((Question) this.questions.get(1), testAnswers.get(1));


        QuizEngine quizEngine = new QuizEngine(new TestDao(), new TestIO(testAnswers));
        quizEngine.process();
        Map<Question, String> result = quizEngine.getResult();
        Assertions.assertIterableEquals(expectedResult.keySet(), result.keySet());
    }

    private <T> List<T> arrayToList(T[] arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }

    class TestDao implements Dao<Question> {
        @Override
        public List<Question> getAll() {
            return questions;
        }
    }

    class TestIO implements QuizIO {
        List<String> answers;

        public TestIO(List<String> answers) {
            this.answers = answers;
        }

        @Override
        public void write(String out) {
        }

        @Override
        public String read() {
            String answer = this.answers.iterator().next();
            return answer;
        }
    }
}
