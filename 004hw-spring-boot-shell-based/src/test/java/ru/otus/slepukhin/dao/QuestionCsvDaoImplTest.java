package ru.otus.slepukhin.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.slepukhin.config.FileNameProvider;
import ru.otus.slepukhin.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Class QuestionCsvDaoImpl")
class QuestionCsvDaoImplTest {
    FileNameProvider mockFileNameProvider = mock(FileNameProvider.class);

    @DisplayName("should have correct constructor")
    @Test
    void shouldHaveCorrectConstructor() {
        String fileName = "test_questions.csv";
        when(mockFileNameProvider.getFileName()).thenReturn(fileName);

        QuestionCsvDaoImpl questionCsvDaoImpl = new QuestionCsvDaoImpl(mockFileNameProvider);
        Question[] questionArray = {
                new Question("question1", "answer1"),
                new Question("question2", "answer2")
        };
        List<Question> questions = new ArrayList<>(Arrays.asList(questionArray));

        try {
            Assertions.assertIterableEquals(questions, questionCsvDaoImpl.getAll());
        } catch (Exception e) {
            Assertions.fail("Expected no exception");
        }

    }

    @DisplayName("should throws exception")
    @Test
    void shouldThrowsException() {
        String fileName = "bad-file.csv";
        try {
            when(mockFileNameProvider.getFileName()).thenReturn(fileName);

            QuestionCsvDaoImpl questionCsvDaoImpl = new QuestionCsvDaoImpl(mockFileNameProvider);
            questionCsvDaoImpl.getAll();
            Assertions.fail("Expected QuestionsLoadingException");

        } catch (QuestionsLoadingException e) {
            Assertions.assertNotNull(e);
        }
    }
}

