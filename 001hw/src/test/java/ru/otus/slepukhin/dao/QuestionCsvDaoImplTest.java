package ru.otus.slepukhin.dao;

import org.junit.jupiter.api.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.slepukhin.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Class QuestionCsvDaoImpl")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuestionCsvDaoImplTest {
    private QuestionCsvDaoImpl questionCsvDaoImpl;

    @BeforeAll
    public void setup() {
        String fileName = "test_questions.csv";
        Resource res = new ClassPathResource(fileName);
        this.questionCsvDaoImpl = new QuestionCsvDaoImpl(res);
    }

    @DisplayName("should have correct constructor")
    @Test
    void shouldHaveCorrectConstructor() {
        Question[] questionArray = {
                new Question("question1", "answer1"),
                new Question("question2", "answer2")
        };
        List<Question> questions = new ArrayList<>(Arrays.asList(questionArray));
        Assertions.assertIterableEquals(questions, questionCsvDaoImpl.getAll());
    }
}

