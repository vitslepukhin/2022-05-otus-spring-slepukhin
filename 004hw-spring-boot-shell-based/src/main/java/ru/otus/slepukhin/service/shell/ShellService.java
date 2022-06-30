package ru.otus.slepukhin.service.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.slepukhin.service.quizengine.QuizEngine;

@ShellComponent
@AllArgsConstructor
public class ShellService {
    private QuizEngine quizEngine;

    @ShellMethod(value = "Start quiz command", key = {
            "q",
            "quiz"
    })
    public void startTesting() {
        quizEngine.process();
    }
}


