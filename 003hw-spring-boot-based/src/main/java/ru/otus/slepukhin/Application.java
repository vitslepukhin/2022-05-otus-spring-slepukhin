package ru.otus.slepukhin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.slepukhin.service.QuizEngine;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        QuizEngine quizEngine = context.getBean(QuizEngine.class);
        quizEngine.process();
    }
}
