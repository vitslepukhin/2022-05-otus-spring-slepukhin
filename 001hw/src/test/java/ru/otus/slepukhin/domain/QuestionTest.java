package ru.otus.slepukhin.domain;

import org.junit.jupiter.api.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.slepukhin.dao.QuestionCsvDaoImpl;


@DisplayName("Class Question")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionTest {

    private Question question;
    private static final String rightAnswerField = "rightAnswer";
    private static final String questionField = "question";

    @BeforeAll
    public void setup() {
        this.question = new Question(questionField, rightAnswerField);
    }

    @DisplayName("should have correct constructor")
    @Test
    public void shouldHaveCorrectConstructor() {
        Assertions.assertEquals(questionField, question.getQuestion());
        Assertions.assertEquals(rightAnswerField, question.getAnswer());
    }

    @DisplayName("should correct check right answer")
    @Test
    public void shouldHaveCorrectCheckRightAnswer() {
        Assertions.assertTrue(question.isRightAnswer(rightAnswerField));
    }

    @DisplayName("should correct check wrong answer")
    @Test
    public void shouldHaveCorrectCheckWrongAnswer() {
        Assertions.assertFalse(question.isRightAnswer("wrongAnswer"));
    }
}
