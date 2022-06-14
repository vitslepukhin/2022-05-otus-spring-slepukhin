package ru.otus.slepukhin.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.slepukhin.config.QuizProperties;
import ru.otus.slepukhin.dao.QuestionDao;
import ru.otus.slepukhin.dao.QuestionsLoadingException;
import ru.otus.slepukhin.domain.Question;
import ru.otus.slepukhin.service.IO.IO;
import ru.otus.slepukhin.service.translation.Translation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("Class QuizEngine")
class QuizEngineTest {
    static private final int RIGHT_ANSWERS_TO_PASS_QUIZ = 2;
    static private final String STUDENT_NAME = "studentName";

    private final QuestionDao mockQuestionDao = mock(QuestionDao.class);
    private final IO mockIO = mock(IO.class);
    private final Translation mockTranslation = mock(Translation.class);
    private final QuizProperties mockQuizProperties = mock(QuizProperties.class);


    @BeforeEach
    void initQuestionDao() throws
            QuestionsLoadingException {
        when(mockQuestionDao.getAll()).thenReturn(arrayToList(new Question[]{
                new Question("question1", "rightAnswer1"),
                new Question("question2", "rightAnswer2"),
                }));
    }

    @BeforeEach
    void initRequestName() {
        when(mockIO.request("quiz-engine.name-request")).thenReturn(STUDENT_NAME);
    }

    @BeforeEach
    void initTranslations() {
        when(mockTranslation.translate(anyString())).thenAnswer(i -> i.getArguments()[0]);
    }

    @DisplayName("should give negative result")
    @Test
    void shouldGiveNegativeResult() {
        when(mockIO.request("question1")).thenReturn("wrongAnswer1");
        when(mockIO.request("question2")).thenReturn("rightAnswer2");
        when(mockQuizProperties.getRightAnswersToPassQuiz()).thenReturn(RIGHT_ANSWERS_TO_PASS_QUIZ);

        QuizEngine quizEngine = new QuizEngine(mockQuestionDao, mockIO, mockQuizProperties, mockTranslation);
        quizEngine.run();

        verify(mockIO, atLeastOnce()).write(getFormattedTestResult("quiz-engine.failed"));
    }

    @DisplayName("should give positive result")
    @Test
    void shouldGivePositiveResult() {
        when(mockIO.request("question1")).thenReturn("rightAnswer1");
        when(mockIO.request("question2")).thenReturn("rightAnswer2");
        when(mockQuizProperties.getRightAnswersToPassQuiz()).thenReturn(RIGHT_ANSWERS_TO_PASS_QUIZ);

        QuizEngine quizEngine = new QuizEngine(mockQuestionDao, mockIO, mockQuizProperties, mockTranslation);
        quizEngine.process();

        verify(mockIO, atLeastOnce()).write(getFormattedTestResult("quiz-engine.passed"));
    }

    private <T> List<T> arrayToList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    private String getFormattedTestResult(String conclusion) {
        String preamble = STUDENT_NAME + " quiz-engine.result-preamble ";
        return preamble + conclusion;
    }
}
