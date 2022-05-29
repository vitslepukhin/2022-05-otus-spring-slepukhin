package ru.otus.slepukhin.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.slepukhin.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Class QuestionCsvDaoImpl")
class QuestionCsvDaoImplTest {

    @DisplayName("should have correct constructor")
    @Test
    void shouldHaveCorrectConstructor() {
        String fileName = "test_questions.csv";
        Resource res = new ClassPathResource(fileName);
        QuestionCsvDaoImpl questionCsvDaoImpl = new QuestionCsvDaoImpl(res);
        Question[] questionArray = {
                new Question("question1", "answer1"),
                new Question("question2", "answer2")
        };
        List<Question> questions = new ArrayList<>(Arrays.asList(questionArray));
        Assertions.assertIterableEquals(questions, questionCsvDaoImpl.getAll());
    }
}

