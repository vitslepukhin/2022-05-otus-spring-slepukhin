package ru.otus.slepukhin.domain;

import org.junit.jupiter.api.*;


@DisplayName("Class Question")
class QuestionTest {

    private static final String rightAnswerField = "rightAnswer";
    private static final String questionField = "question";
    private Question question;

    @BeforeEach
    public void setup() {
        this.question = new Question(questionField, rightAnswerField);
    }

    @DisplayName("should have correct constructor")
    @Test
    void shouldHaveCorrectConstructor() {
        Assertions.assertEquals(questionField, question.getQuestion());
        Assertions.assertEquals(rightAnswerField, question.getAnswer());
    }

    @DisplayName("should correct check right answer")
    @Test
    void shouldHaveCorrectCheckRightAnswer() {
        Assertions.assertTrue(question.isRightAnswer(rightAnswerField));
    }

    @DisplayName("should correct check wrong answer")
    @Test
    void shouldHaveCorrectCheckWrongAnswer() {
        Assertions.assertFalse(question.isRightAnswer("wrongAnswer"));
    }
}
