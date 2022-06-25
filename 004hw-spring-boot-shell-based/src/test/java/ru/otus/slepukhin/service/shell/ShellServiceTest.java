package ru.otus.slepukhin.service.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.slepukhin.service.quizengine.QuizEngine;

import static org.mockito.Mockito.verify;

@DisplayName("Class ShellService")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class ShellServiceTest {
    private static final String COMMAND_START_QUIZ = "quiz";
    private static final String COMMAND_START_QUIZ_SHORT = "q";

    @MockBean
    private QuizEngine mockQuizEngine;

    @Autowired
    private Shell shell;


    @DisplayName("Should invoke process method for quiz command")
    @Test
    void shouldInvokeStartTestingForQuizCommand() {
        shell.evaluate(() -> COMMAND_START_QUIZ);
        verify(mockQuizEngine).process();
    }

    @DisplayName("Should invoke process method for short quiz command")
    @Test
    void shouldInvokeStartTestingForShortQuizCommand() {
        shell.evaluate(() -> COMMAND_START_QUIZ_SHORT);
        verify(mockQuizEngine).process();
    }
}
